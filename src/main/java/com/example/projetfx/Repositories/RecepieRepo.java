package com.example.projetfx.Repositories;

import com.example.projetfx.Models.Ingredient;
import com.example.projetfx.Models.Recepie;

import java.util.*;
import java.util.stream.Collectors;

public class RecepieRepo {
    private static List<Recepie> recipeList;

    public RecepieRepo() {
        recipeList = new ArrayList<>();
    }

    public void addRecipe(Recepie recipe) {
        recipeList.add(recipe);
    }

    public static List<Recepie> getRecipeList() {
        return recipeList;
    }


    /*****************Ex4****************/
    public List<String> listRecipeTitles() {
        return recipeList.stream()
                .map(Recepie::getTitle)
                .map(title -> title + System.lineSeparator())
                .collect(Collectors.toList());
    }


    /*****************Ex5****************/
    public static double calculateTotalEggsUsed() {
        return getRecipeList().stream()
                .flatMap(recipe -> recipe.getIngredients().stream())
                .filter(ingredient -> ingredient.getName().contains("egg"))
                .mapToDouble(Ingredient::getAmount)
                .sum();
    }
    /*****************Ex6****************/
    public List<Recepie> getRecipesUsingOliveOil() {
        return getRecipeList().stream()
                .filter(recipe -> recipe.getIngredients()
                        .stream()
                        .anyMatch(ingredient -> ingredient.getName().equalsIgnoreCase("olive oil")))
                .collect(Collectors.toList());
    }
    /*****************Ex7****************/
    public static Map<String, Double> calculateEggsPerRecipe() {
        return getRecipeList().stream()
                .collect(Collectors.toMap(Recepie::getTitle, recipe -> recipe.getIngredients().stream()
                        .filter(ingredient -> ingredient.getName().contains("egg"))
                        .mapToDouble(Ingredient::getAmount)
                        .sum()));
    }
    /*****************Ex8****************/
    public List<Recepie> getLowCalorieRecipes() {
        return getRecipeList().stream()
                .filter(recipe -> recipe.getNutrition().getCalories() < 500)
                .collect(Collectors.toList());
    }

    /*****************Ex9****************/
    public static double getTotalSugarForZuppaInglese() {
        return getRecipeList().stream()
                .filter(recipe -> recipe.getTitle().equals("Zuppa Inglese"))
                .flatMap(recipe -> recipe.getIngredients().stream())
                .filter(ingredient -> ingredient.getName().equals("sugar"))
                .mapToDouble(Ingredient::getAmount)
                .sum();
    }

    /*****************Ex10****************/
    public String getFirstTwoSteps(String recipeName) {
        return recipeList.stream()
                .filter(recipe -> recipe.getTitle().equals(recipeName))
                .limit(1)
                .flatMap(recipe -> recipe.getSteps().stream().limit(2))
                .collect(Collectors.joining(System.lineSeparator()));
    }



    /*****************Ex11****************/

    public List<Recepie> getRecipesWithMoreThan5Steps() {
        return getRecipeList().stream()
                .filter(recipe -> recipe.getSteps().size() > 5)
                .collect(Collectors.toList());
    }

    /*****************Ex12****************/

    public static List<Recepie> getRecipesWithoutButter() {
        return getRecipeList().stream()
                .filter(recipe -> recipe.getIngredients().stream()
                        .noneMatch(ingredient -> ingredient.getName().toLowerCase().equals("butter")))
                .collect(Collectors.toList());
    }

    /*****************Ex13****************/



    /*****************Ex14****************/
    public Recepie getMostCaloricRecipe() {
        return getRecipeList().stream()
                .max(Comparator.comparingDouble(recipe -> recipe.getNutrition().getCalories()))
                .orElse(null);
    }
    /*****************Ex15****************/
    public String getMostFrequentUnit() {
        Map<String, Long> unitCountMap = getRecipeList().stream()
                .flatMap(recipe -> recipe.getIngredients().stream())
                .collect(Collectors.groupingBy(Ingredient::getUnit, Collectors.counting()));

        return Collections.max(unitCountMap.entrySet(), Map.Entry.comparingByValue())
                .getKey();
    }
    /*****************Ex16****************/
    public static Map<String, Integer> calculateIngredientsCountByRecipe() {
        return getRecipeList().stream()
                .collect(Collectors.toMap(Recepie::getTitle, recipe -> recipe.getIngredients().size()));
    }
    /*****************Ex17****************/
    public Recepie getRecipeWithMostFat() {
        return getRecipeList().stream()
                .max(Comparator.comparingDouble(r -> Double.parseDouble(r.getNutrition().getFat().replace("%",""))))
                .orElse(null);
    }
    /*****************Ex18****************/


    /*****************Ex19****************/

    public static String getRecipesSortedByIngredients() {
        Comparator<Recepie> byIngredientCount = Comparator.comparingInt(r -> r.getIngredients().size());
        return getRecipeList().stream()
                .sorted(byIngredientCount)
                .map(Recepie::getTitle)
                .collect(Collectors.joining(System.lineSeparator()));
    }



    /*****************Ex20****************/

    public static Map<String, List<String>> getRecipesByIngredient() {
        Map<String, List<String>> ingredientsMap = new HashMap<>();
        for (Recepie recepie : getRecipeList()) {
            for (Ingredient ingredient : recepie.getIngredients()) {
                String ingredientName = ingredient.getName();
                List<String> recipeNames = ingredientsMap.computeIfAbsent(ingredientName, k -> new ArrayList<>());
                recipeNames.add(recepie.getTitle());
            }
        }
        return ingredientsMap;
    }


    /*****************Ex21****************/

    public Map<String, Integer> getRecipeStepDistribution() {
        Map<String, Integer> stepCounts = new HashMap<>();

        getRecipeList().stream()
                .flatMap(recipe -> recipe.getSteps().stream())
                .forEach(step -> stepCounts.compute(step, (k, v) -> (v == null) ? 1 : v + 1));

        return stepCounts;
    }

    /*****************Ex22****************/
    public Recepie getEasiestRecipe() {
        return getRecipeList().stream()
                .min(Comparator.comparingInt(recipe -> recipe.getSteps().size()))
                .orElse(null);
    }


















    @Override
    public String toString() {
        return recipeList.toString();
    }
}
