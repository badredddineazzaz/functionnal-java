package com.example.projetfx;


import com.example.projetfx.Models.Main;
import com.example.projetfx.Models.Recepie;
import com.example.projetfx.Repositories.RecepieRepo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

public class HelloController implements Initializable {

    RecepieRepo recepieRepo = new RecepieRepo();

    @FXML
    private ComboBox comboText;

    @FXML
    private Button searchBtn;

    @FXML
    private TextArea textArea;

    @FXML
    void serachItems(ActionEvent event) {

        int selectedItem = comboText.getSelectionModel().getSelectedIndex();
        String result = null;
                
//        List<String> result = null;
        double resultDouble = 0;
        List<Recepie> resultRecepie=null;
        switch(selectedItem) {
            case 0:
                result = recepieRepo.listRecipeTitles().toString();
                break;
            case 1:
                result = Double.toString(recepieRepo.calculateTotalEggsUsed());
                break;
            case 2:
                result = recepieRepo.getRecipesUsingOliveOil().toString();
                break;
            case 3:
                result = recepieRepo.calculateEggsPerRecipe().toString();
                break;
            case 4:
                result = recepieRepo.getLowCalorieRecipes().toString();
                break;
            case 5:
                result = Double.toString(recepieRepo.getTotalSugarForZuppaInglese());
                break;
            case 6:
                result = recepieRepo.getFirstTwoSteps("Zuppa Inglese");
                break;
            case 7:
                result = recepieRepo.getRecipesWithMoreThan5Steps().toString();
                break;
            case 8:
                result = recepieRepo.getRecipesWithoutButter().toString();
                break;
            case 9:
                result = "Not done";
                break;
            case 10:
                result = recepieRepo.getMostCaloricRecipe().toString();
                break;
            case 11:
                result = recepieRepo.getMostFrequentUnit();
                break;
            case 12:
                result = recepieRepo.calculateIngredientsCountByRecipe().toString();
                break;
            case 13:
                result = recepieRepo.getRecipeWithMostFat().toString();
                break;
            case 14:
                result =  "not done";
                break;
            case 15:
                result = recepieRepo.getRecipesSortedByIngredients();
                break;
            case 16:
                result = recepieRepo.getRecipesByIngredient().toString();
                break;
            case 17:
                result = recepieRepo.getRecipeStepDistribution().toString();
                break;
            case 18:
                result = recepieRepo.getEasiestRecipe().toString();
                break;
            default:
                System.out.println("Number out of range.");
                break;
        }
        textArea.setText(String.valueOf(result));


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList("lister les titres des recettes","le nombre total d’œufs utilisés.","les recettes utilisant l’huile d’olive","le nombre d’œufs par recette.","les recettes fournissant moins de 500 calories.","la quantité de sucre utilisée par la recette Zuppa Inglese","les 2 premières étapes de la recette Zuppa Inglese","les recettes qui nécessitent plus de 5 étapes","es recettes qui ne contiennent pas de beure","les recettes ayant des ingrédients en communs avec la recette Zuppa Inglese.","la recette la plus calorique.","l’unité la plus fréquente","le nombre d’ingrédients par recette","la recette comportant le plus de fat","l’ingrédient le plus utilisé","les recettes triées par nombre d’ingrédients.","affiche pour chaque ingrédient, les recettes qui l’utilisent.","la répartition des recettes par étape de réalisation","la recette la plus facile (avec le moins d’étape)");
                comboText.setItems(list);
        try {
            recepieRepo = Main.init();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }
}
