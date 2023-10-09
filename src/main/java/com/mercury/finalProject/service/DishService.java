package com.mercury.finalProject.service;

import com.mercury.finalProject.bean.Dish;
import com.mercury.finalProject.bean.Flavor;
import com.mercury.finalProject.bean.Menu;
import com.mercury.finalProject.dao.DishDao;
import com.mercury.finalProject.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DishService {

    @Autowired
    private DishDao dishDao;

    @Autowired
    private MenuService menuService;

    public Response createDishFromMenuAndFlavors(int menuId, List<Flavor> flavors) {
        Menu menu = menuService.getMenuById(menuId);
        if(menu == null) {
            return new Response(false, "Menu not found with ID: " + menuId);
        }
        double totalPrice = menu.getPrice();
        for (Flavor flavor : flavors) {
            totalPrice += flavor.getPrice();
        }
        Dish dish = new Dish();
        dish.setMenu(menu);
        dish.setFlavors(flavors);
        dish.setPrice(totalPrice);
        return saveDish(dish);
    }

    public Response saveDish(Dish dish) {
        try {
            dishDao.save(dish);
            return new Response(true, "Dish saving successfully!");
        } catch (Exception e) {
            return new Response(false, "Dish saving failed. " + e.getMessage());
        }
    }

    public Response deleteDishById(int id) {
        try {
            dishDao.deleteById(id);
            return new Response(true, "Dish deleting successfully!");
        } catch (Exception e) {
            return new Response(false, "Dish deleting failed. " + e.getMessage());
        }
    }

    public Dish getDishById(int id) {
        return dishDao.findById(id).orElse(null);
    }

    public Response addFlavorToDish(int dishId, Flavor flavor){
        Dish dish = getDishById(dishId);
        if (dish == null) {
            return new Response(false, "Dish not found with ID: " + dishId);
        }
        List<Flavor> flavors = dish.getFlavors();
        flavors.add(flavor);
        dish.setFlavors(flavors);
        try {
            dishDao.save(dish);
            return new Response(true, "Flavor added to dish successfully.");
        } catch (Exception e) {
            return new Response(false, "Failed to add flavor to dish: " + e.getMessage());
        }
    }

    public Response deleteFlavorFromDish(int dishId, int flavorId){
        Dish dish = getDishById(dishId);
        if (dish == null) {
            return new Response(false, "Dish not found with ID: " + dishId);
        }
        List<Flavor> flavors = dish.getFlavors();
        boolean isFound = false;
        for (int i = 0; i < flavors.size(); i++) {
            if (flavors.get(i).getId() == flavorId) {
                flavors.remove(i);
                dish.setFlavors(flavors);
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            return new Response(false, "Flavor not found with ID: " + flavorId);
        }
        try {
            dishDao.save(dish);
            return new Response(true, "Flavor removed from dish successfully.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to remove flavor from dish: " + e.getMessage());
        }
    }

    public Response updateFlavorInDish(int dishId, Flavor updatedFlavor){
        Dish dish = getDishById(dishId);
        if (dish == null) {
            return new Response(false, "Dish not found with ID: " + dishId);
        }
        List<Flavor> flavors = dish.getFlavors();
        boolean isFound = false;
        for (int i = 0; i < flavors.size(); i++) {
            Flavor oldFlavor = flavors.get(i);
            if (oldFlavor.getId() == updatedFlavor.getId()) {
                flavors.set(i, updatedFlavor);
                dish.setFlavors(flavors);
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            return new Response(false, "Flavor not found in dish.");
        }
        try {
            dishDao.save(dish);
            return new Response(true, "Flavor updated in dish successfully.");
        } catch (Exception e) {
            return new Response(false, "Failed to update flavor in dish: " + e.getMessage());
        }
    }

    public List<Flavor> getAllFlavorsByDishId(int dishId){
        Dish dish = getDishById(dishId);
        if(dish == null){
            return null;
        }
        return dish.getFlavors();
    }

}
