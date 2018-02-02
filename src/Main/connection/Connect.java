package Main.connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Connect {


    private static final String DBNS_CONN_STRING = "jdbc:mysql://localhost:3306/project";
    private static final String DBNS_USERNAME = "root";
    private static final String DBNS_PASSWORD = "mainata970430";

    private Connection connection;

    public Connect(){

    }
    protected Connection getConnection() throws SQLException, ClassNotFoundException {
        if (connection != null){
            return connection;
        }
        Class.forName("com.mysql.jdbc.Driver");

        connection =  DriverManager.getConnection(DBNS_CONN_STRING,DBNS_USERNAME,DBNS_PASSWORD);
         return connection ;
    }




}
