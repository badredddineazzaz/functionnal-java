package com.example.projetfx.Models;

import java.util.List;

public class Ingredient {
    private String name;
    private String amount;
    private String unit;
    private List<Ingredient> nestedIngredients;
    private List<String> steps;

    public Ingredient(String name, String amount, String unit) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }

    public Ingredient(String name, String amount, String unit, List<Ingredient> nestedIngredients,List<String> steps) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.nestedIngredients = nestedIngredients;
        this.steps = steps;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (nestedIngredients !=null){
            sb.append(String.format("%s{\n",name));
            for (Ingredient ing: nestedIngredients) {
                sb.append(String.format("\t\t%s\n",ing));
            }
            sb.append("\t}\n");
        }else{
            sb.append(String.format("%s %s of %s\n",amount,unit,name));
        }
        return sb.toString();
    }

    /************************************************/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        if (amount=="*"){
            return 0.0;
        }else{
            return Double.parseDouble(amount);
        }
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<Ingredient> getNestedIngredients() {
        return nestedIngredients;
    }

    public void setNestedIngredients(List<Ingredient> nestedIngredients) {
        this.nestedIngredients = nestedIngredients;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }
}
