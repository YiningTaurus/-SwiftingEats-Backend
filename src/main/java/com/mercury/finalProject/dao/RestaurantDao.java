package com.mercury.finalProject.dao;

import com.mercury.finalProject.bean.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantDao extends JpaRepository<Restaurant, Integer> {
    Restaurant findRestaurantByName(String name);

    List<Restaurant> findRestaurantByRestaurantCategory(String restaurantCategory);
}
