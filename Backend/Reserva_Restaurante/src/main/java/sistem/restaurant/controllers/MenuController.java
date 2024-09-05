package sistem.restaurant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistem.restaurant.dtos.menu.MenuDto;
import sistem.restaurant.dtos.menu.NewMenuDto;
import sistem.restaurant.dtos.menuItem.MenuItemDto;
import sistem.restaurant.dtos.menuItem.NewMenuItemDto;
import sistem.restaurant.entities.Menu;
import sistem.restaurant.services.MenuService;

import java.util.List;

@RestController
@RequestMapping("/menus")
public class MenuController
{
    @Autowired
    private MenuService menuService;

    @PostMapping("/{name}")
    public ResponseEntity<MenuDto> createMenu(@PathVariable String name, @RequestBody NewMenuDto newMenuDto)
    {
        return ResponseEntity.ok(menuService.createMenu(name, newMenuDto));
    }

    @PutMapping("/{restaurantName}/{menuCategory}")
    public ResponseEntity<MenuDto> updateMenu(@PathVariable String restaurantName, @PathVariable String menuCategory,
            @RequestBody NewMenuDto menuDto)
    {
        MenuDto updatedMenu = menuService.updateMenu(menuCategory, restaurantName, menuDto);
        return ResponseEntity.ok(updatedMenu);
    }

    @DeleteMapping("/{menuCategory}")
    public ResponseEntity<Boolean> deleteMenu(@PathVariable String menuCategory)
    {
        return ResponseEntity.ok(menuService.deleteMenu(menuCategory));
    }

    @GetMapping("")
    public ResponseEntity<List<MenuDto>> getAllMenus()
    {
        return ResponseEntity.ok(menuService.getAllMenus());
    }
}
