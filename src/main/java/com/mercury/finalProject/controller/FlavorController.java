package com.mercury.finalProject.controller;

import com.mercury.finalProject.bean.Flavor;
import com.mercury.finalProject.bean.FlavorCategory;
import com.mercury.finalProject.http.Response;
import com.mercury.finalProject.service.FlavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flavor")
public class FlavorController {

    @Autowired
    private FlavorService flavorService;

    @PostMapping
    public ResponseEntity<Flavor> saveFlavor(@RequestBody Flavor flavor) {
        System.out.println("Saving: " + flavor);
        return flavorService.saveFlavor(flavor);
    }

    @DeleteMapping("{id}")
    public Response deleteFlavorById(@PathVariable int id) {
        System.out.println("Deleting flavor: " + id);
        return flavorService.deleteFlavorById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flavor> updateFlavorById(@PathVariable int id, @RequestBody Flavor flavor){
        Flavor oldFlavor = flavorService.getFlavorById(id);
        if(oldFlavor != null){
            flavor.setId(id);
            return flavorService.saveFlavor(flavor);
        }else{
            throw new RuntimeException("Flavor not found.");
        }
    }

    @GetMapping("/{id}")
    public Flavor getFlavorById(@PathVariable int id) {
        return flavorService.getFlavorById(id);
    }

     @GetMapping("/info/{info}")
     public Flavor getFlavorByInfo(@PathVariable String info) {
         return flavorService.getFlavorByInfo(info);
     }

    @GetMapping("/category/{flavorCategoryId}")
    public List<Flavor> getFlavorsByFlavorCategoryId(@PathVariable int flavorCategoryId) {
        return flavorService.getFlavorsByFlavorCategoryId(flavorCategoryId);
    }

    @PostMapping("/category")
    public List<Flavor> getFlavorsByFlavorCategory(@RequestBody FlavorCategory flavorCategory) {
        return flavorService.getFlavorsByFlavorCategory(flavorCategory);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Flavor>> getFlavorsByRestaurantId(@PathVariable int restaurantId) {
        List<Flavor> flavors = flavorService.getFlavorsByRestaurantId(restaurantId);
        return ResponseEntity.ok(flavors);
    }

    @GetMapping
    public List<Flavor> getAllFlavors() {
        return flavorService.getAllFlavors();
    }

}
