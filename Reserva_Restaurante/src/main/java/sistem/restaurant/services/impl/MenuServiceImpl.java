package sistem.restaurant.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistem.restaurant.repositories.MenuItemRepository;
import sistem.restaurant.services.MenuService;
@Service
public class MenuServiceImpl implements MenuService
{
    @Autowired
    private MenuItemRepository menuItemRepository;
}
