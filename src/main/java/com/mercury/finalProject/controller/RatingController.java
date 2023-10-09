package com.mercury.finalProject.controller;

import com.mercury.finalProject.bean.Rating;
import com.mercury.finalProject.http.Response;
import com.mercury.finalProject.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping
    public List<Rating> getAllRatings() {
        return ratingService.getAllRatings();
    }

    @GetMapping("/{ratingId}")
    public Rating getRatingById(@PathVariable int ratingId) {
        return ratingService.getRatingByRatingId(ratingId);
    }

    @GetMapping("/order/{orderId}")
    public Rating getRatingByOrderId(@PathVariable int orderId) {
        return ratingService.getRatingByOrderId(orderId);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Rating>> getRatingsByRestaurantId(@PathVariable("restaurantId") int restaurantId) {
        List<Rating> ratings = ratingService.getRatingsByRestaurantId(restaurantId);
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/restaurantAvg/{restaurantId}")
    public double getAverageRatingForRestaurant(@PathVariable int restaurantId) {
        return ratingService.getAverageRatingForRestaurant(restaurantId);
    }

    @PostMapping
    public ResponseEntity<Rating> saveRating(@RequestBody Rating rating) {
        System.out.println("Saving: " + rating);
        return ratingService.saveRating(rating);
    }

    @PutMapping("/{ratingId}")
    public ResponseEntity<Rating> updateRating(@PathVariable int ratingId, @RequestBody Rating rating) {
        Rating oldRating = ratingService.getRatingByRatingId(ratingId);
        if(oldRating != null){
            rating.setId(ratingId);
            return ratingService.saveRating(rating);
        }else{
            throw new RuntimeException("Rating not found.");
        }
    }

    @DeleteMapping("/{ratingId}")
    public Response deleteRating(@PathVariable int ratingId) {
        System.out.println("Deleting FlavorCategory: " + ratingId);
        return ratingService.deleteRatingById(ratingId);
    }

}
