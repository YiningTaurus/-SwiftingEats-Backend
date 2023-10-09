package com.mercury.finalProject.bean;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @SequenceGenerator(name = "ORDERS_SEQ_GEN", sequenceName = "ORDERS_SEQ", allocationSize = 1)
    @GeneratedValue(generator="ORDERS_SEQ_GEN", strategy = GenerationType.AUTO)
    private int id;

    @Column
    private double totalAmount;

    @Column(name = "USER_ID")
    private int userId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant;

    @Column
    private String address;

    @Column
    private String phone;

    @Column(name = "ORDER_TIME")
    private Date orderTime;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Column
    private String remark;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "ORDER_DETAIL",
            joinColumns = {
                    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "DISH_ID", referencedColumnName = "ID")
            }
    )
    private List<Dish> dishes;

    public Order() {
    }

    public Order(int id, double totalAmount, int userId, Restaurant restaurant, String address, String phone, Date orderTime, Status status, String remark, List<Dish> dishes) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.userId = userId;
        this.restaurant = restaurant;
        this.address = address;
        this.phone = phone;
        this.orderTime = orderTime;
        this.status = status;
        this.remark = remark;
        this.dishes = dishes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = Status.fromValue(status);
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", totalAmount=" + totalAmount +
                ", userId=" + userId +
                ", restaurant=" + restaurant +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", orderTime=" + orderTime +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", dishes=" + dishes +
                '}';
    }
}
