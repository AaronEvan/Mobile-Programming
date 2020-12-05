package com.aaronevan.binusezyfoody.binusclass;

import java.io.Serializable;
import java.util.ArrayList;

public class Drink implements Serializable {
    private int image;
    private String name;
    private int price;
    private int quantity;

    public Drink(int image, String name, int price) {
        this.image = image;
        this.name = name;
        this.price = price;
    }

    public Drink(int image, String name, int price, int quantity) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
