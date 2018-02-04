package Main.Service;


import Main.connection.Connect;
import javax.management.StringValueExp;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.Scanner;


public class UserServiceImpl extends Connect implements UserService  {


    @Override
    public void findUser() {
        System.out.println("Enter gsm & email:");
        Scanner scanner = new Scanner(System.in);
        String sqlStatement = String.format(" SELECT * FROM person WHERE gsm = '%s' OR email =  '%s'",scanner.next(),scanner.next());
        try(Connection con = getConnection();
            Statement statement = con.createStatement()){
            ResultSet rs = statement.executeQuery(sqlStatement);

            while (rs.next()){
                System.out.printf("%s %s %s %s %d %d",rs.getString("name"),rs.getString("gsm"),rs.getString("email"),rs.getString("address"),rs.getInt("number_movies"),rs.getInt("tax"));
            }

        }catch (SQLException |  ClassNotFoundException ex ){
            ex.printStackTrace();
        }
    }

    @Override
    public void createUser() {
        Scanner scanner = new Scanner(System.in);
        String sqlStatement = "INSERT INTO person(name,gsm,email,address) " +
                "values (?,?,?,?);";
        try(Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(sqlStatement)){
            System.out.print("Enter name: ");
            statement.setString(1,scanner.nextLine());
            System.out.print("Enter GSM number: ");
            statement.setString(2,scanner.next());
            System.out.print("Enter e-mail: ");
            statement.setString(3,scanner.next());
            System.out.print("Enter address: ");
            statement.setString(4,scanner.nextLine());


            int rs = statement.executeUpdate();

        }catch (SQLException |  ClassNotFoundException  e) {
            e.printStackTrace();
        }
    }
    @Override
    public void listUsers() {
        String sqlStatement = "SELECT * FROM person";
        try(Connection con =getConnection();
            Statement statement = con.createStatement()){
            ResultSet rs = statement.executeQuery(sqlStatement);

            while (rs.next()){
                System.out.printf("%s %s %s %s %d %d",rs.getString("name"),rs.getString("gsm"),rs.getString("email"),rs.getString("address"),rs.getInt("number_movies"),rs.getInt("tax"));
                System.out.println();
            }

        }catch (SQLException |  ClassNotFoundException ex ){
            ex.printStackTrace();
        }
    }
    @Override
    public void loanMovie() {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name=scanner.nextLine();
        System.out.print("Enter title of movie you want to get: ");
        String title=scanner.nextLine();


        String sqlStatement="SELECT id FROM person WHERE name = ? ;";
        String sqlStatement1="SELECT id FROM movies WHERE title = ? ;";
        String sqlStatement2="INSERT INTO person_movies (id_person, id_movies,return_date) VALUES (?,?, NOW() + INTERVAL ? DAY);";
        String sqlStatement3 ="SELECT Available_days FROM movies WHERE title = ? ;";
        String sqlStatement4 = "UPDATE movies SET Available=Available-1 Where Title = ? ;";
        String sqlStatement5 = "UPDATE person SET number_movies=number_movies+1 Where name = ? ;";

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

            PreparedStatement statement3 = con.prepareStatement(sqlStatement3);
            statement3.setString(1, title);
            ResultSet rs3=statement3.executeQuery();

                int availableDays = 0;
            if (rs3.next()) {
                availableDays = rs3.getInt("Available_days");
            }

            PreparedStatement statement2=con.prepareStatement(sqlStatement2);
            statement2.setInt(1,id_person);
            statement2.setInt(2,id_movies);
            statement2.setInt(3,availableDays);

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
