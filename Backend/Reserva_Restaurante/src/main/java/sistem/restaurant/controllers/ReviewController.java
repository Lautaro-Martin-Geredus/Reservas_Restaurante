package sistem.restaurant.controllers;

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
