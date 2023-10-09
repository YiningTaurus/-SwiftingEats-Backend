package com.mercury.finalProject.dao;

import com.mercury.finalProject.bean.Order;
import com.mercury.finalProject.bean.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderDao extends JpaRepository<Order, Integer> {

    List<Order> findByUserId(int userId);
    Optional<Order> findByUserIdAndId(int userId, int orderId);
    List<Order> findOrdersByRestaurant(Restaurant restaurant);
    List<Order> findOrdersByRestaurantId(int restaurantId);

}
