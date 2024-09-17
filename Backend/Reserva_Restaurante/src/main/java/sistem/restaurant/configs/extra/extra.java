package sistem.restaurant.configs.extra;

public class extra
{
    /*
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
    public UserDto updateUser(String email, UserDto userDto)
    {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty())
        {
            throw new EntityExistsException("Email Not Found!");
        }
        else
        {
            User user1 = user.get();
            user1.setName(userDto.getName());
            user1.setEmail(userDto.getEmail());
            user1.setPassword(userDto.getPassword());
            user1.setRole(userDto.getRole());

            User userSaved = userRepository.save(user1);
            return modelMapper.map(userSaved, UserDto.class);
        }
    }
}


package sistem.restaurant.services.impl;

import jakarta.persistence.EntityExistsException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistem.restaurant.dtos.table.NewTableDto;
import sistem.restaurant.dtos.table.TableeDto;
import sistem.restaurant.entities.Restaurant;
import sistem.restaurant.entities.Tablee;
import sistem.restaurant.repositories.RestaurantRepository;
import sistem.restaurant.repositories.TableeRepository;
import sistem.restaurant.services.RestaurantService;
import sistem.restaurant.services.TableService;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class TableServiceImpl implements TableService
{
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TableeRepository tableeRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public TableeDto createTable(String restaurantName, NewTableDto tableeDto)
    {
        Optional<Restaurant> restaurant = restaurantRepository.findByName(restaurantName);
        Optional<Tablee> tableeOptional = tableeRepository.findByNumber(tableeDto.getNumber());
        if(restaurant.isEmpty())
        {
            throw new EntityExistsException("Restaurant not found with name!");
        }
        if(tableeOptional.isPresent())
        {
            throw new EntityExistsException("Table with that number already exist!");
        }

        Tablee tablee = new Tablee();
        tablee.setNumber(tableeDto.getNumber());
        tablee.setSeats(tableeDto.getSeats());
        tablee.setAvailable(true);
        tablee.setRestaurant(modelMapper.map(restaurant.get(), Restaurant.class));

        tableeRepository.save(tablee);

        TableeDto t = modelMapper.map(tablee, TableeDto.class);
        t.setRestaurantName(restaurant.get().getName());
        return t;
    }

    @Override
    public List<TableeDto> getAllTables()
    {
        List<Tablee> table = tableeRepository.findAll();
        Type listType = new TypeToken<List<TableeDto>>() {}.getType();

        return modelMapper.map(table, listType);
    }
}

package sistem.restaurant.services.impl;

import jakarta.persistence.EntityExistsException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistem.restaurant.dtos.menuItem.NewMenuItemDto;
import sistem.restaurant.dtos.review.NewReviewDto;
import sistem.restaurant.dtos.review.ReviewDto;
import sistem.restaurant.entities.MenuItem;
import sistem.restaurant.entities.Restaurant;
import sistem.restaurant.entities.Review;
import sistem.restaurant.entities.User;
import sistem.restaurant.repositories.RestaurantRepository;
import sistem.restaurant.repositories.ReviewRepository;
import sistem.restaurant.repositories.UserRepository;
import sistem.restaurant.services.ReviewService;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService
{
    @Autowired private ModelMapper modelMapper;
    @Autowired private ReviewRepository reviewRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private RestaurantRepository restaurantRepository;

    @Override
    public List<ReviewDto> getAllReviews()
    {
        List<Review> reviews = reviewRepository.findAll();
        Type listType = new TypeToken<List<ReviewDto>>() {}.getType();

        return modelMapper.map(reviews, listType);
    }

    @Override
    public ReviewDto createReview(String userName, String restaurantName, NewReviewDto newReviewDto)
    {
        Optional<Review> reviewOptional = reviewRepository.findByClientName(newReviewDto.getClientName());
        Optional<Restaurant> restaurantOptional = restaurantRepository.findByName(restaurantName);
        Optional<User> userOptional = userRepository.findByName(userName);
        if(restaurantOptional.isEmpty() && userOptional.isEmpty())
        {
            throw new EntityExistsException("Review with that parameters not found!");
        }

        Review review = new Review();
        review.setUser(modelMapper.map(userOptional.get(), User.class));
        review.setRestaurant(modelMapper.map(restaurantOptional.get(), Restaurant.class));
        review.setClientName(newReviewDto.getClientName());
        review.setRating(newReviewDto.getRating());
        review.setComment(newReviewDto.getComment());
        review.setReviewDateTime(newReviewDto.getReviewDateTime());

        reviewRepository.save(review);
        ReviewDto r = modelMapper.map(review, ReviewDto.class);
        r.setUserName(userOptional.get().getName());
        r.setRestaurantName(restaurantOptional.get().getName());

        return r;
    }
}
package sistem.restaurant.services.impl;

import jakarta.persistence.EntityExistsException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistem.restaurant.dtos.restaurant.RestaurantDto;
import sistem.restaurant.dtos.table.NewTableDto;
import sistem.restaurant.entities.Restaurant;
import sistem.restaurant.repositories.RestaurantRepository;
import sistem.restaurant.services.RestaurantService;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService
{
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RestaurantDto createRestaurant(RestaurantDto restaurantDto)
    {
        Optional<Restaurant> restaurant = restaurantRepository.findByName(restaurantDto.getName());
        if(restaurant.isPresent())
        {
            throw new EntityExistsException("This restaurant is already registered!");
        }
        Restaurant restaurant1 = new Restaurant();
        restaurant1.setName(restaurantDto.getName());
        restaurant1.setAddress(restaurantDto.getAddress());
        restaurant1.setPhoneNumber(restaurantDto.getPhoneNumber());
        restaurant1.setOpeningHours(restaurantDto.getOpeningHours());

        restaurantRepository.save(restaurant1);
        return modelMapper.map(restaurant1, RestaurantDto.class);
    }

    @Override
    public RestaurantDto updateRestaurant(String name, RestaurantDto restaurantDto)
    {
        Optional<Restaurant> restaurant = restaurantRepository.findByName(name);
        if(restaurant.isEmpty())
        {
            throw new EntityExistsException("Not restaurant found!");
        }
        Restaurant restaurant1 = restaurant.get();
        restaurant1.setName(restaurantDto.getName());
        restaurant1.setAddress(restaurantDto.getAddress());
        restaurant1.setPhoneNumber(restaurantDto.getPhoneNumber());
        restaurant1.setOpeningHours(restaurantDto.getOpeningHours());

        restaurantRepository.save(restaurant1);
        return modelMapper.map(restaurant1, RestaurantDto.class);
    }

    @Override
    public boolean deleteRestaurant(String name)
    {
        Optional<Restaurant> restaurant = restaurantRepository.findByName(name);
        if(restaurant.isPresent())
        {
            Restaurant restaurant1 = restaurant.get();
            restaurantRepository.delete(restaurant1);

            return true;
        }
        return false;
    }

    @Override
    public List<RestaurantDto> getAllRestaurant()
    {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        Type listType = new TypeToken<List<RestaurantDto>>() {}.getType();

        return modelMapper.map(restaurants, listType);
    }
}


    * */
}
