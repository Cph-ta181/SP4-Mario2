import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private ArrayList<Pizza> pizzas;


    public Menu() {
        readPizzasFromDB();
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public void addPizza(Pizza pizzaToAdd){
        pizzas.add(pizzaToAdd);
    }

    public void removePizza(Pizza pizzaToRemove) {
        pizzas.remove(pizzaToRemove);
    }

    private ArrayList<Pizza> readPizzasFromDB(){
        pizzas = new ArrayList<Pizza>();
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/pizzaShop", "root", "root123");
            Statement preSta = con.createStatement();
            ResultSet res = preSta.executeQuery("Select * from pizza order by pizzaNumber;");
            while (res.next()) {
                Pizza pizzaToAdd = new Pizza(res.getInt(1), res.getInt(3), res.getString(2));
                pizzas.add(pizzaToAdd);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pizzas;
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