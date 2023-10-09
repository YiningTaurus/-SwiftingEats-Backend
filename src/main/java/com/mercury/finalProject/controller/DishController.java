package com.mercury.finalProject.controller;

import com.mercury.finalProject.bean.Dish;
import com.mercury.finalProject.bean.Flavor;
import com.mercury.finalProject.http.Response;
import com.mercury.finalProject.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @PostMapping("/create")
    public Response createDishFromMenuAndFlavors(@RequestParam int menuId, @RequestBody List<Flavor> flavors) {
        return dishService.createDishFromMenuAndFlavors(menuId, flavors);
    }

    @PostMapping
    public Response saveDish(@RequestBody Dish dish) {
        System.out.println("Saving: " + dish);
        return dishService.saveDish(dish);
    }

    @DeleteMapping("/{id}")
    public Response deleteDishById(@PathVariable int id) {
        System.out.println("Deleting shoppingCart: " + id);
        return dishService.deleteDishById(id);
    }

    @GetMapping("/{id}")
    public Dish getDishById(@PathVariable int id) {
        return dishService.getDishById(id);
    }

    @PostMapping("/{dishId}/flavor")
    public Response addFlavorToDish(@PathVariable int dishId, @RequestBody Flavor flavor) {
        return dishService.addFlavorToDish(dishId, flavor);
    }

    @DeleteMapping("/{dishId}/flavor/{flavorId}")
    public Response deleteFlavorFromDish(@PathVariable int dishId, @PathVariable int flavorId) {
        return dishService.deleteFlavorFromDish(dishId, flavorId);
    }

    @PutMapping("/{dishId}/flavor")
    public Response updateFlavorInDish(@PathVariable int dishId, @RequestBody Flavor updatedFlavor) {
        return dishService.updateFlavorInDish(dishId, updatedFlavor);
    }

    @GetMapping("/{dishId}/flavor")
    public List<Flavor> getAllFlavorsByDishId(@PathVariable int dishId) {
        return dishService.getAllFlavorsByDishId(dishId);
    }


}
