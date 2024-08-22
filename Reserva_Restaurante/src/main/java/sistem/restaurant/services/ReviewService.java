package sistem.restaurant.services;

import org.springframework.stereotype.Service;
import sistem.restaurant.dtos.review.NewReviewDto;
import sistem.restaurant.dtos.review.ReviewDto;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface ReviewService
{
    List<ReviewDto> getAllReviews();

    ReviewDto createReview(String userName, String restaurantName, NewReviewDto newReviewDto);
}
