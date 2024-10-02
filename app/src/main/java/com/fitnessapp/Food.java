package com.fitnessapp;

public class Food {
    private String description;
    private int calories;
    // Add other properties like protein, fat, carbohydrates, etc. as needed

    public Food() {
        // Default constructor
    }

    public Food(String description, int calories) {
        this.description = description;
        this.calories = calories;
    }

    // Getters and setters for description and calories
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    // Add getters and setters for other properties as needed
}