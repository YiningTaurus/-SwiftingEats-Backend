package com.mercury.finalProject.service;

import com.mercury.finalProject.bean.Rating;
import com.mercury.finalProject.dao.RatingDao;
import com.mercury.finalProject.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingDao ratingDao;

    public List<Rating> getAllRatings() {
        return ratingDao.findAll();
    }

    public Rating getRatingByRatingId(int ratingId) {
        return ratingDao.findById(ratingId).orElse(null);
    }

    public Rating getRatingByOrderId(int orderId) {
        return ratingDao.findByOrderId(orderId).orElse(null);
    }

    public List<Rating> getRatingsByRestaurantId(int restaurantId){
        return ratingDao.findByRestaurantId(restaurantId);
    }

    public double getAverageRatingForRestaurant(int restaurantId) {
        List<Rating> ratings = ratingDao.findByRestaurantId(restaurantId);
        if (ratings.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        for (Rating rating : ratings) {
            sum += rating.getRate();
        }
        return sum / ratings.size();
    }

    public ResponseEntity<Rating> saveRating(Rating rating) {
        try{
            Rating savedRating = ratingDao.save(rating);
            return new ResponseEntity<>(savedRating, HttpStatus.OK);
        }catch (Exception e){
            throw new RuntimeException("Rating saving failed." + e.getMessage());
        }
    }

    public Response deleteRatingById(int ratingId) {
        try{
            ratingDao.deleteById(ratingId);
            return new Response(true, "Rating deleted successfully!");
        }catch (Exception e){
            throw new RuntimeException("Rating deleted failed." + e.getMessage());
        }
    }

}
