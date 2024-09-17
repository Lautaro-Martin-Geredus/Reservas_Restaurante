package sistem.restaurant.configs.extra;

public class extra_2
{
    /*

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

package sistem.restaurant.services.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sistem.restaurant.dtos.menuItem.NewMenuItemDto;
import sistem.restaurant.dtos.reservation.NewReservationDto;
import sistem.restaurant.dtos.reservation.ReservationDto;
import sistem.restaurant.entities.*;
import sistem.restaurant.repositories.ReservationRepository;
import sistem.restaurant.repositories.RestaurantRepository;
import sistem.restaurant.repositories.TableeRepository;
import sistem.restaurant.repositories.UserRepository;
import sistem.restaurant.services.ReservationService;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService
{
    @Autowired private ReservationRepository reservationRepository;
    @Autowired private TableeRepository tableeRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private ModelMapper modelMapper;
    @Autowired private RestaurantRepository restaurantRepository;

    @Override
    public ReservationDto createReservation(String userName, int tableNumber, String restaurantName,
                                            NewReservationDto newReservationDto)
    {
        Optional<User> userOptional = userRepository.findByName(userName);
        Optional<Tablee> tableOptional = tableeRepository.findByNumber(tableNumber);
        Optional<Restaurant> restaurantOptional = restaurantRepository.findByName(restaurantName);
        Optional<Reservation> reservationOptional = reservationRepository.findByClientName(newReservationDto.getClientName());

        if(userOptional.isEmpty() && tableOptional.isEmpty() && restaurantOptional.isEmpty())
        {
            throw new EntityNotFoundException("User, Table and Restaurant not found!");
        }

        if(reservationOptional.isPresent())
        {
            throw new EntityExistsException("Reservation already exists!");
        }

        Tablee table = tableOptional.get();

        if (!table.isAvailable())
        {
            throw new IllegalStateException("Table is already reserved!");
        }

        table.setAvailable(false);
        tableeRepository.save(table);

        Reservation reservation = new Reservation();
        reservation.setUser(userOptional.get());
        reservation.setClientName(newReservationDto.getClientName());
        reservation.setTable(table);
        reservation.setRestaurant(restaurantOptional.get());
        reservation.setReservationDateTime(newReservationDto.getReservationDateTime());

        reservationRepository.save(reservation);

        ReservationDto r = modelMapper.map(reservation, ReservationDto.class);
        r.setUserName(userOptional.get().getName());
        r.setTableNumber(table.getNumber());
        r.setRestaurantName(restaurantOptional.get().getName());

        return r;
    }

    @Override
    public boolean deleteReservation(String clientName)
    {
        Optional<Reservation> reservationOptional = reservationRepository.findByClientName(clientName);
        if(reservationOptional.isPresent())
        {
            reservationRepository.delete(reservationOptional.get());
            return true;
        }
        return false;
    }

    // Admin

    @Override
    public List<ReservationDto> getAllReservations()
    {
        List<Reservation> reservations = reservationRepository.findAll();
        Type listType = new TypeToken<List<ReservationDto>>() {}.getType();

        return modelMapper.map(reservations, listType);
    }
}

package sistem.restaurant.services.impl;

import jakarta.persistence.EntityExistsException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistem.restaurant.dtos.menu.MenuDto;
import sistem.restaurant.dtos.menuItem.MenuItemDto;
import sistem.restaurant.dtos.menu.NewMenuDto;
import sistem.restaurant.dtos.menuItem.NewMenuItemDto;
import sistem.restaurant.entities.Menu;
import sistem.restaurant.entities.MenuItem;
import sistem.restaurant.entities.Restaurant;
import sistem.restaurant.repositories.MenuItemRepository;
import sistem.restaurant.repositories.MenuRepository;
import sistem.restaurant.repositories.RestaurantRepository;
import sistem.restaurant.services.MenuService;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService
{
    @Autowired private MenuItemRepository menuItemRepository;
    @Autowired private MenuRepository menuRepository;
    @Autowired private ModelMapper modelMapper;
    @Autowired private RestaurantRepository restaurantRepository;

    @Override
    public MenuDto createMenu(String restaurantName, NewMenuDto newMenuDto)
    {
        Optional<Restaurant> restaurant = restaurantRepository.findByName(restaurantName);
        if(restaurant.isEmpty())
        {
            throw new EntityExistsException("Restaurant not found with name!");
        }

        Menu menu = new Menu();
        menu.setCategory(newMenuDto.getCategory());
        menu.setRestaurant(restaurant.get());

        menuRepository.save(menu);

        MenuDto m = modelMapper.map(menu, MenuDto.class);
        m.setRestaurantName(restaurant.get().getName());

        return m;
    }

    @Override
    public MenuDto updateMenu(String menuCategory, String restaurantName, NewMenuDto menuDto)
    {
        Optional<Menu> menu = menuRepository.findByCategory(menuCategory);
        Optional<Restaurant> restaurant = restaurantRepository.findByName(restaurantName);
        if(menu.isPresent() && restaurant.isPresent())
        {
            Menu m = menu.get();
            m.setCategory(menuDto.getCategory());
            m.setRestaurant(modelMapper.map(restaurant.get(), Restaurant.class));

            menuRepository.save(m);

            MenuDto menuD = modelMapper.map(m, MenuDto.class);
            menuD.setRestaurantName(restaurant.get().getName());

            return menuD;
        }

        throw new EntityExistsException("Restaurant and Menu not found!");
    }

    @Override
    public boolean deleteMenu(String menuCategory)
    {
        Optional<Menu> menuOptional = menuRepository.findByCategory(menuCategory);
        if(menuOptional.isPresent())
        {
            menuRepository.delete(menuOptional.get());
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public List<MenuDto> getAllMenus()
    {
        List<Menu> menu = menuRepository.findAll();
        Type listType = new TypeToken<List<MenuDto>>() {}.getType();

        return modelMapper.map(menu, listType);
    }

    // MENU ITEM

    @Override
    public MenuItemDto createMenuItem(String menu, NewMenuItemDto newMenuItemDto)
    {
        Optional<Menu> menuOptional = menuRepository.findByCategory(menu);
        if(menuOptional.isEmpty())
        {
            throw new EntityExistsException("Menu not found!");
        }

        MenuItem mI = new MenuItem();
        mI.setName(newMenuItemDto.getName());
        mI.setDescription(newMenuItemDto.getDescription());
        mI.setPrice(newMenuItemDto.getPrice());
        mI.setMenu(modelMapper.map(menuOptional.get(), Menu.class));

        menuItemRepository.save(mI);
        MenuItemDto menuItemDto = modelMapper.map(mI, MenuItemDto.class);
        //menuItemDto.setMenuName(menuOptional.get().getCategory());

        return menuItemDto;
    }

    @Override
    public MenuItemDto updateMenuItem(String itemName, String menuCategory, NewMenuItemDto newMenuItemDto)
    {
        Optional<MenuItem> menuItemOptional = menuItemRepository.findByName(itemName);
        Optional<Menu> menuOptional = menuRepository.findByCategory(menuCategory);
        if(menuItemOptional.isEmpty() && menuOptional.isEmpty())
        {
            throw new EntityExistsException("Menu and Item not found!");
        }

        MenuItem menuItem = menuItemOptional.get();
        menuItem.setName(newMenuItemDto.getName());
        menuItem.setDescription(newMenuItemDto.getDescription());
        menuItem.setPrice(newMenuItemDto.getPrice());

        menuItemRepository.save(menuItem);
        MenuItemDto menuItemDto = modelMapper.map(menuItem, MenuItemDto.class);
        //menuItemDto.setMenuName(menuOptional.get().getCategory());

        return menuItemDto;
    }

    @Override
    public List<MenuItemDto> getAllItems(String category)
    {
        List<MenuItem> menuItems;
        if (category == null || category.isEmpty())
        {
            menuItems = menuItemRepository.findAll();
        }
        else
        {
            menuItems = menuItemRepository.findByMenuCategory(category);
        }
        Type listType = new TypeToken<List<MenuItemDto>>() {}.getType();
        return modelMapper.map(menuItems, listType);
    }


    @Override
    public boolean deleteMenuItem(String itemName)
    {
        Optional<MenuItem> menuItemOptional = menuItemRepository.findByName(itemName);
        if(menuItemOptional.isPresent())
        {
            menuItemRepository.delete(menuItemOptional.get());
            return true;
        }
        else {
            return false;
        }
    }
}

@RestController
@RequestMapping("/users")
public class UserController
{
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public ResponseEntity<UserResponseDto> LoginUser(@RequestBody @Valid Credential credential)
    {
        return ResponseEntity.ok(userService.loginUser(credential));
    }

    @PostMapping("")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto)
    {
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @PutMapping("/{email}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String email, @RequestBody @Valid UserDto userDto)
    {
        return ResponseEntity.ok(userService.updateUser(email, userDto));
    }
}


@RestController
@RequestMapping("/tables")
public class TableController
{
    @Autowired
    private TableService tableService;

    @PostMapping("/{name}")
    public ResponseEntity<TableeDto> createTable(@PathVariable String name, @RequestBody NewTableDto tableeDto)
    {
        return ResponseEntity.ok(tableService.createTable(name, tableeDto));
    }

    @GetMapping("")
    public ResponseEntity<List<TableeDto>> getAllTables()
    {
        return ResponseEntity.ok(tableService.getAllTables());
    }
}

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistem.restaurant.dtos.review.NewReviewDto;
import sistem.restaurant.dtos.review.ReviewDto;
import sistem.restaurant.services.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController
{
    @Autowired
    private ReviewService reviewService;

    @GetMapping("")
    public ResponseEntity<List<ReviewDto>> getAllReviews()
    {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @PostMapping("/{userName}/{restaurantName}")
    public ResponseEntity<ReviewDto> createReview(@PathVariable String userName, @PathVariable String restaurantName,
                                                  @RequestBody NewReviewDto newReviewDto)
    {
        return ResponseEntity.ok(reviewService.createReview(userName, restaurantName, newReviewDto));
    }
}

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistem.restaurant.dtos.restaurant.RestaurantDto;
import sistem.restaurant.services.RestaurantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistem.restaurant.dtos.restaurant.RestaurantDto;
import sistem.restaurant.services.RestaurantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistem.restaurant.dtos.restaurant.RestaurantDto;
import sistem.restaurant.services.RestaurantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistem.restaurant.dtos.restaurant.RestaurantDto;
import sistem.restaurant.services.RestaurantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistem.restaurant.dtos.restaurant.RestaurantDto;
import sistem.restaurant.services.RestaurantService;


import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController
{
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("CreateRestaurant")
    public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody RestaurantDto restaurantDto)
    {
        return ResponseEntity.ok(restaurantService.createRestaurant(restaurantDto));
    }

    @PutMapping("updateRestaurant/{name}")
    public ResponseEntity<RestaurantDto> updateRestaurant(@PathVariable String name, @RequestBody RestaurantDto restaurantDto)
    {
        return ResponseEntity.ok(restaurantService.updateRestaurant(name, restaurantDto));
    }

    @DeleteMapping("DeleteRestaurat/{name}")
    public ResponseEntity<Boolean> deleteRestaurant(@PathVariable String name)
    {
        return ResponseEntity.ok(restaurantService.deleteRestaurant(name));
    }

    @GetMapping("GetRestaurant")
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants()
    {
        return ResponseEntity.ok(restaurantService.getAllRestaurant());
    }
}

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistem.restaurant.dtos.reservation.NewReservationDto;
import sistem.restaurant.dtos.reservation.ReservationDto;
import sistem.restaurant.services.ReservationService;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController
{
    @Autowired
    private ReservationService reservationService;

    @PostMapping("/{userName}/{tableNumber}/{restaurantName}")
    public ResponseEntity<ReservationDto> createReservation(@PathVariable String userName, @PathVariable int tableNumber,
                                                            @PathVariable String restaurantName,
                                                            @RequestBody NewReservationDto newReservationDto)
    {
        return ResponseEntity.ok(reservationService.createReservation(userName, tableNumber, restaurantName, newReservationDto));
    }

    @DeleteMapping("/{clientName}")
    public ResponseEntity<Boolean> deleteReservation(@PathVariable String clientName)
    {
        return ResponseEntity.ok(reservationService.deleteReservation(clientName));
    }

    @GetMapping("")
    public ResponseEntity<List<ReservationDto>> getAllReservations()
    {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }
}

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistem.restaurant.dtos.menuItem.MenuItemDto;
import sistem.restaurant.dtos.menuItem.NewMenuItemDto;
import sistem.restaurant.services.MenuService;

import java.util.List;

@RestController
@RequestMapping("/menusItems")
public class MenuItemController
{
    @Autowired
    private MenuService menuService;

    @PostMapping("CreateItem/{nameC}")
    public ResponseEntity<MenuItemDto> createMenuItem(@PathVariable String nameC, @RequestBody NewMenuItemDto newMenuItemDto)
    {
        return ResponseEntity.ok(menuService.createMenuItem(nameC, newMenuItemDto));
    }

    @PutMapping("EditItem/{iName}/{mCategory}")
    public ResponseEntity<MenuItemDto> updateMenuItem(@PathVariable String iName, @PathVariable String mCategory,
                                                      @RequestBody NewMenuItemDto newMenuItemDto)
    {
        return ResponseEntity.ok(menuService.updateMenuItem(iName, mCategory, newMenuItemDto));
    }

    @GetMapping("GetItems/{categ}")
    public ResponseEntity<List<MenuItemDto>> getAllItems(@PathVariable String categ)
    {
        return ResponseEntity.ok(menuService.getAllItems(categ));
    }

    @DeleteMapping("DeleteItems/{itemN}")
    public ResponseEntity<Boolean> deleteItem(@PathVariable String itemN)
    {
        return ResponseEntity.ok(menuService.deleteMenuItem(itemN));
    }
}

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistem.restaurant.dtos.menu.MenuDto;
import sistem.restaurant.dtos.menu.NewMenuDto;
import sistem.restaurant.dtos.menuItem.MenuItemDto;
import sistem.restaurant.dtos.menuItem.NewMenuItemDto;
import sistem.restaurant.entities.Menu;
import sistem.restaurant.services.MenuService;

import java.util.List;

@RestController
@RequestMapping("/menus")
public class MenuController
{
    @Autowired
    private MenuService menuService;

    @PostMapping("/{name}")
    public ResponseEntity<MenuDto> createMenu(@PathVariable String name, @RequestBody NewMenuDto newMenuDto)
    {
        return ResponseEntity.ok(menuService.createMenu(name, newMenuDto));
    }

    @PutMapping("/{restaurantName}/{menuCategory}")
    public ResponseEntity<MenuDto> updateMenu(@PathVariable String restaurantName, @PathVariable String menuCategory,
            @RequestBody NewMenuDto menuDto)
    {
        MenuDto updatedMenu = menuService.updateMenu(menuCategory, restaurantName, menuDto);
        return ResponseEntity.ok(updatedMenu);
    }

    @DeleteMapping("/{menuCategory}")
    public ResponseEntity<Boolean> deleteMenu(@PathVariable String menuCategory)
    {
        return ResponseEntity.ok(menuService.deleteMenu(menuCategory));
    }

    @GetMapping("")
    public ResponseEntity<List<MenuDto>> getAllMenus()
    {
        return ResponseEntity.ok(menuService.getAllMenus());
    }
}
    * */

}
