package electricity.billing.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MySqlConnection {
    Connection connection;
    Statement statement;

    MySqlConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bill_System", "root", "arun@987");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
