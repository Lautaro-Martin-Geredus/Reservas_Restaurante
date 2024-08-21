package sistem.restaurant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sistem.restaurant.dtos.menu.MenuDto;
import sistem.restaurant.services.MenuService;

@RestController
@RequestMapping("/menus")
public class MenuController
{
    @Autowired
    private MenuService menuService;

    @PostMapping("")
    public ResponseEntity<MenuDto> createMenu(MenuDto menuDto)
    {
        return ResponseEntity.ok(menuService.createMenu(menuDto));
    }
}
