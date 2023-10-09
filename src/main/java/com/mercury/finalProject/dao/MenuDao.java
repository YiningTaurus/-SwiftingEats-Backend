package com.mercury.finalProject.dao;

import com.mercury.finalProject.bean.Menu;
import com.mercury.finalProject.bean.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuDao extends JpaRepository<Menu, Integer> {

    Menu findMenuByName(String name);

    List<Menu> findMenusByMenuCategory(String menuCategory);

    List<Menu> findMenusByRestaurantId(int restaurantId);

    List<Menu> findMenusByRestaurant(Restaurant restaurant);
}
