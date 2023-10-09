package com.mercury.finalProject.controller;

import com.mercury.finalProject.bean.Menu;
import com.mercury.finalProject.bean.Restaurant;
import com.mercury.finalProject.http.Response;
import com.mercury.finalProject.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Restaurant> saveRestaurant(@RequestBody Restaurant restaurant){
        System.out.println("Saving: " + restaurant);
        return restaurantService.saveRestaurant(restaurant);
    }

    @DeleteMapping("/{id}")
    public Response deleteRestaurantById(@PathVariable int id){
        System.out.println("Deleting restaurant: " + id);
        return restaurantService.deleteRestaurantById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurantById(@PathVariable int id, @RequestBody Restaurant restaurant){
        Restaurant oldRestaurant = restaurantService.getRestaurantById(id);
        if(oldRestaurant != null){
            restaurant.setId(id);
            return restaurantService.saveRestaurant(restaurant);
        }else {
            throw new RuntimeException("Restaurant not found.");
        }
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable int id){
        return restaurantService.getRestaurantById(id);
    }

    @GetMapping("/name/{name}")
    public Restaurant getRestaurantByName(@PathVariable String name){
        return restaurantService.getRestaurantByName(name);
    }

    @GetMapping("/restaurantCategory/{restaurantCategory}")
    public List<Restaurant> getRestaurantsByRestaurantCategory(@PathVariable String restaurantCategory){
        return restaurantService.getRestaurantsByRestaurantCategory(restaurantCategory);
    }

    @GetMapping
    public List<Restaurant> getAllRestaurants(){
        return restaurantService.getAllRestaurants();
    }

}
