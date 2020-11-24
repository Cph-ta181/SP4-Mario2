import java.util.*;

public class UIController {
    Scanner sc = new Scanner(System.in);
    Menu menu = new Menu("jdbc:mysql://localhost/pizzaShop");
    PizzaShop MariosPizzaBar = new PizzaShop(menu);


    public void runApp() {
        int run = 0;
        boolean running = true;
        while (running) {
            System.out.println("1.) Tilføj bestilling");
            System.out.println("2.) Fjern bestilling");
            System.out.println("3.) Omsætning for denne måned");
            System.out.println("4.) Udskriv alle bestillinger");
            System.out.println("5.) Udskriv hvor mange gange pizzaerne er købt");
            System.out.println("9.) Exit");

            run = sc.nextInt();
            switch (run) {
                case 1:
                    addBestillingMenu();
                    break;
                case 2:
                    removeBestillingMenu();
                    break;
                case 3:
                    //System.out.println(MariosPizzaBar.calculateThisMonthEarnings());
                    break;
                case 4:
                    udskrivBestillinger();
                    break;
                case 5:
                    //MariosPizzaBar.printMostBoughtPizzas();
                    break;
                case 9:
                    running = false;
                    break;
                default:
                    System.out.println("Indtast venligst et gyldigt input");
            }

        }
    }

    private void removeBestillingMenu() {
        for (Order order : MariosPizzaBar.getOrders()) {
            System.out.println("" + MariosPizzaBar.getOrders().indexOf(order) + order);
        }
        System.out.println("Indtast nummeret på den bestilling du vil have fjernet.");
        //MariosPizzaBar.removeOrder(MariosPizzaBar.getOrders().get(sc.nextInt()));
    }

    private void addBestillingMenu() {
        //System.out.println(MariosPizzaBar.getMenu());
        System.out.println("Indtast den måned du vil have pizzaen i.");
        int month = sc.nextInt();
        System.out.println("Indtast den dag du vil have pizzaen i.");
        int day = sc.nextInt();

        ArrayList<Pizza> pizzaer = new ArrayList<Pizza>();
        int pizzaIndex = sc.nextInt();
        /*while (pizzaIndex <= MariosPizzaBar.getMenu().getPizzas().size()) {
            pizzaer.add(MariosPizzaBar.getMenu().getPizzas().get(pizzaIndex));
            pizzaIndex = sc.nextInt();
        }*/
        Order tempOrder = new Order(pizzaer, new GregorianCalendar(Calendar.YEAR, month, day), false);
        //MariosPizzaBar.addOrder(tempOrder);
    }

    private void udskrivBestillinger() {
        for (Order order : MariosPizzaBar.getOrders()) {
            System.out.println(order);
        }

    }
}
