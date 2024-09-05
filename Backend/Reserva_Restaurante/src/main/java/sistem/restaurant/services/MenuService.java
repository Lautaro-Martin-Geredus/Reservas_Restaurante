package sistem.restaurant.services;

import org.springframework.stereotype.Service;
import sistem.restaurant.dtos.menu.MenuDto;
import sistem.restaurant.dtos.menuItem.MenuItemDto;
import sistem.restaurant.dtos.menu.NewMenuDto;
import sistem.restaurant.dtos.menuItem.NewMenuItemDto;

import java.util.List;

@Service
public interface MenuService
{
    MenuDto createMenu(String restaurantName, NewMenuDto newMenuDto);

    MenuDto updateMenu(String menuCategory, String restaurantName, NewMenuDto menuDto);

    boolean deleteMenu(String menuCategory);

    List<MenuDto> getAllMenus();

    // MENU ITEM

    MenuItemDto createMenuItem(String menu, NewMenuItemDto newMenuItemDto);

    MenuItemDto updateMenuItem(String itemName, String menuCategory, NewMenuItemDto newMenuItemDto);

    List<MenuItemDto> getAllItems();

    boolean deleteMenuItem(String menuName);
}
