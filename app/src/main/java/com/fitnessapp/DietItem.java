package com.fitnessapp;
public class DietItem {
    private int id;
    private String foodName;
    private String category;
    private int calories;
    private int greenPercentage;
    private String imageUrl;

    public DietItem(int id, String foodName, String category, int calories, int greenPercentage, String imageUrl) {
        this.id = id;
        this.foodName = foodName;
        this.category = category;
        this.calories = calories;
        this.greenPercentage = greenPercentage;
        this.imageUrl = imageUrl;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getGreenPercentage() {
        return greenPercentage;
    }

    public void setGreenPercentage(int greenPercentage) {
        this.greenPercentage = greenPercentage;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}