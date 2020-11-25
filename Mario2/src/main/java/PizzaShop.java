import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class PizzaShop {
    private Menu menu;

    public PizzaShop(Menu menu){
        this.menu = menu;
    }

    public ArrayList<Order> getOrders(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSS");

        ArrayList<Order> orderArr = new ArrayList<Order>();
        Connection con = null;

        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/pizzaShop", "root","root123");
            Statement preSta = con.createStatement();
            ResultSet res = preSta.executeQuery("Select * from activeOrder;");
            while (res.next()){
                String[] pizzasStringArr  = res.getString(3).split("@");
                ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
                for (int i = 1; i<pizzasStringArr.length;i++){
                    for (Pizza pizza : menu.getPizzas()){
                        if (Integer.parseInt(pizzasStringArr[i]) == pizza.getNumber()){
                            pizzas.add(pizza);
                        }
                    }
                }
                GregorianCalendar date = new GregorianCalendar();
                date.setTime(res.getDate(2));
                orderArr.add(new Order(pizzas, date, false));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  orderArr;
    }

    public Menu getMenu(){
        return this.menu;
    }

    public void moveActiveToCompleted(int index){
        Connection con = null;
        String completedOrderSQL = "";
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/pizzaShop", "root","root123");
            Statement preSta = con.createStatement();
            ResultSet res = preSta.executeQuery("Select * from activeOrder where orderNumber = "+ index+ ";");

            if (res.next()){
                completedOrderSQL += "insert into completedOrder(orderNumber, deliveryDate, pizzas) values (" +
                        res.getInt(1) + ", '" +
                        res.getString(2) + "', '" +
                        res.getString(3) + "');";
            }
            preSta.execute(completedOrderSQL);
            preSta.execute("delete from activeOrder where orderNumber = "+ index+ ";");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addToActive(Order order){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSS");
        String date = format.format(order.getDatetime().getTime());
        String pizzaList = "";
        for(Pizza pizza : order.getPizzas()){
            pizzaList += "@" + pizza.getNumber();
        }
        String SQL = "insert into activeOrder (orderNumber, deliveryDate, pizzas) values (" +
                0 + ", '" +
                date + "', '" +
                pizzaList + "');";

        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/pizzaShop", "root", "root123");
            Statement preSta = con.createStatement();
            preSta.execute(SQL);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void printMostBoughtPizzas(){
        int pizzaCount = 0;

        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/pizzaShop", "root", "root123");
            Statement preSta = con.createStatement();
            ResultSet res = preSta.executeQuery("SELECT count(*) from pizza;");
            if(res.next()){
                pizzaCount = res.getInt(1);
            }
            res = preSta.executeQuery("select * from completedOrder;");
            ArrayList<String> pizzasString = new ArrayList<String>();
            while(res.next()){
                pizzasString.addAll(Arrays.asList(res.getString(3).split("@")));
            }
            for (int i= 1; i <= pizzaCount; i++) {
                int timesBought = Collections.frequency(pizzasString, String.valueOf(i));
                System.out.println("Nr. " + i + ": " + timesBought);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int calculateThisMonthEarnings(){
        int totalEarnings = 0;
        String date = "'"+
                Calendar.getInstance().get(Calendar.YEAR) + "-" +
                Calendar.getInstance().get(Calendar.MONTH) +
                "-00 00:00:00.0000'";

        String SQL = "select * from completedOrder where deliveryDate > " + date + ";";

        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/pizzaShop", "root", "root123");
            Statement preSta = con.createStatement();
            ResultSet res = preSta.executeQuery(SQL);
            ArrayList<String> pizzasString = new ArrayList<String>();
            while(res.next()){
                pizzasString.addAll(Arrays.asList(res.getString(3).split("@")));
            }
            for (String string : pizzasString) {
                ResultSet res2 = preSta.executeQuery("SELECT pizzaPrice from pizza where pizzaNumber = " + string + ";");
                if (res2.next()) {
                    totalEarnings += res2.getInt(1);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return totalEarnings;
    }


}
