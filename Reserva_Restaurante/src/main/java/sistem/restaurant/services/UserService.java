package sistem.restaurant.services;

import org.springframework.stereotype.Service;
import sistem.restaurant.dtos.user.UserDto;
import sistem.restaurant.dtos.user.UserResponseDto;

@Service
public interface UserService
{
    UserDto createUser(UserDto userDto);

    UserResponseDto getUserByName(String name);

    boolean deleteUserByName(String name);
}
