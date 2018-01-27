package Main.Service;

import java.sql.*;
import java.util.Scanner;

public class MovieServiceImpl implements MovieService {
    private static final String DBNS_CONN_STRING = "jdbc:mysql://localhost:3306/project";
    private static final String DBNS_USERNAME = "root";
    private static final String DBNS_PASSWORD = "mainata970430";

    @Override
    public void findMovie() {
        System.out.println("Enter title:");
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
    public void createMovie() {
        Scanner scanner = new Scanner(System.in);
        String sqlStatement = "INSERT INTO movies (id,Title,Loan,Available) " +
                "values (?,?,?,?);";
        try(Connection con = DriverManager.getConnection(DBNS_CONN_STRING,DBNS_USERNAME,DBNS_PASSWORD);
            PreparedStatement statement = con.prepareStatement(sqlStatement)){
            statement.setInt(1,scanner.nextInt());
            statement.setString(2,scanner.next());
            statement.setDouble(3,scanner.nextDouble());
            statement.setInt(4,scanner.nextInt());

            int rs = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void listMovies() {

    }
}
