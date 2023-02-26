package com.example.projetfx.Models;

import java.util.List;

public class Recepie {
    private String id;
    private String title;
    private String date;
    private List<Ingredient> ingredients;
    private List<String> steps;
    private Nutrition nutrition;

    public Recepie(String id, String title, String date, List<Ingredient> ingredients, List<String> steps, Nutrition nutrition) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.ingredients = ingredients;
        this.steps = steps;
        this.nutrition = nutrition;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Name: %s\n",title));
        sb.append(String.format("Id: %s\n",id));
        sb.append(String.format("Date: %s\n",date));
        sb.append(String.format("Ingredient: \n"));
        for (Ingredient ing: ingredients) {
            sb.append(String.format("\t-%s",ing));
        }
        sb.append(String.format("Steps: \n"));
        for (String step: steps) {
            sb.append(String.format("\t-%s",step));
        }
        sb.append(String.format("Nutrition: \n"));
        sb.append(String.format("%s: \n",nutrition));

        return sb.toString();


    }



    /*****************Getters Setter******************/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }
}


