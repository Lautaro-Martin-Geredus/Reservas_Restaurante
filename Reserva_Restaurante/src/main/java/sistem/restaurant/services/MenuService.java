package sistem.restaurant.services;

import org.springframework.stereotype.Service;
import sistem.restaurant.dtos.menu.MenuDto;
import sistem.restaurant.dtos.menu.MenuItemDto;

@Service
public interface MenuService
{
    MenuDto createMenu(MenuDto menuDto);

    MenuDto updateMenu(MenuDto menuDto);

    boolean deleteMenu(String menuName);

    // Menu Item

    MenuItemDto createMenuItem(MenuItemDto menuItemDto);

    MenuItemDto updateMenuItem(MenuItemDto menuItemDto);

    boolean deleteMenuItem(String menuName);
}
