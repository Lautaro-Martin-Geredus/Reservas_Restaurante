package sistem.restaurant.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistem.restaurant.dtos.user.UserDto;
import sistem.restaurant.dtos.user.UserResponseDto;
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
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @GetMapping("/{name}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable String name)
    {
        return ResponseEntity.ok(userService.getUserByName(name));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String name)
    {
        return ResponseEntity.ok(userService.deleteUserByName(name));
    }
}
