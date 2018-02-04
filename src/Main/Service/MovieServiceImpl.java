package Main.Service;

import Main.connection.Connect;

import java.sql.*;
import java.util.Scanner;

public class MovieServiceImpl extends Connect implements MovieService {

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
            ex.printStackTrace();
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
            ex.printStackTrace();
        }

    }
    @Override
    public void returnMovie() {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name=scanner.nextLine();
        System.out.print("Enter title of movie you want to return: ");
        String title=scanner.nextLine();


        String sqlStatement="SELECT id FROM person WHERE name = ? ;";
        String sqlStatement1="SELECT id FROM movies WHERE title = ? ;";
        String sqlStatement2="DELETE FROM person_movies WHERE id_person = ? AND id_movies = ? ;";
        String sqlStatement4 = "UPDATE movies SET Available=Available+1 WHERE Title = ? ;";
        String sqlStatement5 = "UPDATE person SET number_movies=number_movies-1 WHERE name = ? ;";

        try(Connection con = getConnection()){
            PreparedStatement statement =con.prepareStatement(sqlStatement);
            statement.setString(1,name);
            ResultSet rs = statement.executeQuery();

            int id_person = 0;

            if (rs.next()) {
                id_person=rs.getInt("id");
            }

            PreparedStatement statement1=con.prepareStatement(sqlStatement1);
            statement1.setString(1, title);
            ResultSet rs1=statement1.executeQuery();

            int id_movies = 0;

            if (rs1.next()) {
                id_movies=rs1.getInt("id");
            }
            

            PreparedStatement statement2=con.prepareStatement(sqlStatement2);
            statement2.setInt(1,id_person);
            statement2.setInt(2,id_movies);

            statement2.executeUpdate();

            PreparedStatement statement4=con.prepareStatement(sqlStatement4);
            statement4.setString(1, title);
            statement4.executeUpdate();

            PreparedStatement statement5=con.prepareStatement(sqlStatement5);
            statement5.setString(1,name);
            statement5.executeUpdate();


        }catch (SQLException |  ClassNotFoundException ex ){
            ex.printStackTrace();

        }
    }
}
