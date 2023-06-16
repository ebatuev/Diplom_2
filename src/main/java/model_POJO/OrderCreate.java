package model_POJO;

import java.util.ArrayList;


// Cоздать класс OrderCreate
public class OrderCreate {
    private ArrayList<String> ingredients; // список целых ингредиентов


    // Конструктор с параметрами
    public OrderCreate(ArrayList<String> ingredients) {

        this.ingredients = ingredients;
    }


    // геттер для списка ingredients
    public ArrayList<String> getIngredients() {

        return ingredients;
    }

    // сеттер для списка ingredients
    public void setIngredients(ArrayList<String> ingredients) {

        this.ingredients = ingredients;
    }

}
