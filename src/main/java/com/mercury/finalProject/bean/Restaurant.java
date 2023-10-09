package com.mercury.finalProject.bean;

import javax.persistence.*;

@Entity
@Table(name = "RESTAURANT")
public class Restaurant {
    @Id
    @SequenceGenerator(name = "RESTAURANT_SEQ_GEN", sequenceName = "RESTAURANT_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "RESTAURANT_SEQ_GEN", strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @Column
    private String image;

    @Column
    private String restaurantCategory;

    public Restaurant() {
    }

    public Restaurant(int id, String name, String image, String restaurantCategory) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.restaurantCategory = restaurantCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRestaurantCategory() {
        return restaurantCategory;
    }

    public void setRestaurantCategory(String restaurantCategory) {
        this.restaurantCategory = restaurantCategory;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", restaurantCategory='" + restaurantCategory + '\'' +
                '}';
    }
}
