package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connection {
    private DB_Connection() {

    }

    public static DB_Connection getInstance() {

        return new DB_Connection();

    }

    public Connection getConnection() {
/*        Connection conn = null;
        String url = "jdbc:sqlite:C:/Users/Morten/IdeaProjects/Train_schedule_APP/src/db/Train_schedule.db";

*/
        String connect_string = "jdbc:sqlite:C:/Users/Morten/IdeaProjects/Train_schedule_APP/src/db/Train_schedule.db";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(connect_string);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

}
