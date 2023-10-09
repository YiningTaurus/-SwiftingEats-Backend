package com.mercury.finalProject.bean;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "DISH")
public class Dish {

    @Id
    @SequenceGenerator(name = "DISH_SEQ_GEN", sequenceName = "DISH_SEQ", allocationSize = 1)
    @GeneratedValue(generator="DISH_SEQ_GEN", strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "MENU_ID")
    private Menu menu;

    @Column
    private double price;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "DISH_FLAVOR",
            joinColumns = {
                    @JoinColumn(name = "DISH_ID", referencedColumnName = "ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "FLAVOR_ID", referencedColumnName = "ID")
            }
    )
    private List<Flavor> flavors;

    public Dish() {
    }

    public Dish(int id, Menu menu, double price, List<Flavor> flavors) {
        this.id = id;
        this.menu = menu;
        this.price = price;
        this.flavors = flavors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<Flavor> getFlavors() {
        return flavors;
    }

    public void setFlavors(List<Flavor> flavors) {
        this.flavors = flavors;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", menu=" + menu +
                ", price=" + price +
                ", flavors=" + flavors +
                '}';
    }
}
