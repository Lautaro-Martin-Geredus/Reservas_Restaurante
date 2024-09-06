package sistem.restaurant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistem.restaurant.dtos.menuItem.MenuItemDto;
import sistem.restaurant.dtos.menuItem.NewMenuItemDto;
import sistem.restaurant.services.MenuService;

import java.util.List;

@RestController
@RequestMapping("/menusItems")
public class MenuItemController
{
    @Autowired
    private MenuService menuService;

    @PostMapping("CreateItem/{nameC}")
    public ResponseEntity<MenuItemDto> createMenuItem(@PathVariable String nameC, @RequestBody NewMenuItemDto newMenuItemDto)
    {
        return ResponseEntity.ok(menuService.createMenuItem(nameC, newMenuItemDto));
    }

    @PutMapping("EditItem/{iName}/{mCategory}")
    public ResponseEntity<MenuItemDto> updateMenuItem(@PathVariable String iName, @PathVariable String mCategory,
                                                      @RequestBody NewMenuItemDto newMenuItemDto)
    {
        return ResponseEntity.ok(menuService.updateMenuItem(iName, mCategory, newMenuItemDto));
    }

    @GetMapping("GetItems/{categ}")
    public ResponseEntity<List<MenuItemDto>> getAllItems(@PathVariable String categ)
    {
        return ResponseEntity.ok(menuService.getAllItems(categ));
    }

    @DeleteMapping("DeleteItems/{itemN}")
    public ResponseEntity<Boolean> deleteItem(@PathVariable String itemN)
    {
        return ResponseEntity.ok(menuService.deleteMenuItem(itemN));
    }
}
