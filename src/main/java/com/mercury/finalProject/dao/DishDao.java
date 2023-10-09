package com.mercury.finalProject.dao;

import com.mercury.finalProject.bean.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishDao extends JpaRepository<Dish, Integer> {
}
