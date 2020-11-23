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
            if (res.next()){
                System.out.println(res.getString(1));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return  orderArr;
    }
}
