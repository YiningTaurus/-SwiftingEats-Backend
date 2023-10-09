//package com.mercury.finalProject.controller;
//
//import com.mercury.finalProject.bean.Dish;
//import com.mercury.finalProject.bean.ShoppingCart;
//import com.mercury.finalProject.http.Response;
//import com.mercury.finalProject.service.ShoppingCartService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/shoppingCart")
//public class ShoppingCartController {
//
//    @Autowired
//    private ShoppingCartService shoppingCartService;
//
//    @PostMapping
//    public Response saveShoppingCart(@RequestBody ShoppingCart shoppingCart) {
//        System.out.println("Saving: " + shoppingCart);
//        return shoppingCartService.saveShoppingCart(shoppingCart);
//    }
//
//    @DeleteMapping("/{id}")
//    public Response deleteShoppingCartById(@PathVariable int id) {
//        System.out.println("Deleting shoppingCart: " + id);
//        return shoppingCartService.deleteShoppingCartById(id);
//    }
//
//    @GetMapping("/{id}")
//    public ShoppingCart getShoppingCartById(@PathVariable int id) {
//        return shoppingCartService.getShoppingCartById(id);
//    }
//
//    @GetMapping("/userId/{userId}")
//    public ShoppingCart getShoppingCartByUserId(@PathVariable("userId") int userId) {
//        return shoppingCartService.getShoppingCartByUserId(userId);
//    }
//
//    @PostMapping("/{shoppingCartId}/dish")
//    public Response addDishToShoppingCart(@PathVariable("shoppingCartId") int shoppingCartId, @RequestBody Dish dish) {
//        return shoppingCartService.addDishToShoppingCart(shoppingCartId, dish);
//    }
//
//    @DeleteMapping("/{shoppingCartId}/dish/{dishId}")
//    public Response deleteDishFromShoppingCart(@PathVariable("shoppingCartId") int shoppingCartId, @PathVariable("dishId") int dishId) {
//        return shoppingCartService.deleteDishFromShoppingCart(shoppingCartId, dishId);
//    }
//
//    @PutMapping("/{shoppingCartId}/dish")
//    public Response updateDishInShoppingCart(@PathVariable("shoppingCartId") int shoppingCartId, @RequestBody Dish updatedDish) {
//        return shoppingCartService.updateDishInShoppingCart(shoppingCartId, updatedDish);
//    }
//
//    @GetMapping("/{shoppingCartId}/dish")
//    public List<Dish> getAllDishesByShoppingCartId(@PathVariable("shoppingCartId") int shoppingCartId) {
//        return shoppingCartService.getAllDishesByShoppingCartId(shoppingCartId);
//    }
//
//}
