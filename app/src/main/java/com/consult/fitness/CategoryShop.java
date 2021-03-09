package com.consult.fitness;

import androidx.annotation.NonNull;

public class CategoryShop {
    private String cate_Name, imageUrl;

    public CategoryShop(String cate_Name, String imageUrl) {
        this.cate_Name = cate_Name;
        this.imageUrl = imageUrl;
    }

    public String getCate_Name() {
        return cate_Name;
    }

    public void setCate_Name(String cate_Name) {
        this.cate_Name = cate_Name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NonNull
    @Override
    public String toString() {
        return "Category{" +
                "Name='"+ cate_Name + '\'' +
                ", imageUrl='" + imageUrl + '\''+
                '}';
    }
}
