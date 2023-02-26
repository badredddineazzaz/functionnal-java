package com.example.projetfx.Models;

import com.example.projetfx.Repositories.RecepieRepo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static RecepieRepo init() throws ParserConfigurationException, IOException, SAXException {
        File xmlFile = new File("./src/recipes.xml");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();

        NodeList recipeNodes = doc.getElementsByTagName("rcp:recipe");
        RecepieRepo recipeRepo = new RecepieRepo();

        for (int i = 0; i < recipeNodes.getLength(); i++) {
            Element recipeElement = (Element) recipeNodes.item(i);

            String id = recipeElement.getAttribute("id");
            String title = recipeElement.getElementsByTagName("rcp:title").item(0).getTextContent();
            String date = recipeElement.getElementsByTagName("rcp:date").item(0).getTextContent();

            /********************Ingredients********************/
            List<Ingredient> ingredients = new ArrayList<>();
            NodeList ingredientNodes = recipeElement.getElementsByTagName("rcp:ingredient");
            for (int j = 0; j < ingredientNodes.getLength(); j++) {
                Element ingredientElement = (Element) ingredientNodes.item(j);
                String name = ingredientElement.getAttribute("name");
                String amount = ingredientElement.getAttribute("amount");
                String unit = ingredientElement.getAttribute("unit");
                String text = ingredientElement.getTextContent().trim();

                if (text.isEmpty()) {
                    ingredients.add(new Ingredient(name, amount, unit));
                } else {
                    List<Ingredient> nestedIngredients = new ArrayList<>();
                    NodeList nestedIngredientNodes = ingredientElement.getElementsByTagName("rcp:ingredient");
                    for (int k = 0; k < nestedIngredientNodes.getLength(); k++) {
                        Element nestedIngredientElement = (Element) nestedIngredientNodes.item(k);
                        String nestedName = nestedIngredientElement.getAttribute("name");
                        String nestedAmount = nestedIngredientElement.getAttribute("amount");
                        String nestedUnit = nestedIngredientElement.getAttribute("unit");
                        nestedIngredients.add(new Ingredient(nestedName, nestedAmount, nestedUnit));
                    }
                    /**steps**/
                    NodeList prepNode = ingredientElement.getElementsByTagName("rcp:preparation");
                    List<String> stepsList = new ArrayList<>();
                    for (int h = 0; h < prepNode.getLength(); h++) {
                        Element stepElement = (Element) prepNode.item(h);
                        String step = stepElement.getElementsByTagName("rcp:step").item(0).getTextContent();
                        stepsList.add(step);
                    }
                    /**steps**/
                    Ingredient ingredient = new Ingredient(name, amount, unit, nestedIngredients, stepsList);

                    ingredients.add(ingredient);
                }
            }

            /********************Preparation********************/
            NodeList prepNode = recipeElement.getElementsByTagName("rcp:preparation");
            List<String> stepsList = new ArrayList<>();
            for (int j = 0; j < prepNode.getLength(); j++) {
                Element stepElement = (Element) prepNode.item(j);
                String step = stepElement.getElementsByTagName("rcp:step").item(0).getTextContent();
                stepsList.add(step);
            }

            /*************************Nutririon*********************/
            Element nutritionElement = (Element) recipeElement.getElementsByTagName("rcp:nutrition").item(0);
            String calories = nutritionElement.getAttribute("calories");
            String fat = nutritionElement.getAttribute("fat");
            String carbs = nutritionElement.getAttribute("carbohydrates");
            String protein = nutritionElement.getAttribute("protein");
            Nutrition nutrition = new Nutrition(calories, fat, carbs, protein);

            /*************************Add Recepie*******************/
            Recepie recipe = new Recepie(id, title, date, ingredients, stepsList, nutrition);
            recipeRepo.addRecipe(recipe);
        }

        return recipeRepo;
    }
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        RecepieRepo recepieRepo;
        recepieRepo = init();
        recepieRepo.listRecipeTitles();


        System.out.println("---------------------------------Total eggs-------------------------");
        System.out.println(recepieRepo.calculateTotalEggsUsed());
        System.out.println("---------------------------------Olive Oil-------------------------");
        System.out.println(recepieRepo.getRecipesUsingOliveOil());
        System.out.println("---------------------------------Eggs in Recepie-------------------------");
        System.out.println(recepieRepo.calculateEggsPerRecipe());
        System.out.println("---------------------------------Less than 500 calories-------------------------");
        System.out.println(recepieRepo.getLowCalorieRecipes());
        System.out.println("---------------------------------getTotalSugarForZuppaInglese-------------------------");
        System.out.println(recepieRepo.getTotalSugarForZuppaInglese());
        System.out.println("---------------------------------2 first steps-------------------------");
        System.out.println(recepieRepo.getFirstTwoSteps("Zuppa Inglese")); /********error******/
        System.out.println("---------------------------------More tha 5 steps-------------------------");
        System.out.println(recepieRepo.getRecipesWithMoreThan5Steps());
        System.out.println("---------------------------------No butter-------------------------");
        System.out.println(recepieRepo.getRecipesWithoutButter());

        System.out.println("---------------------------------Commun Ingredients-------------------------");
        System.out.println(recepieRepo.getRecipesWithoutButter());/***error****/

        System.out.println("---------------------------------Most calories-------------------------");
        System.out.println(recepieRepo.getMostCaloricRecipe());

        System.out.println("---------------------------------Most Units used-------------------------");
        System.out.println(recepieRepo.getMostFrequentUnit());

        System.out.println("---------------------------------Calculate ingredients-------------------------");
        System.out.println(recepieRepo.calculateIngredientsCountByRecipe());

        System.out.println("---------------------------------Most fat-------------------------");
        System.out.println(recepieRepo.getRecipeWithMostFat());

        System.out.println("---------------------------------Most used ingredient-------------------------");
        System.out.println(recepieRepo.getRecipeWithMostFat());/*****error********/

        System.out.println("---------------------------------Sorted by ingredients-------------------------");
        System.out.println(recepieRepo.getRecipesSortedByIngredients());

        System.out.println("---------------------------------ingredient and its recepies-------------------------");
        System.out.println(        recepieRepo.getRecipesByIngredient());

        System.out.println("---------------------------------Distrubition-------------------------");
        System.out.println(        recepieRepo.getRecipeStepDistribution());

        System.out.println("---------------------------------Easiest Recepie-------------------------");
        System.out.println(        recepieRepo.getEasiestRecipe());








    }


}