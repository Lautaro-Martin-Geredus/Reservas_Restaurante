package sistem.restaurant.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistem.restaurant.dtos.user.UserDto;
import sistem.restaurant.repositories.UserRepository;
import sistem.restaurant.services.UserService;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto user)
    {
        return null;
    }
}
