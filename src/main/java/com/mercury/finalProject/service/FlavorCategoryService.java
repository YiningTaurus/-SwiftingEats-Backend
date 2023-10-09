package com.mercury.finalProject.service;

import com.mercury.finalProject.bean.FlavorCategory;
import com.mercury.finalProject.dao.FlavorCategoryDao;
import com.mercury.finalProject.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlavorCategoryService {

    @Autowired
    private FlavorCategoryDao flavorCategoryDao;

//    public Response saveFlavorCategory(FlavorCategory flavorCategory){
//        try {
//            flavorCategoryDao.save(flavorCategory);
//            return new Response(true, "FlavorCategory saved successfully!");
//        }catch (Exception e) {
//            return new Response(false, "FlavorCategory saving failed. " + e.getMessage());
//        }
//    }

    public ResponseEntity<FlavorCategory> saveFlavorCategory(FlavorCategory flavorCategory){
        try {
            FlavorCategory savedFlavorCategory = flavorCategoryDao.save(flavorCategory);
            return new ResponseEntity<>(savedFlavorCategory, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("FlavorCategory saving failed. " + e.getMessage());
        }
    }

    public Response deleteFlavorCategoryById(int id) {
        try {
            flavorCategoryDao.deleteById(id);
            return new Response(true, "FlavorCategory deleted successfully!");
        } catch (Exception e) {
            throw new RuntimeException("FlavorCategory deleted failed!" + e.getMessage());
        }
    }

    public FlavorCategory getFlavorCategoryById(int id) {
        return flavorCategoryDao.findById(id).orElse(null);
    }

    public FlavorCategory getFlavorCategoryByType(String type) {
        return flavorCategoryDao.findByType(type);
    }

    public List<FlavorCategory> getFlavorCategoriesByRestaurantId(int restaurantId){
        return flavorCategoryDao.findByRestaurantId(restaurantId);
    }

    public List<FlavorCategory> getAllFlavorCategories() {
        return flavorCategoryDao.findAll();
    }

}
