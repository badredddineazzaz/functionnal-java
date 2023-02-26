package com.example.projetfx.Models;

public class Nutrition {
    private String calories;
    private String fat;
    private String carbohydrates;
    private String protein;

    public Nutrition(String calories, String fat, String carbohydrates, String protein) {
        this.calories = calories;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.protein = protein;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("\tCalories: %s\n",calories));
        sb.append(String.format("\tFat: %s\n",fat));
        sb.append(String.format("\tCarbohydrates: %s\n",carbohydrates));
        sb.append(String.format("\tProtein: %s\n",protein));

        return sb.toString();
    }


    public Integer getCalories() {
        return Integer.parseInt(calories);
    }

    public String  getFat() {
        return fat;
    }

    public String getCarbohydrates() {
        return carbohydrates;
    }

    public String getProtein() {
        return protein;
    }
}
