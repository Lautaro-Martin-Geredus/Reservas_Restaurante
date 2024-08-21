package sistem.restaurant.services.impl;

import jakarta.persistence.EntityExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sistem.restaurant.dtos.menu.MenuDto;
import sistem.restaurant.dtos.menu.MenuItemDto;
import sistem.restaurant.entities.Menu;
import sistem.restaurant.entities.Restaurant;
import sistem.restaurant.repositories.MenuItemRepository;
import sistem.restaurant.repositories.MenuRepository;
import sistem.restaurant.services.MenuService;

import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService
{
    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private MenuRepository MenuRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Qualifier("modelMapper")
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MenuDto createMenu(MenuDto menuDto)
    {
        Optional<Menu> menu = menuRepository.findByCategory(menuDto.getCategory());
        if(menu.isPresent())
        {
            throw new EntityExistsException("This Menu already exists!") ;
        }
        Menu menu1 = new Menu();
        menu1.setCategory(menuDto.getCategory());
        menu1.setRestaurant(modelMapper.map(menuDto.getRestaurantName(), Restaurant.class));

        menuRepository.save(menu1);
        return modelMapper.map(menu1, MenuDto.class);
    }

    @Override
    public MenuDto updateMenu(MenuDto menuDto)
    {
        return null;
    }

    @Override
    public boolean deleteMenu(String menuName)
    {
        return false;
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
