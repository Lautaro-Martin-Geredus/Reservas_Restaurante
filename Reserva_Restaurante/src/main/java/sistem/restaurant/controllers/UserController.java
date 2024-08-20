package sistem.restaurant.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sistem.restaurant.dtos.user.UserDto;
import sistem.restaurant.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController
{
    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto)
    {
        return null;
    }
}
