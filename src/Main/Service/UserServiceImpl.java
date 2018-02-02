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
    private static final String DBNS_CONN_STRING = "jdbc:mysql://localhost:3306/project";
    private static final String DBNS_USERNAME = "root";
    private static final String DBNS_PASSWORD = "mainata970430";



    @Override
    public void findUser() {
        System.out.println("Enter gsm & email:");
        Scanner scanner = new Scanner(System.in);
        String sqlStatement = String.format(" SELECT * FROM person WHERE gsm = '%s' OR email =  '%s'",scanner.next(),scanner.next());
        try(Connection con = DriverManager.getConnection(DBNS_CONN_STRING,DBNS_USERNAME,DBNS_PASSWORD);
            Statement statement = con.createStatement()){
            ResultSet rs = statement.executeQuery(sqlStatement);

            while (rs.next()){
                System.out.printf("%s %s %s %s %d",rs.getString("name"),rs.getString("gsm"),rs.getString("email"),rs.getString("address"),rs.getInt("tax"));
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
        String sqlStatement = "INSERT INTO person(name,gsm,email,address,tax) " +
                "values (?,?,?,?,?);";
        try(Connection con = DriverManager.getConnection(DBNS_CONN_STRING,DBNS_USERNAME,DBNS_PASSWORD);
            PreparedStatement statement = con.prepareStatement(sqlStatement)){
            statement.setString(1,scanner.next());
            statement.setString(2,scanner.next());
            statement.setString(3,scanner.next());
            statement.setString(4,scanner.next());
            statement.setInt(5,scanner.nextInt());

            int rs = statement.executeUpdate();

        } catch (SQLException  e) {
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
                System.out.printf("%s %s %s %s %d",rs.getString("name"),rs.getString("gsm"),rs.getString("email"),rs.getString("address"),rs.getInt("tax"));
                System.out.println();
            }

        }catch (SQLException  ex ){
//
            ex.printStackTrace();
        }
    }

    public void loanMovie() {
        Scanner scanner=new Scanner(System.in);



        System.out.println("Enter your name:");
        String name  = scanner.next();

        System.out.println("Enter movie you want to get:");
        String title = scanner.next();

        String sqlStatement=String.format("SELECT id FROM person WHERE name = '%s'",name);
        String sqlStatement1=String.format("SELECT id FROM movies WHERE title = '%s'",title);

        String sqlStatement2="INSERT INTO person_movies(id_person,id_movies,get_date,return_date) VALUES (?,?,?,?);";

        try(Connection con = DriverManager.getConnection(DBNS_CONN_STRING,DBNS_USERNAME,DBNS_PASSWORD)){

            PreparedStatement statement = con.prepareStatement(sqlStatement);

            ResultSet rs = statement.executeQuery(sqlStatement);

            int id_person = 0;
            while (rs.next()) {
                  id_person = rs.getInt("id");
            }

            PreparedStatement statement1=con.prepareStatement(sqlStatement1);

            ResultSet rs1=statement1.executeQuery(sqlStatement1);

            int id_movies = 0;
            while (rs1.next()) {
                id_movies = rs1.getInt("id");
            }

            PreparedStatement statement2=con.prepareStatement(sqlStatement2);
            statement2.setInt(1,id_person);
            statement2.setInt(2,id_movies);


            int rs2 = statement2.executeUpdate(sqlStatement2);


        }
        catch (SQLException  ex){
            while (ex!=null){
                System.out.println(ex.getSQLState());
                System.out.println(ex.getMessage());
                System.out.println(ex.getErrorCode());
                ex = ex.getNextException();
            }
        }
    }

}
