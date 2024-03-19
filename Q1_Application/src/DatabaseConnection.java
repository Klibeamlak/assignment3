import java.sql.*;

public class DatabaseConnection {
    // Database connection parameters
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/assignment3"; // URL for connecting to the PostgreSQL database
    private static final String USER = "postgres"; // Username for accessing the database
    private static final String PASS = "Thelibe11"; // Password for accessing the database

    /*
        Function to establish database connection
        Initialize connection object
        Establish connection to the database
        Print stack trace if connection fails
        Return the established connection (or null if failed)
     */
    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
