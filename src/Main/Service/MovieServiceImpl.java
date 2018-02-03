package Main.Service;

import Main.connection.Connect;

import java.sql.*;
import java.util.Scanner;

public class MovieServiceImpl extends Connect implements MovieService {
//    private static final String DBNS_CONN_STRING = "jdbc:mysql://localhost:3306/project";
//    private static final String DBNS_USERNAME = "root";
//    private static final String DBNS_PASSWORD = "mainata970430";

    @Override
    public void findMovie() {
        System.out.println("Enter title: ");
        Scanner scanner = new Scanner(System.in);
        String sqlStatement = String.format(" SELECT * FROM movies WHERE Title =  '%s'",scanner.nextLine());
        try(Connection con = getConnection();
            Statement statement = con.createStatement()){
            ResultSet rs = statement.executeQuery(sqlStatement);

            while (rs.next()){
                System.out.printf("%s %.2f %d %d",rs.getString("Title"),rs.getDouble("Loan"),rs.getInt("Available"),rs.getInt("Available_days"));
            }

        }catch (SQLException |  ClassNotFoundException ex ){
//            while (ex!=null){
//                System.out.println(ex.getSQLState());
//                System.out.println(ex.getMessage());
//                System.out.println(ex.getErrorCode());
//                ex = ex.getNextException();
//            }
        }
    }

    @Override
    public void createMovie() {
        Scanner scanner = new Scanner(System.in);
        String sqlStatement = "INSERT INTO movies (Title,Loan,Available, Available_days) " +
                "values (?,?,?,?);";
        try(Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(sqlStatement)){
            System.out.print("Enter title: ");
            statement.setString(1,scanner.nextLine());
            System.out.print("Enter oan price:");
            statement.setDouble(2,scanner.nextDouble());
            System.out.print("Enter available units: ");
            statement.setInt(3,scanner.nextInt());
            System.out.print("Enter available days: ");
            statement.setInt(4,scanner.nextInt());

            int rs = statement.executeUpdate();

        } catch (SQLException |  ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void listMovies() {
        String sqlStatement = "SELECT * FROM movies";
        try(Connection con =getConnection();
            Statement statement = con.createStatement()){
            ResultSet rs = statement.executeQuery(sqlStatement);


            while (rs.next()){
                System.out.printf("%s %.2f %d %d",rs.getString("Title"),rs.getDouble("Loan"),rs.getInt("Available"),rs.getInt("Available_days"));
                System.out.println();
            }

        }catch (SQLException |  ClassNotFoundException ex ){
//            while (ex!=null){
//                System.out.println(ex.getSQLState());
//                System.out.println(ex.getMessage());
//                System.out.println(ex.getErrorCode());
//                ex = ex.getNextException();
//            }
        }

    }
}
