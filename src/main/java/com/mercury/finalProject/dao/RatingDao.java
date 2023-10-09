package com.mercury.finalProject.dao;

import com.mercury.finalProject.bean.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingDao extends JpaRepository<Rating, Integer> {
    Optional<Rating> findByOrderId(int orderId);
    List<Rating> findByRestaurantId(int restaurantId);
}
