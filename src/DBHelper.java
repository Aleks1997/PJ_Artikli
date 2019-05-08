
import java.sql.*;
import java.util.ArrayList;

public class DBHelper {

    public static Connection getConnection() {

        try {
           String strDbUser = "root"; // database loging username
           String strDbPassword = "vitez123"; // database login password
            String url = "jdbc:mysql://127.0.0.1:3306/";

            Class.forName("com.mysql.cj.jdbc.Driver");
            String database = "sakila";
            Connection con = DriverManager.getConnection(url + database, strDbUser,
                    strDbPassword);

            return con;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static void createTable() {
        try{
            Connection con = getConnection();
            PreparedStatement create =  con.prepareStatement("CREATE TABLE IF NOT EXISTS ");

        }catch(Exception e){System.out.println(e);}
    }

    public static ArrayList<String> get(){
        try{
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT first_name FROM customer");
            ResultSet resultSet = statement.executeQuery();

            ArrayList<String> array = new ArrayList<>();

            while(resultSet.next()){

                System.out.println(resultSet.getString("first_name"));
                System.out.println(" ");

                array.add(resultSet.getString("first_name"));
            }
            System.out.println("Konec");
            return array;
        }catch(Exception e){
            return null;
        }
    }

    private DBHelper(){}
}
