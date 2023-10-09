package com.mercury.finalProject.service;

import com.mercury.finalProject.bean.Restaurant;
import com.mercury.finalProject.dao.RestaurantDao;
import com.mercury.finalProject.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantDao restaurantDao;

    public ResponseEntity<Restaurant> saveRestaurant(Restaurant restaurant){
        try {
            Restaurant savedRestaurant = restaurantDao.save(restaurant);
            return new ResponseEntity<>(savedRestaurant, HttpStatus.OK);
        }catch (Exception e){
            throw new RuntimeException("Restaurant saving failed. " + e.getMessage());
        }
    }

    public Response deleteRestaurantById(int id){
        try {
            restaurantDao.deleteById(id);
            return new Response(true, "Restaurant deleting successfully!");
        }catch (Exception e){
            throw new RuntimeException("Restaurant deleting failed. " + e.getMessage());
        }
    }

    public Restaurant getRestaurantById(int id){
        return restaurantDao.findById(id).orElse(null);
    }

    public Restaurant getRestaurantByName(String name){
        return restaurantDao.findRestaurantByName(name);
    }

    public List<Restaurant> getRestaurantsByRestaurantCategory(String restaurantCategory){
        return restaurantDao.findRestaurantByRestaurantCategory(restaurantCategory);
    }

    public List<Restaurant> getAllRestaurants(){
        return restaurantDao.findAll();
    }

}
