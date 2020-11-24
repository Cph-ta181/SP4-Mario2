import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MenuKort {
    private ArrayList<Pizza> pizzas;

    public Menu(String filename) {
        pizzas = new ArrayList<Pizza>();
        File file = new File(filename);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            int counter = 1;
            Pizza pizza;
            while((line = br.readLine()) != null) {
                String[] lineArr = line.split(",");
                pizza = new Pizza (lineArr[2], Integer.parseInt(lineArr[0]), Integer.parseInt(lineArr[1]));
                pizzas.add(pizza);
                counter++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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