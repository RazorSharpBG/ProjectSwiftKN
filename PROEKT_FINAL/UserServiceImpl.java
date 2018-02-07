package projekt;
import java.sql.*;
import java.util.Scanner;
import projekt.Connect;

public class UserServiceImpl extends Connect implements UserService {
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
   @Override
    public void findUser() {
        System.out.println("Enter GSM & E-Mail: ");
        Scanner scanner = new Scanner(System.in);
        String sqlStatement =" SELECT * FROM person WHERE gsm = ? or email = ?";
        String gsm=scanner.next();
        String email=scanner.next();
        try(Connection con = getConnection();
            PreparedStatement statement =con.prepareStatement(sqlStatement)){
        	statement.setString(1, gsm);
        	statement.setString(2, email);
        	ResultSet rs = statement.executeQuery();

            while (rs.next()){
                System.out.printf("%s %s %s %s %d",rs.getString("name"),rs.getString("gsm"),rs.getString("email"),rs.getString("address"),rs.getInt("tax"));
            }

        }catch (SQLException | ClassNotFoundException ex){
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

    @Override
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