import java.sql.*;
import java.util.ArrayList;

public class PizzaShop {
    private Menu menu;

    public PizzaShop(Menu menu){
        this.menu = menu;
    }

    public ArrayList<Order> getOrders(){
        ArrayList<Order> orderArr = new ArrayList<Order>();
        Connection con = null;

        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/pizzaShop", "root","root123");
            Statement preSta = con.createStatement();
            ResultSet res = preSta.executeQuery("Select * from activeOrder;");
            while (res.next()){
                String[] pizzasStringArr  = res.getString(3).split(",");
                int[] pizzas = new int[pizzasStringArr.length];
                for (int i = 0; i<pizzasStringArr.length;i++){
                    pizzas[i] = Integer.parseInt(pizzasStringArr[i]);
                }
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


}
