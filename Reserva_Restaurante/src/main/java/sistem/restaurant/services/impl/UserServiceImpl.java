package sistem.restaurant.services.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistem.restaurant.dtos.user.Credential;
import sistem.restaurant.dtos.user.UserDto;
import sistem.restaurant.dtos.user.UserResponseDto;
import sistem.restaurant.entities.User;
import sistem.restaurant.repositories.UserRepository;
import sistem.restaurant.services.UserService;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserResponseDto loginUser(Credential credential)
    {
        Optional<User> user = userRepository.findByUserNameOrEmailAndPassword(credential.getIdentity(), credential.getPassword());
        if(user.isPresent())
        {
            return modelMapper.map(user.get(), UserResponseDto.class);
        }
        else
        {
            throw new EntityNotFoundException("Some parameters are invalid!");
        }
    }

    @Override
    public UserDto createUser(UserDto userDto)
    {
        Optional<User> user = userRepository.findByEmail(userDto.getEmail());
        if(user.isPresent())
        {
            throw new EntityExistsException("This email is already registered!");
        }
        else
        {
            User user1 = new User();
            user1.setName(userDto.getName());
            user1.setEmail(userDto.getEmail());
            user1.setPassword(userDto.getPassword());
            user1.setRole(userDto.getRole());

            User userSaved = userRepository.save(user1);
            return modelMapper.map(userSaved, UserDto.class);
        }
    }

    @Override
    public UserResponseDto getUserByName(String name)
    {
        Optional<User> user = userRepository.findByName(name);
        if(user.isEmpty())
        {
            throw new EntityExistsException("There is no user with that name!");
        }
        return modelMapper.map(user, UserResponseDto.class);
    }
}
