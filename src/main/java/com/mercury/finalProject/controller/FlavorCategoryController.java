package com.mercury.finalProject.controller;

import com.mercury.finalProject.bean.FlavorCategory;
import com.mercury.finalProject.http.Response;
import com.mercury.finalProject.service.FlavorCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flavorCategory")
public class FlavorCategoryController {

    @Autowired
    private FlavorCategoryService flavorCategoryService;

    @PostMapping
    public ResponseEntity<FlavorCategory> saveFlavorCategory(@RequestBody FlavorCategory flavorCategory) {
        System.out.println("Saving: " + flavorCategory);
        return flavorCategoryService.saveFlavorCategory(flavorCategory);
    }

    @DeleteMapping("/{id}")
    public Response deleteFlavorCategoryById(@PathVariable int id) {
        System.out.println("Deleting FlavorCategory: " + id);
        return flavorCategoryService.deleteFlavorCategoryById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlavorCategory> updateFlavorCategoryById(@PathVariable int id, @RequestBody FlavorCategory flavorCategory) {
        FlavorCategory oldFlavorCategory = flavorCategoryService.getFlavorCategoryById(id);
        if (oldFlavorCategory != null) {
            flavorCategory.setId(id);
            return flavorCategoryService.saveFlavorCategory(flavorCategory);
        } else {
            throw new RuntimeException("FlavorCategory not found.");
        }
    }

    @GetMapping("/{id}")
    public FlavorCategory getFlavorCategoryById(@PathVariable int id) {
        return flavorCategoryService.getFlavorCategoryById(id);
    }

    @GetMapping("/type/{type}")
    public FlavorCategory getFlavorCategoryByType(@PathVariable String type) {
        return flavorCategoryService.getFlavorCategoryByType(type);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<FlavorCategory>> getFlavorCategoriesByRestaurantId(@PathVariable int restaurantId) {
        List<FlavorCategory> flavorCategories = flavorCategoryService.getFlavorCategoriesByRestaurantId(restaurantId);
        return ResponseEntity.ok(flavorCategories);
    }

    @GetMapping
    public List<FlavorCategory> getAllFlavorCategories() {
        return flavorCategoryService.getAllFlavorCategories();
    }

}
