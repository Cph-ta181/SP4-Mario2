import java.util.*;

public class UIController {
    Scanner sc = new Scanner(System.in);
    MenuKort menu = new MenuKort("./resources/pizzaer.txt");
    PizzaBar MariosPizzaBar = new PizzaBar(menu);


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
                    System.out.println(MariosPizzaBar.calculateThisMonthEarnings());
                    break;
                case 4:
                    udskrivBestillinger();
                    break;
                case 5:
                    MariosPizzaBar.printMostBoughtPizzas();
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
        for (Bestilling bestilling : MariosPizzaBar.getBestillinger()) {
            System.out.println("" + MariosPizzaBar.getBestillinger().indexOf(bestilling) + bestilling);
        }
        System.out.println("Indtast nummeret på den bestilling du vil have fjernet.");
        MariosPizzaBar.removeBestilling(MariosPizzaBar.getBestillinger().get(sc.nextInt()));
    }

    private void addBestillingMenu() {
        System.out.println(MariosPizzaBar.getMenu());
        System.out.println("Indtast den måned du vil have pizzaen i.");
        int month = sc.nextInt();
        System.out.println("Indtast den dag du vil have pizzaen i.");
        int day = sc.nextInt();

        ArrayList<Pizza> pizzaer = new ArrayList<>();
        int pizzaIndex = sc.nextInt();
        while (pizzaIndex <= MariosPizzaBar.getMenu().getPizzas().size()) {
            pizzaer.add(MariosPizzaBar.getMenu().getPizzas().get(pizzaIndex));
            pizzaIndex = sc.nextInt();
        }
        Bestilling tempBestilling = new Bestilling(pizzaer, new GregorianCalendar(Calendar.YEAR, month, day), false);
        MariosPizzaBar.addBestilling(tempBestilling);
    }

    private void udskrivBestillinger() {
        for (Bestilling bestilling : MariosPizzaBar.getBestillinger()) {
            System.out.println(bestilling);
        }

    }
}
