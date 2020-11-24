import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private ArrayList<Pizza> pizzas;

    public Menu() {


    }
    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void addPizza(Pizza pizzaToAdd){
        pizzas.add(pizzaToAdd);
    }

    public void removePizza(Pizza pizzaToRemove) {
        pizzas.remove(pizzaToRemove);
    }

    @Override
    public String toString() {
        String tempString = "";
        for (Pizza pizza:pizzas) {
            tempString+=pizza + "\n";
        }
        return tempString;
    }
}