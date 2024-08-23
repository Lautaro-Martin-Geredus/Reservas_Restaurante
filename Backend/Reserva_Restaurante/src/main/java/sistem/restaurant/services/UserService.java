package sistem.restaurant.services;

import org.springframework.stereotype.Service;
import sistem.restaurant.dtos.user.Credential;
import sistem.restaurant.dtos.user.UserDto;
import sistem.restaurant.dtos.user.UserResponseDto;

@Service
public interface UserService
{
    UserResponseDto loginUser(Credential credential);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(String email,UserDto userDto);
}
