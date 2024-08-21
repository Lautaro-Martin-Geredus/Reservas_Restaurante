package sistem.restaurant.services.impl;

import jakarta.persistence.EntityExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sistem.restaurant.dtos.menu.MenuDto;
import sistem.restaurant.dtos.menu.MenuItemDto;
import sistem.restaurant.dtos.menu.NewMenuDto;
import sistem.restaurant.entities.Menu;
import sistem.restaurant.entities.Restaurant;
import sistem.restaurant.repositories.MenuItemRepository;
import sistem.restaurant.repositories.MenuRepository;
import sistem.restaurant.repositories.RestaurantRepository;
import sistem.restaurant.services.MenuService;

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
        Optional<Menu> menuOptional = menuRepository.findByCategory(newMenuDto.getCategory());
        if(restaurant.isEmpty())
        {
            throw new EntityExistsException("Restaurant not found with name!");
        }
        if(menuOptional.isPresent())
        {
            throw new EntityExistsException("That Menu already exist!");
        }

        Menu menu = new Menu();
        menu.setCategory(newMenuDto.getCategory());
        menu.setRestaurant(modelMapper.map(restaurant.get(), Restaurant.class));

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
            Menu m = new Menu();
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

    // Menu Item

    @Override
    public MenuItemDto createMenuItem(MenuItemDto menuItemDto)
    {
        return null;
    }

    @Override
    public MenuItemDto updateMenuItem(MenuItemDto menuItemDto)
    {
        return null;
    }

    @Override
    public boolean deleteMenuItem(String menuName)
    {
        return false;
    }
}
