package com.mercury.finalProject.service;

import com.mercury.finalProject.bean.Dish;
import com.mercury.finalProject.bean.Order;
import com.mercury.finalProject.bean.Restaurant;
import com.mercury.finalProject.dao.DishDao;
import com.mercury.finalProject.dao.OrderDao;
import com.mercury.finalProject.dao.RestaurantDao;
import com.mercury.finalProject.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private DishDao dishDao;

    @Autowired
    private RestaurantDao restaurantDao;

    public List<Order> getAllOrders() {
        return orderDao.findAll();
    }

    public List<Order> getOrdersByUserId(int userId) {
        return orderDao.findByUserId(userId);
    }

    public Order getOrderByOrderId(int orderId) {
        return orderDao.findById(orderId).orElse(null);
    }

    public Optional<Order> getOrderByOrderIdAndUserId(int userId, int orderId){
        return orderDao.findByUserIdAndId(userId, orderId);
    }

    public List<Order> getOrdersByRestaurant(Restaurant restaurant){
        return orderDao.findOrdersByRestaurant(restaurant);
    }

    public List<Order> getOrdersByRestaurantId(int restaurantId){
        return orderDao.findOrdersByRestaurantId(restaurantId);
    }


    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Response saveOrder(Order order) {
        System.out.println(order.getOrderTime().toString());
        // Spring transaction will automatically roll back if exception out.
        try {
            orderDao.save(order);
            return new Response(true, "Order saving successfully.");
        } catch (Exception e) {
            return new Response(false, "Failed to save the order.");
        }
    }


    public Response updateOrder(Order order) {
        try {
            Order oldOrder = orderDao.findById(order.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + order.getId()));

            List<Dish> newDishes = order.getDishes();
            newDishes.forEach((dish) -> {
                Dish dishInDb = dishDao.findById(dish.getId())
                        .orElseThrow(() -> new IllegalArgumentException("Dish not found with ID: " + dish.getId()));
                dish.setFlavors(dishInDb.getFlavors());
            });
            oldOrder.setDishes(newDishes);
            oldOrder.setTotalAmount(order.getTotalAmount());
            oldOrder.setAddress(order.getAddress());
            oldOrder.setPhone(order.getPhone());
            oldOrder.setOrderTime(order.getOrderTime());
            oldOrder.setStatus(order.getStatus());
            oldOrder.setRemark(order.getRemark());
            orderDao.save(oldOrder);
            return new Response(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Response(false);
        }
    }
}
