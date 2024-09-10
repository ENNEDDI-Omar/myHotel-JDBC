package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    //création des propriétées
    private static final String URL = "jdbc:postgresql://localhost:5432/myHotel";
    private static final String User = "myHotel";
    private static  final String Password = "";
    private static Connection conx = null;
    public static DbConnection instance = null;

    //mise en place du constructeur privé
    private DbConnection()
    {
        try {
            //loader le jdbc driver
            Class.forName("org.postgresql.Driver");

            //établissement de la connection
            conx = DriverManager.getConnection(URL, User, Password);
            System.out.println("Database connection established successfully!");

        }catch (SQLException e)
        {
            System.out.println("Database connection failed!" + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver class not found!" + e.getMessage());
            e.printStackTrace();
        }
    }

}
