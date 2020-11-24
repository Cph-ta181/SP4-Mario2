public class Order {

import java.util.List;
import java.util.GregorianCalendar;


    public class Order{

        private  List<Pizza> pizzas;
        private  GregorianCalendar datetime;
        private  Boolean paid;

        public Order(List<Pizza> pizzas, GregorianCalendar datetime, Boolean paid) {
            this.pizzas = pizzas;
            this.datetime = datetime;
            this.paid = paid;
        }

        public List<Pizza> getPizzas() {
            return pizzas;
        }

        public GregorianCalendar getDatetime() {
            return datetime;
        }

        public Boolean getPaid() {
            return paid;
        }

        public int totalPrice(){
            int totalPrice = 0;
            for (Pizza pizza:pizzas) {
                totalPrice+=pizza.getPrice();

            }

            return totalPrice;
        }


        public void addPizza(Pizza pizzaToAdd){
            pizzas.add(pizzaToAdd);
        }

        public void removePizza(Pizza pizzaToRemove){

            pizzas.remove(pizzaToRemove);
        }

        @Override
        public String toString() {
            String tempString = "Order";
            for (Pizza pizza: pizzas){
                tempString += "\n"+ pizza.getNumber();
            }
            tempString += "\nPris: "+totalPrice();
            tempString += "\nDato: "+datetime;
            if (paid){
                tempString += "Er betalt";
            }
            else{
                tempString += "Er ikke betalt\n";
            }
            return tempString;
        }
    }
}
