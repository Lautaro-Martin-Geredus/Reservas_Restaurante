package sistem.restaurant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistem.restaurant.dtos.menuItem.MenuItemDto;
import sistem.restaurant.dtos.menuItem.NewMenuItemDto;
import sistem.restaurant.services.MenuService;

@RestController
@RequestMapping("/menusItems")
public class MenuItemController
{
    @Autowired
    private MenuService menuService;

    @PostMapping("/{nameC}")
    public ResponseEntity<MenuItemDto> createMenuItem(@PathVariable String nameC, @RequestBody NewMenuItemDto newMenuItemDto)
    {
        return ResponseEntity.ok(menuService.createMenuItem(nameC, newMenuItemDto));
    }
}
