package com.mercury.finalProject.service;

import com.mercury.finalProject.bean.Flavor;
import com.mercury.finalProject.bean.FlavorCategory;
import com.mercury.finalProject.dao.FlavorDao;
import com.mercury.finalProject.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlavorService {

    @Autowired
    private FlavorDao flavorDao;

    public ResponseEntity<Flavor> saveFlavor(Flavor flavor) {
        try {
            Flavor savedFlavor = flavorDao.save(flavor);
            return new ResponseEntity<>(savedFlavor, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Flavor saving failed. " + e.getMessage());
        }
    }

    public Response deleteFlavorById(int id) {
        try {
            flavorDao.deleteById(id);
            return new Response(true, "Flavor deleting successfully!");
        } catch (Exception e) {
            throw new RuntimeException("Flavor deleting failed. " + e.getMessage());
        }
    }

    public Flavor getFlavorById(int id) {
        return flavorDao.findById(id).orElse(null);
    }

    public Flavor getFlavorByInfo(String info) {
         return flavorDao.findFlavorByInfo(info);
    }

    public List<Flavor> getFlavorsByFlavorCategoryId(int flavorCategoryId){
        return flavorDao.findFlavorsByFlavorCategoryId(flavorCategoryId);
    }

    public List<Flavor> getFlavorsByRestaurantId(int restaurantId){
        return flavorDao.findFlavorsByRestaurantId(restaurantId);
    }

    public List<Flavor> getFlavorsByFlavorCategory(FlavorCategory flavorCategory){
        return flavorDao.findFlavorsByFlavorCategory(flavorCategory);
    }

    public List<Flavor> getAllFlavors() {
        return flavorDao.findAll();
    }
}
