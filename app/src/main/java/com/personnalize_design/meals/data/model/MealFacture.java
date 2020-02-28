package com.personnalize_design.meals.data.model;

public class MealFacture {

    private String mealImage;
    private String mealName;
    private String mealPrice;
    private String mealQuantity;

    public MealFacture(String mealImage, String mealName, String mealPrice, String mealQuantity) {
        this.mealImage = mealImage;
        this.mealName = mealName;
        this.mealPrice = mealPrice;
        this.mealQuantity = mealQuantity;
    }

    public String getMealImage() {
        return mealImage;
    }

    public void setMealImage(String mealImage) {
        this.mealImage = mealImage;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealPrice() {
        return mealPrice;
    }

    public void setMealPrice(String mealPrice) {
        this.mealPrice = mealPrice;
    }

    public String getMealQuantity() {
        return mealQuantity;
    }

    public void setMealQuantity(String mealQuantity) {
        this.mealQuantity = mealQuantity;
    }
}
