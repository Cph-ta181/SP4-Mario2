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
            PreparedStatement preSta = con.prepareStatement("SELECT COUNT(*) FROM activeOrder;");
            ResultSet res = preSta.executeQuery();
            System.out.println(res);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return  orderArr;
    }
}
