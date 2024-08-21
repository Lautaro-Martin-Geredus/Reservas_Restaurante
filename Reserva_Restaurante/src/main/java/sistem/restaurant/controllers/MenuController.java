package sistem.restaurant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sistem.restaurant.services.MenuService;

@RestController
@RequestMapping("/menus")
public class MenuController
{
    @Autowired
    private MenuService menuService;
}
