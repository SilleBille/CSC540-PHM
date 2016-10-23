package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by mkdin on 16-10-2016.
 */
public class ConnectionClass {
    private static final String jdbcURL = "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";
    public static Connection conn = null;

    // These are my credentials. Please don't flood my schema :D
    private static String user = "dmolugu";
    private static String pass = "200153075";

    public static Connection getDBConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // Get a connection from the first driver in the
            // DriverManager list that recognizes the URL jdbcURL

            conn = DriverManager.getConnection(jdbcURL, user, pass);




            return conn;


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
