package com.personnalize_design.meals.data.model;

public class MealBilanModel {

    private String mealName;
    private String mealQuantity;
    private String mealPrice;

    public MealBilanModel(String mealName, String mealQuantity, String mealPrice) {
        this.mealName = mealName;
        this.mealQuantity = mealQuantity;
        this.mealPrice = mealPrice;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealQuantity() {
        return mealQuantity;
    }

    public void setMealQuantity(String mealQuantity) {
        this.mealQuantity = mealQuantity;
    }

    public String getMealPrice() {
        return mealPrice;
    }

    public void setMealPrice(String mealPrice) {
        this.mealPrice = mealPrice;
    }
}
