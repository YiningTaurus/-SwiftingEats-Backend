package com.mercury.finalProject.bean;

import javax.persistence.*;

@Entity
@Table(name = "RATING")
public class Rating {

    @Id
    @SequenceGenerator(name = "RATING_SEQ_GEN", sequenceName = "RATING_SEQ", allocationSize = 1)
    @GeneratedValue(generator="RATING_SEQ_GEN", strategy = GenerationType.AUTO)
    private int id;

    @Column
    private int orderId;

    @Column
    private int userId;

    @Column
    private float rate;

    @Column
    private String comments;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant;

    public Rating() {
    }

    public Rating(int id, int orderId, int userId, float rate, String comments, Restaurant restaurant) {
        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
        this.rate = rate;
        this.comments = comments;
        this.restaurant = restaurant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", userId=" + userId +
                ", rate=" + rate +
                ", comments='" + comments + '\'' +
                ", restaurant=" + restaurant +
                '}';
    }
}
