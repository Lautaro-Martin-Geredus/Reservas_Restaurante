package sistem.restaurant.services.impl;

import jakarta.persistence.EntityExistsException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistem.restaurant.dtos.menu.MenuDto;
import sistem.restaurant.dtos.menuItem.MenuItemDto;
import sistem.restaurant.dtos.menu.NewMenuDto;
import sistem.restaurant.dtos.menuItem.NewMenuItemDto;
import sistem.restaurant.entities.Menu;
import sistem.restaurant.entities.MenuItem;
import sistem.restaurant.entities.Restaurant;
import sistem.restaurant.repositories.MenuItemRepository;
import sistem.restaurant.repositories.MenuRepository;
import sistem.restaurant.repositories.RestaurantRepository;
import sistem.restaurant.services.MenuService;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService
{
    @Autowired private MenuItemRepository menuItemRepository;
    @Autowired private MenuRepository menuRepository;
    @Autowired private ModelMapper modelMapper;
    @Autowired private RestaurantRepository restaurantRepository;

    @Override
    public MenuDto createMenu(String restaurantName, NewMenuDto newMenuDto)
    {
        Optional<Restaurant> restaurant = restaurantRepository.findByName(restaurantName);
        if(restaurant.isEmpty())
        {
            throw new EntityExistsException("Restaurant not found with name!");
        }

        Menu menu = new Menu();
        menu.setCategory(newMenuDto.getCategory());
        menu.setRestaurant(restaurant.get());

        menuRepository.save(menu);

        MenuDto m = modelMapper.map(menu, MenuDto.class);
        m.setRestaurantName(restaurant.get().getName());

        return m;
    }

    @Override
    public MenuDto updateMenu(String menuCategory, String restaurantName, NewMenuDto menuDto)
    {
        Optional<Menu> menu = menuRepository.findByCategory(menuCategory);
        Optional<Restaurant> restaurant = restaurantRepository.findByName(restaurantName);
        if(menu.isPresent() && restaurant.isPresent())
        {
            Menu m = menu.get();
            m.setCategory(menuDto.getCategory());
            m.setRestaurant(modelMapper.map(restaurant.get(), Restaurant.class));

            menuRepository.save(m);

            MenuDto menuD = modelMapper.map(m, MenuDto.class);
            menuD.setRestaurantName(restaurant.get().getName());

            return menuD;
        }

        throw new EntityExistsException("Restaurant and Menu not found!");
    }

    @Override
    public boolean deleteMenu(String menuCategory)
    {
        Optional<Menu> menuOptional = menuRepository.findByCategory(menuCategory);
        if(menuOptional.isPresent())
        {
            menuRepository.delete(menuOptional.get());
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public List<MenuDto> getAllMenus()
    {
        List<Menu> menu = menuRepository.findAll();
        Type listType = new TypeToken<List<MenuDto>>() {}.getType();

        return modelMapper.map(menu, listType);
    }

    // MENU ITEM

    @Override
    public MenuItemDto createMenuItem(String menu, NewMenuItemDto newMenuItemDto)
    {
        Optional<Menu> menuOptional = menuRepository.findByCategory(menu);
        if(menuOptional.isEmpty())
        {
            throw new EntityExistsException("Menu not found!");
        }

        MenuItem mI = new MenuItem();
        mI.setName(newMenuItemDto.getName());
        mI.setDescription(newMenuItemDto.getDescription());
        mI.setPrice(newMenuItemDto.getPrice());
        mI.setMenu(modelMapper.map(menuOptional.get(), Menu.class));

        menuItemRepository.save(mI);
        MenuItemDto menuItemDto = modelMapper.map(mI, MenuItemDto.class);
        menuItemDto.setMenuName(menuOptional.get().getCategory());

        return menuItemDto;
    }

    @Override
    public MenuItemDto updateMenuItem(String itemName, String menuCategory, NewMenuItemDto newMenuItemDto)
    {
        Optional<MenuItem> menuItemOptional = menuItemRepository.findByName(itemName);
        Optional<Menu> menuOptional = menuRepository.findByCategory(menuCategory);
        if(menuItemOptional.isEmpty() && menuOptional.isEmpty())
        {
            throw new EntityExistsException("Menu and Item not found!");
        }

        MenuItem menuItem = menuItemOptional.get();
        menuItem.setName(newMenuItemDto.getName());
        menuItem.setDescription(newMenuItemDto.getDescription());
        menuItem.setPrice(newMenuItemDto.getPrice());

        menuItemRepository.save(menuItem);
        MenuItemDto menuItemDto = modelMapper.map(menuItem, MenuItemDto.class);
        menuItemDto.setMenuName(menuOptional.get().getCategory());

        return menuItemDto;
    }

    @Override
    public List<MenuItemDto> getAllItems()
    {
        List<MenuItem> menuItems = menuItemRepository.findAll();
        Type listType = new TypeToken<List<MenuItemDto>>() {}.getType();

        return modelMapper.map(menuItems, listType);
    }

    @Override
    public boolean deleteMenuItem(String itemName)
    {
        Optional<MenuItem> menuItemOptional = menuItemRepository.findByName(itemName);
        if(menuItemOptional.isPresent())
        {
            menuItemRepository.delete(menuItemOptional.get());
            return true;
        }
        else {
            return false;
        }
    }
}
