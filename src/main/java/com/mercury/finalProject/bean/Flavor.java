package com.mercury.finalProject.bean;

import javax.persistence.*;

@Entity
@Table(name = "FLAVOR")
public class Flavor {

    @Id
    @SequenceGenerator(name = "DISH_SEQ_GEN", sequenceName = "DISH_SEQ", allocationSize = 1)
    @GeneratedValue(generator="DISH_SEQ_GEN", strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String info;

    @Column
    private double price;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "FLAVOR_CATEGORY_ID")
    private FlavorCategory flavorCategory;

    @Column(name = "RESTAURANT_ID")
    private int restaurantId;

    public Flavor() {
    }

    public Flavor(int id, String info, double price, FlavorCategory flavorCategory, int restaurantId) {
        this.id = id;
        this.info = info;
        this.price = price;
        this.flavorCategory = flavorCategory;
        this.restaurantId = restaurantId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public FlavorCategory getFlavorCategory() {
        return flavorCategory;
    }

    public void setFlavorCategory(FlavorCategory flavorCategory) {
        this.flavorCategory = flavorCategory;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "Flavor{" +
                "id=" + id +
                ", info='" + info + '\'' +
                ", price=" + price +
                ", flavorCategory=" + flavorCategory +
                ", restaurantId=" + restaurantId +
                '}';
    }
}
