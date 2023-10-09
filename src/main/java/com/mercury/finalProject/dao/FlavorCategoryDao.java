package com.mercury.finalProject.dao;

import com.mercury.finalProject.bean.FlavorCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlavorCategoryDao extends JpaRepository<FlavorCategory, Integer> {

    FlavorCategory findByType(String type);

    List<FlavorCategory> findByRestaurantId(int restaurantId);

}
