package Main.Service;


import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UserServiceImpl implements UserService {
    private static final String DBNS_CONN_STRING = "jdbc:mysql://localhost:3306/project";
    private static final String DBNS_USERNAME = "root";
    private static final String DBNS_PASSWORD = "mainata970430";


    @Override
    public void findUser() {
        System.out.println("Enter gsm & email:");
        Scanner scanner = new Scanner(System.in);
        String sqlStatement = "SELECT * FROM person " +
                "WHERE gsm = " + scanner.next() + " OR email = " + scanner.next();
        try(Connection con = DriverManager.getConnection(DBNS_CONN_STRING,DBNS_USERNAME,DBNS_PASSWORD);
            Statement statement = con.createStatement()){
            ResultSet rs = statement.executeQuery(sqlStatement);

            while (rs.next()){
                System.out.printf("%s %s %s %s %d",rs.getString("name"),rs.getString("gsm"),rs.getString("email"),rs.getString("adress"),rs.getInt("tax"));
            }

        }catch (SQLException ex ){
            while (ex!=null){
                System.out.println(ex.getSQLState());
                System.out.println(ex.getMessage());
                System.out.println(ex.getErrorCode());
                ex = ex.getNextException();
            }
        }
    }

    @Override
    public void createUser() {
        Scanner scanner = new Scanner(System.in);
        String sqlStatement = "INSERT INTO person(name,gsm,email,adress,tax) " +
                "values (?,?,?,?,?);";
        try(Connection con = DriverManager.getConnection(DBNS_CONN_STRING,DBNS_USERNAME,DBNS_PASSWORD);
            PreparedStatement statement = con.prepareStatement(sqlStatement)){
            statement.setString(1,scanner.next());
            statement.setString(2,scanner.next());
            statement.setString(3,scanner.next());
            statement.setString(4,scanner.next());
            statement.setInt(5,scanner.nextInt());

            int rs = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void listUsers() {
        String sqlStatement = "SELECT * FROM person";
        try(Connection con = DriverManager.getConnection(DBNS_CONN_STRING,DBNS_USERNAME,DBNS_PASSWORD);
            Statement statement = con.createStatement()){
            ResultSet rs = statement.executeQuery(sqlStatement);


            while (rs.next()){
                System.out.printf("%s %s %s %s %d",rs.getString("name"),rs.getString("gsm"),rs.getString("email"),rs.getString("adress"),rs.getInt("tax"));
            }

        }catch (SQLException ex ){
            while (ex!=null){
                System.out.println(ex.getSQLState());
                System.out.println(ex.getMessage());
                System.out.println(ex.getErrorCode());
                ex = ex.getNextException();
            }
        }
    }

}
