package sistem.restaurant.services;

import org.springframework.stereotype.Service;
import sistem.restaurant.dtos.user.UserDto;

@Service
public interface UserService
{
    UserDto createUser(UserDto user);
}
