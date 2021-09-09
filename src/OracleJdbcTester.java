// import oracle.jdbc.OracleConnection;
import java.sql.*;
import java.util.Properties;

public class OracleJdbcTester {

    public static void main(String[] args) throws ClassNotFoundException {

        String jdbc_user = System.getenv("JDBC_CONNECT_USER");
        if (jdbc_user == null) {
            System.out.println("Invalid argument...provide a jdbc user");
            System.exit(1);
        }

        String jdbc_password = System.getenv("JDBC_CONNECT_PASSWORD");
        if (jdbc_password == null) {
            System.out.println("Invalid argument...provide a jdbc jdbc_password");
            System.exit(1);
        }

        String jdbc_connection = System.getenv("JDBC_CONNECTION_STRING");
        if (jdbc_connection == null) {
            System.out.println("Invalid argument...provide a jdbc_connection in the format jdbc:oracle:thin:@//<host>:<port>/<SID>");
            System.exit(1);
        }

        Class.forName("oracle.jdbc.driver.OracleDriver");

        Properties properties = new Properties();
        properties.setProperty("user", jdbc_user);
        properties.setProperty("password", jdbc_password);

        try {
            System.out.println("****** Starting JDBC Connection test *******");
            String sqlQuery = "select sysdate from dual";

            final Connection conn = DriverManager.getConnection(jdbc_connection, properties);
            conn.setAutoCommit(false);
            final Statement statement = conn.createStatement();
            System.out.println("Running SQL query: " + sqlQuery);

            final ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                System.out.println("Result of SQL query: [" + resultSet.getString(1) + "]");
            }

            statement.close();
            conn.close();

            System.out.println("JDBC connection test successful!");
            System.exit(0);
        } catch (SQLException ex) {
            System.out.println("Exception occurred connecting to database: " + ex.getMessage());
            System.exit(ex.getErrorCode());
        }
    }
}