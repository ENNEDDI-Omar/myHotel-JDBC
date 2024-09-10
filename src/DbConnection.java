import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public static Connection connectToDb() {
        Connection conx=null;

        try {
            //charger le pilot JDBC
            Class.forName("org.postgresql.Driver");

            //j'établit la connexion
            String url = "jdbc:postgresql://localhost:5432/myHotel";
            conx = DriverManager.getConnection(url, "myHotel", "");

            if (conx != null)
            {
                System.out.println("Connected to the database successfully!");
            }
              return conx;
        } catch (SQLException e)
        {
            System.out.println("SQLException: "+e.getMessage());
            return null;
        } catch (ClassNotFoundException e)
        {
            System.out.println("ClassNotFoundException: "+e.getMessage());
            return null;
        }finally
        {
            try {
                if (conx != null)
                {
                    conx.close();
                }
            } catch (SQLException e) {
                System.out.println("SQL Exception on close: "+e.getMessage());
            }
        }

    }
}
