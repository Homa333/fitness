package com.consult.fitness;

import androidx.annotation.NonNull;

public class items {
    private String item_name, price, imageURL;

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public items(String item_name, String price, String imageURL) {
        this.item_name = item_name;
        this.price = price;
        this.imageURL = imageURL;
    }

    @NonNull
    @Override
    public String toString() {
        return "Category{" +
                "Name='"+ item_name + '\'' +
                ", price='" + price + '\''+
                ", imageUrl='" + imageURL + '\''+
                '}';
    }
}
