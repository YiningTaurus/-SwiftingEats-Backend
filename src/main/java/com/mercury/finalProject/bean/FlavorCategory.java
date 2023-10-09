package com.mercury.finalProject.bean;

import javax.persistence.*;

@Entity
@Table(name = "FLAVOR_CATEGORY")
public class FlavorCategory {

    @Id
    @SequenceGenerator(name = "FLAVOR_CATEGORY_SEQ_GEN", sequenceName = "FLAVOR_CATEGORY_SEQ", allocationSize = 1)
    @GeneratedValue(generator="FLAVOR_CATEGORY_SEQ_GEN", strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String type;

    @Column(name = "ISSINGLETON")
    private int isSingleton;

    @Column(name = "ISMANDATORY")
    private int isMandatory;

    @Column(name = "RESTAURANT_ID")
    private int restaurantId;

    public FlavorCategory() {
    }

    public FlavorCategory(int id, String type, int isSingleton, int isMandatory, int restaurantId) {
        this.id = id;
        this.type = type;
        this.isSingleton = isSingleton;
        this.isMandatory = isMandatory;
        this.restaurantId = restaurantId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getIsSingleton() {
        return isSingleton == 1;
    }

    public void setIsSingleton(boolean isSingleton) {
        this.isSingleton = isSingleton ? 1 : 0;
    }

    public boolean getIsMandatory() {
        return isMandatory == 1;
    }

    public void setIsMandatory(boolean isMandatory) {
        this.isMandatory = isMandatory ? 1 : 0;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "FlavorCategory{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", isSingleton=" + isSingleton +
                ", isMandatory=" + isMandatory +
                ", restaurantId=" + restaurantId +
                '}';
    }
}
