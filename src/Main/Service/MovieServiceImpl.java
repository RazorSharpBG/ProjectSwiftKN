package Main.Service;

import java.sql.*;
import java.util.Scanner;

public class MovieServiceImpl implements MovieService {
    private static final String DBNS_CONN_STRING = "jdbc:mysql://localhost:3306/project";
    private static final String DBNS_USERNAME = "root";
    private static final String DBNS_PASSWORD = "mainata970430";

    @Override
    public void findMovie() {
        System.out.println("Enter title: ");
        Scanner scanner = new Scanner(System.in);
        String sqlStatement = String.format(" SELECT * FROM movies WHERE Title =  '%s'",scanner.next());
        try(Connection con = DriverManager.getConnection(DBNS_CONN_STRING,DBNS_USERNAME,DBNS_PASSWORD);
            Statement statement = con.createStatement()){
            ResultSet rs = statement.executeQuery(sqlStatement);

            while (rs.next()){
                System.out.printf("%s %.2f %d %d",rs.getString("Title"),rs.getDouble("Loan"),rs.getInt("Available"),rs.getInt("Available_days"));
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
        String sqlStatement = "INSERT INTO movies (Title,Loan,Available, Available_days) " +
                "values (?,?,?,?);";
        try(Connection con = DriverManager.getConnection(DBNS_CONN_STRING,DBNS_USERNAME,DBNS_PASSWORD);
            PreparedStatement statement = con.prepareStatement(sqlStatement)){
            statement.setString(1,scanner.next());
            statement.setDouble(2,scanner.nextDouble());
            statement.setInt(3,scanner.nextInt());
            statement.setInt(4,scanner.nextInt());

            int rs = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void listMovies() {
        String sqlStatement = "SELECT * FROM movies";
        try(Connection con = DriverManager.getConnection(DBNS_CONN_STRING,DBNS_USERNAME,DBNS_PASSWORD);
            Statement statement = con.createStatement()){
            ResultSet rs = statement.executeQuery(sqlStatement);


            while (rs.next()){
                System.out.printf("%s %f %d %d",rs.getString("Title"),rs.getDouble("Loan"),rs.getInt("Available"),rs.getInt("Available_days"));
                System.out.println();
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
