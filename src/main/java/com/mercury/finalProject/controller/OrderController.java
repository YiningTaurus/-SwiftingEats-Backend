package com.mercury.finalProject.controller;

import com.mercury.finalProject.bean.Order;
import com.mercury.finalProject.bean.Restaurant;
import com.mercury.finalProject.http.Response;
import com.mercury.finalProject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_WEB_ADMIN')")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyAuthority('ROLE_CUSTOMER', 'ROLE_RESTAURANT_MANAGER')")
    public List<Order> getOrdersByUserId(@PathVariable int userId) {
        return orderService.getOrdersByUserId(userId);
    }

    @GetMapping("/{orderId}")
    @PreAuthorize("hasAnyAuthority('ROLE_CUSTOMER', 'ROLE_RESTAURANT_MANAGER')")
    public Order getOrderByOrderId(@PathVariable int orderId) {
        return orderService.getOrderByOrderId(orderId);
    }

    @GetMapping("/user/{userId}/order/{orderId}")
    @PreAuthorize("hasAnyAuthority('ROLE_CUSTOMER', 'ROLE_RESTAURANT_MANAGER')")
    public Order getOrderByOrderIdAndUserId(
            @PathVariable("userId") int userId,
            @PathVariable("orderId") int orderId) {
        return orderService.getOrderByOrderIdAndUserId(userId, orderId).orElse(null);
    }

    @PostMapping("/restaurant")
    @PreAuthorize("hasAnyAuthority('ROLE_CUSTOMER', 'ROLE_RESTAURANT_MANAGER')")
    public List<Order> getOrdersByRestaurant(@RequestBody Restaurant restaurant) {
        return orderService.getOrdersByRestaurant(restaurant);
    }

    @GetMapping("/restaurant/{restaurantId}")
    @PreAuthorize("hasAnyAuthority('ROLE_CUSTOMER', 'ROLE_RESTAURANT_MANAGER')")
    public List<Order> getOrdersByRestaurantId(@PathVariable int restaurantId) {
        return orderService.getOrdersByRestaurantId(restaurantId);
    }

    @PostMapping
    public Response saveOrder(@RequestBody Order order) {
        System.out.println(order);
        return orderService.saveOrder(order);
    }

    @PutMapping("/{orderId}")
    public Response updateOrder(@PathVariable int orderId, @RequestBody Order order) {
        order.setId(orderId);
        return orderService.updateOrder(order);
    }

}
