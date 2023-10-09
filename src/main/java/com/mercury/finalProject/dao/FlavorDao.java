package com.mercury.finalProject.dao;

import com.mercury.finalProject.bean.Flavor;
import com.mercury.finalProject.bean.FlavorCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlavorDao extends JpaRepository<Flavor, Integer> {

    Flavor findFlavorByInfo(String info);

    List<Flavor> findFlavorsByFlavorCategoryId(int flavorCategoryId);

    List<Flavor> findFlavorsByFlavorCategory(FlavorCategory flavorCategory);

    List<Flavor> findFlavorsByRestaurantId(int restaurantId);
}
