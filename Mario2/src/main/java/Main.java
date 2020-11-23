public class Main {
    public static void main(String[] args){
        Menu menu = new Menu();
        PizzaShop shop = new PizzaShop(menu);

        shop.getOrders();
    }
}
