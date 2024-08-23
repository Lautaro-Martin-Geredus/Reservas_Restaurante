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
