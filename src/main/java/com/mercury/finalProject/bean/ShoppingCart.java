//package com.mercury.finalProject.bean;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name = "SHOPPING_CART")
//public class ShoppingCart {
//
//    @Id
//    @SequenceGenerator(name = "SHOPPING_CART_SEQ_GEN", sequenceName = "SHOPPING_CART_SEQ", allocationSize = 1)
//    @GeneratedValue(generator="SHOPPING_CART_SEQ_GEN", strategy = GenerationType.AUTO)
//    private int id;
//
//    @Column
//    private int userId;
//
//    @Column
//    private int quantity;
//
//    @Column
//    private double totalAmount;
//
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
//    @JoinTable(
//            name = "SHOPPING_CART_DISH",
//            joinColumns = {
//                    @JoinColumn(name = "SHOPPING_CART_ID", referencedColumnName = "ID")
//            },
//            inverseJoinColumns = {
//                    @JoinColumn(name = "DISH_ID", referencedColumnName = "ID")
//            }
//    )
//    private List<Dish> dishes;
//
//    public ShoppingCart() {
//    }
//
//    public ShoppingCart(int id, int userId, int quantity, double totalAmount, List<Dish> dishes) {
//        this.id = id;
//        this.userId = userId;
//        this.quantity = quantity;
//        this.totalAmount = totalAmount;
//        this.dishes = dishes;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    public double getTotalAmount() {
//        return totalAmount;
//    }
//
//    public void setTotalAmount(double totalAmount) {
//        this.totalAmount = totalAmount;
//    }
//
//    public List<Dish> getDishes() {
//        return dishes;
//    }
//
//    public void setDishes(List<Dish> dishes) {
//        this.dishes = dishes;
//    }
//
//    @Override
//    public String toString() {
//        return "ShoppingCart{" +
//                "id=" + id +
//                ", userId=" + userId +
//                ", quantity=" + quantity +
//                ", totalAmount=" + totalAmount +
//                ", dishes=" + dishes +
//                '}';
//    }
//}
