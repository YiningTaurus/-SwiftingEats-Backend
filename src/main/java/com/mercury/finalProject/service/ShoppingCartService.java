//package com.mercury.finalProject.service;
//
//import com.mercury.finalProject.bean.Dish;
//import com.mercury.finalProject.bean.Menu;
//import com.mercury.finalProject.bean.ShoppingCart;
//import com.mercury.finalProject.dao.ShoppingCartDao;
//import com.mercury.finalProject.http.Response;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class ShoppingCartService {
//
//    @Autowired
//    private ShoppingCartDao shoppingCartDao;
//
//    public Response saveShoppingCart(ShoppingCart shoppingCart){
//        try {
//            shoppingCartDao.save(shoppingCart);
//            return new Response(true, "ShoppingCart saving successfully!");
//        }catch (Exception e){
//            return new Response(false, "ShoppingCart saving failed. " + e.getMessage());
//        }
//    }
//
//    public Response deleteShoppingCartById(int id){
//        try {
//            shoppingCartDao.deleteById(id);
//            return new Response(true, "ShoppingCart deleting successfully!");
//        }catch (Exception e){
//            return new Response(false, "ShoppingCart deleting failed. " + e.getMessage());
//        }
//    }
//
//    public ShoppingCart getShoppingCartById(int id){
//        return shoppingCartDao.findById(id).orElse(null);
//    }
//
//    public ShoppingCart getShoppingCartByUserId(int userId){
//        return shoppingCartDao.findShoppingCartByUserId(userId);
//    }
//
//    public Response addDishToShoppingCart(int shoppingCartId, Dish dish){
//        ShoppingCart shoppingCart = getShoppingCartById(shoppingCartId);
//        if(shoppingCart == null){
//            return new Response(false, "ShoppingCart not found.");
//        }
//        List<Dish> dishes = shoppingCart.getDishes();
//        dishes.add(dish);
//        shoppingCart.setDishes(dishes);
//        shoppingCart.setQuantity(shoppingCart.getQuantity() + 1);
//        shoppingCart.setTotalAmount(shoppingCart.getTotalAmount() + dish.getPrice());
//        try{
//            shoppingCartDao.save(shoppingCart);
//            return new Response(true, "Dish added to shopping cart successfully.");
//        }catch (Exception e){
//            return new Response(false, "Failed to add dish to shopping cart: " + e.getMessage());
//        }
//    }
//
//    public Response deleteDishFromShoppingCart(int shoppingCartId, int dishId){
//        ShoppingCart shoppingCart = getShoppingCartById(shoppingCartId);
//        if (shoppingCart == null) {
//            return new Response(false, "Shopping cart not found.");
//        }
//        List<Dish> dishes = shoppingCart.getDishes();
//        boolean isFound = false;
//        double removedDishPrice = 0;
//        for (int i = 0; i < dishes.size(); i++) {
//            Dish currentDish = dishes.get(i);
//            if (currentDish.getId() == dishId) {
//                removedDishPrice = currentDish.getPrice();
//                dishes.remove(i);
//                shoppingCart.setDishes(dishes);
//                shoppingCart.setTotalAmount(shoppingCart.getTotalAmount() - removedDishPrice);
//                isFound = true;
//                break;
//            }
//        }
//        if (!isFound) {
//            return new Response(false, "Dish not found in shopping cart.");
//        }
//        try {
//            shoppingCartDao.save(shoppingCart);
//            return new Response(true, "Dish removed from shopping cart successfully.");
//        } catch (Exception e) {
//            return new Response(false, "Failed to remove dish from shopping cart: " + e.getMessage());
//        }
//    }
//
//    public Response updateDishInShoppingCart(int shoppingCartId, Dish updatedDish){
//        ShoppingCart shoppingCart = getShoppingCartById(shoppingCartId);
//        if(shoppingCart == null){
//            return new Response(false, "ShoppingCart not found.");
//        }
//        List<Dish> dishes = shoppingCart.getDishes();
//        boolean isFound = false;
//        double oldPrice = 0;
//        for(int i = 0; i < dishes.size(); i++){
//            Dish oldDish = dishes.get(i);
//            if(oldDish.getId() == updatedDish.getId()){
//                oldPrice = oldDish.getPrice();
//                dishes.set(i, updatedDish);
//                shoppingCart.setDishes(dishes);
//                shoppingCart.setTotalAmount(shoppingCart.getTotalAmount() - oldPrice + updatedDish.getPrice());
//                isFound = true;
//                break;
//            }
//        }
//        if(!isFound){
//            return new Response(false, "Dish not found in shopping cart.");
//        }
//        try {
//            shoppingCartDao.save(shoppingCart);
//            return new Response(true, "Dish updated in shopping cart successfully.");
//        } catch (Exception e) {
//            return new Response(false, "Failed to update dish in shopping cart: " + e.getMessage());
//        }
//    }
//
//    public List<Dish> getAllDishesByShoppingCartId(int shoppingCartId) {
//        ShoppingCart shoppingCart = getShoppingCartById(shoppingCartId);
//        if (shoppingCart == null) {
//            return new ArrayList<>();
//        }else{
//            return shoppingCart.getDishes();
//        }
//    }
//
//}
