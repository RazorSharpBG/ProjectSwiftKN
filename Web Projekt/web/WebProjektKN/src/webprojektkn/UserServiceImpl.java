package webprojektkn;
import java.sql.*;
import java.util.Scanner;
import webprojektkn.Connect;

public class UserServiceImpl extends Connect{
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
        String sqlStatement5="select available from movies where title=?;";
        String sqlStatement6="select count(id_person) from person_movies where id_person=? and id_movies=?;";
        String sqlStatement7="select count(id_person) from person_movies where id_person=?";
        try(Connection con = getConnection()){
            PreparedStatement statement =con.prepareStatement(sqlStatement);
            statement.setString(1,name);
            ResultSet rs = statement.executeQuery();

            int id_person = 0;

            if (rs.next()) {
                id_person=rs.getInt("id");
            }
            PreparedStatement statement7=con.prepareStatement(sqlStatement7);
            statement7.setInt(1, id_person);
            ResultSet rs7=statement7.executeQuery();
            int count=0;
            if(rs7.next()){
            	count=rs7.getInt(1);
            }
            if(count>5){
            	System.out.println("You have reached the maximum amount of movies you can loan!!!");
            	return;
            }
            PreparedStatement statement1=con.prepareStatement(sqlStatement1);
            statement1.setString(1, title);
            ResultSet rs1=statement1.executeQuery();

            int id_movies = 0;

            if (rs1.next()) {
                id_movies=rs1.getInt("id");
            }
            PreparedStatement statement6=con.prepareStatement(sqlStatement6);
            statement6.setInt(1, id_person);
            statement6.setInt(2, id_movies);
            ResultSet rs6=statement6.executeQuery();
            count=0;
            if(rs6.next()){
            	count=rs6.getInt(1);
            }
            if(count>=0){
            	System.out.println("You have already loaned this movie!");
            	return;
            }
            PreparedStatement statement5=con.prepareStatement(sqlStatement5);
            statement5.setString(1,title);
            ResultSet rs5=statement5.executeQuery();
            int av=0;
            if(rs5.next()){
            	av=rs5.getInt("available");
            }
            if(av<=0){
            	System.out.printf("%s isn't available for loan now!",title);
            	return;
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
        }catch (SQLException |  ClassNotFoundException ex ){
            ex.printStackTrace();
            }
        }
    public User findUser(String username, String password) {
        Boolean trumafaka = false;
        User user = null;
        String sqlStatement ="SELECT * FROM person WHERE username = ? and password = ?;";
        try(Connection con = getConnection();
                PreparedStatement statement = con.prepareStatement(sqlStatement)){
            	statement.setString(1, username);
            	statement.setString(2, password);
        	ResultSet rs = statement.executeQuery();
                trumafaka=true;
//              System.out.println(rs.getString("name"));
        while (rs.next()){
                user = new User();
                user.setName(rs.getString("name"));
                System.out.printf("%s %s %s %s %d",rs.getString("name"),rs.getString("gsm"),rs.getString("email"),rs.getString("address"),rs.getInt("tax"));
                trumafaka = true;
            }
        }catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return user;
//        if (trumafaka)
//            return user;
//        return null;
    }
    
    public void createNewUser(String username, String password, String email, String phone, String address) {
        User user = new User();
        user.setAddress(address);
        user.setUsername(username);
        user.setGsm(phone);
        user.setPassword(password);
        user.setEmail(email);
        
        String sqlStatement = "INSERT INTO person(name,gsm,email,address, username, password) " +
                                "values ('Toshko Ivanov', ?, ?, ?, ?, ?);";
        
        try(Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(sqlStatement)){
            
            statement.setString(1, phone);
            statement.setString(2, email);
            statement.setString(3, address);
            statement.setString(4, username);
            statement.setString(5, password);
            
            int rs = statement.executeUpdate();
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public void createUser() {
        Scanner scanner = new Scanner(System.in);
        String sqlStatement = "INSERT INTO person(name,gsm,email,address) " +
                "values (?,?,?,?);";
        try(Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(sqlStatement)){
        	System.out.println("Enter name:");
            statement.setString(1,scanner.next());
            System.out.println("Enter GSM:");
            statement.setString(2,scanner.next());
            System.out.println("Enter e-mail:");
            statement.setString(3,scanner.next());
            System.out.println("Enter adress:");
            statement.setString(4,scanner.next());
           
            int rs = statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void listUsers() {
        String sqlStatement = "SELECT * FROM person";
        try(Connection con =getConnection();
            Statement statement = con.createStatement()){
            ResultSet rs = statement.executeQuery(sqlStatement);


            while (rs.next()){
                System.out.printf("%s %s %s %s %d",rs.getString("name"),rs.getString("gsm"),rs.getString("email"),rs.getString("address"),rs.getInt("tax"));
                System.out.println();
            }

        }catch (SQLException | ClassNotFoundException ex ){
            	ex.printStackTrace();
        }
    }
    
    
}