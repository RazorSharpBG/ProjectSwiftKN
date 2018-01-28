package projekt;

import javax.management.StringValueExp;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UserServiceImpl implements UserService {
    private static final String DBNS_CONN_STRING = "jdbc:mysql://localhost:3306/project";
    private static final String DBNS_USERNAME = "root";
    private static final String DBNS_PASSWORD = "balon4e1";

    @Override
    public void loanMovie() {
    	Scanner scanner=new Scanner(System.in);
    	System.out.println("Enter your name:");
    	String name=scanner.next();
    	System.out.println("Enter movie you want to get:");
    	String title=scanner.next();
    	String sqlStatement="SELECT id from person WHERE name = ?";
    	String sqlStatement1="SELECT id from movies WHERE title = '?'";
    	String sqlStatement2="insert into person_movies(id_person,id_movies)" + " values (?,?);";
    	try(Connection con = DriverManager.getConnection(DBNS_CONN_STRING,DBNS_USERNAME,DBNS_PASSWORD)){
                PreparedStatement statement =con.prepareStatement(sqlStatement);
    			statement.setString(1,name);
            	ResultSet rs = statement.executeQuery();
            	
            	int id_person = 0;
            	
            	if (rs.next()) {
                	id_person=rs.getInt("id");
                	return;
            	}
            	
            	PreparedStatement statement1=con.prepareStatement(sqlStatement1);
            	statement1.setString(1, title);
            	ResultSet rs1=statement1.executeQuery();
            	int id_movies=rs1.getInt("id");
            	
            	PreparedStatement statement2=con.prepareStatement(sqlStatement2);
            	statement2.setInt(1,id_person);
            	statement2.setInt(2,id_movies);
            	
            	statement2.executeUpdate();

//                while (rs2.next()){
//                    System.out.printf("%d %d",rs2.getInt("id_person"),rs2.getInt("id_movies"));
//                }

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
    public void findUser() {
        System.out.println("Enter GSM & E-Mail: ");
        Scanner scanner = new Scanner(System.in);
        String sqlStatement =" SELECT * FROM person WHERE gsm = ? or email = ?";
        String gsm=scanner.next();
        String email=scanner.next();
        try(Connection con = DriverManager.getConnection(DBNS_CONN_STRING,DBNS_USERNAME,DBNS_PASSWORD);
            PreparedStatement statement =con.prepareStatement(sqlStatement)){
        	statement.setString(1, gsm);
        	statement.setString(2, email);
        	ResultSet rs = statement.executeQuery();

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
//    public void findUser() {
//        System.out.println("Enter gsm & email:");
//        Scanner scanner = new Scanner(System.in);
//        String sqlStatement = String.format(" SELECT * FROM person WHERE gsm = '%s' OR email =  '%s'",scanner.next(),scanner.next());
//        try(Connection con = DriverManager.getConnection(DBNS_CONN_STRING,DBNS_USERNAME,DBNS_PASSWORD);
//            Statement statement = con.createStatement()){
//            ResultSet rs = statement.executeQuery(sqlStatement);
//
//            while (rs.next()){
//                System.out.printf("%s %s %s %s %d",rs.getString("name"),rs.getString("gsm"),rs.getString("email"),rs.getString("address"),rs.getInt("tax"));
//            }
//
//        }catch (SQLException ex ){
//            while (ex!=null){
//                System.out.println(ex.getSQLState());
//                System.out.println(ex.getMessage());
//                System.out.println(ex.getErrorCode());
//                ex = ex.getNextException();
//            }
//        }
//    }

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
                System.out.printf("%s %s %s %s %d",rs.getString("name"),rs.getString("gsm"),rs.getString("email"),rs.getString("address"),rs.getInt("tax"));
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