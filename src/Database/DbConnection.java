package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    //création des propriétées
    private static final String URL = System.getenv("DbUrl");
    private static final String User = System.getenv("DbUsr");
    private static  final String Password = System.getenv("DbPsw");
    private static Connection conx = null;
    private static DbConnection instance = null;

    /**
     *Constructeur privé pour instanciation extérieur
     */
    //mise en place du constructeur privé
    private DbConnection()
    {
        connect();
    }

    /**
     *Instanciation de la classe par la methode getInstance
     *
     * @return instance en singleton dela dbConnection
     */
    public static synchronized DbConnection getInstance()
    {
        if (instance == null)
        {
            instance = new DbConnection();
        }else {
            try {

                if (conx == null || conx.isClosed()) {
                    instance.connect();
                }
            } catch (SQLException e) {
                System.out.println("Failed to validate the database connection: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return instance;
    }

    /**
     * Méthode pour établir de la connexion
     */
    private void connect()
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

    /**
     * Methode pour getConx pour Rétablir la connxion
     * @return la connexion actuel
     */
    public Connection getConx()
    {
        try {
            if (conx == null || conx.isClosed())
            {
                connect();
            }
        } catch (SQLException e) {
            System.out.println("Failled to re-establish connection!");
            e.printStackTrace();
        }
        return conx;
    }

    /**
     * Méthode pour férmer la connexion avec la base de données
     */
    public static void closeConnexion()
    {
        if (conx != null)
        {
            try {
                conx.close();
                conx = null;
                System.out.println("Database connection is closed successfully.");
            } catch (SQLException e) {
                System.out.println("Failed to close database connection!" + e.getMessage());
                e.printStackTrace();
            }
        }
    }

}
