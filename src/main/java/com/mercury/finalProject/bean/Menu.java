package com.mercury.finalProject.bean;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "MENU")
public class Menu {

    @Id
    @SequenceGenerator(name = "MENU_SEQ_GEN", sequenceName = "MENU_SEQ", allocationSize = 1)
    @GeneratedValue(generator="MENU_SEQ_GEN", strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant;

    @Column
    private String name;

    @Column
    private double price;

    @Column
    private String image;

    @Column
    private String description;

    @Column(name = "ISAVAILABLE")
    private int isAvailable;

    @Column
    private String menuCategory;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable(
            name = "MENU_FLAVOR",
            joinColumns = {
                    @JoinColumn(name = "MENU_ID", referencedColumnName = "ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "FLAVOR_ID", referencedColumnName = "ID")
            }
    )
    private List<Flavor> flavors;

    public Menu() {
    }

    public Menu(int id, Restaurant restaurant, String name, double price, String image, String description, int isAvailable, String menuCategory, List<Flavor> flavors) {
        this.id = id;
        this.restaurant = restaurant;
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
        this.isAvailable = isAvailable;
        this.menuCategory = menuCategory;
        this.flavors = flavors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsAvailable() {
        return isAvailable == 1;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable ? 1 : 0;
    }

    public String getMenuCategory() {
        return menuCategory;
    }

    public void setMenuCategory(String menuCategory) {
        this.menuCategory = menuCategory;
    }

    public List<Flavor> getFlavors() {
        return flavors;
    }

    public void setFlavors(List<Flavor> flavors) {
        this.flavors = flavors;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", restaurant=" + restaurant +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", isAvailable=" + isAvailable +
                ", menuCategory='" + menuCategory + '\'' +
                ", flavors=" + flavors +
                '}';
    }
}
