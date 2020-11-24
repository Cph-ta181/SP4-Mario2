
public class Pizza {


    private String pizzaName;
    private int number;
    private int price;

    public Pizza(int number, int price, String pizzaName) {
        this.pizzaName = pizzaName;
        this.number = number;
        this.price = price;
    }

    //public Pizza(String s, int price, int parseInt) {} - se bort fra dette.

    public String getPizzaName() {

        return pizzaName;
    }

    public int getNumber() {

        return number;
    }


    public int getPrice() {

        return price;
    }

    @Override
    public String toString() {
        return "Pizza: " + pizzaName + " Nr: " + number + " Pris: " + price;
    }

}