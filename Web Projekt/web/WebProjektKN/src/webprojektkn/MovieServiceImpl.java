package webprojektkn;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieServiceImpl extends Connect{
    public void findMovie() {
        System.out.println("Enter title: ");
        Scanner scanner = new Scanner(System.in);
        String sqlStatement =" SELECT * FROM movies WHERE Title = ?";
        String title=scanner.next();
        try(Connection con = getConnection();
            PreparedStatement statement =con.prepareStatement(sqlStatement)){
        	statement.setString(1, title);
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                System.out.printf("%s %.2f %d %d",rs.getString("Title"),rs.getDouble("Loan"),rs.getInt("Available"),rs.getInt("Available_days"));
            }

        }catch (SQLException | ClassNotFoundException ex ){
            ex.printStackTrace();
        }
    }
    public void createMovie() {
        Scanner scanner = new Scanner(System.in);
        String sqlStatement = "INSERT INTO movies (Title,Loan,Available, Available_days) " +
                "values (?,?,?,?);";
        try(Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(sqlStatement)){
            statement.setString(1,scanner.next());
            statement.setDouble(2,scanner.nextDouble());
            statement.setInt(3,scanner.nextInt());
            statement.setInt(4,scanner.nextInt());

            int rs = statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void listMovies() {
        String sqlStatement = "SELECT * FROM movies";
        try(Connection con = getConnection();
            Statement statement = con.createStatement()){
            ResultSet rs = statement.executeQuery(sqlStatement);
            while (rs.next()){
                System.out.printf("Title: %s Price:%.2f Availabe movies: %d Available for %d days!",rs.getString("Title"),rs.getDouble("Loan"),rs.getInt("Available"),rs.getInt("Available_days"));
                System.out.println();
            }

        }catch (SQLException | ClassNotFoundException ex ){
            ex.printStackTrace();
        }
    }
    
    public ArrayList exportMoviesToList() {
        ArrayList<Movie> movies = new ArrayList<>();
        String sqlStatement = "SELECT * FROM movies";
        try(Connection con = getConnection();
            Statement statement = con.createStatement()){
            ResultSet rs = statement.executeQuery(sqlStatement);
            while (rs.next()){
                Movie movie = new Movie();
                movie.title = rs.getString("title");
                movie.loan = rs.getInt("Loan");
                movie.available=rs.getInt("Available");
                movie.available_for_days = rs.getInt("Available_days");
                movies.add(movie);
            }

        }catch (SQLException | ClassNotFoundException ex ){
            ex.printStackTrace();
        }
        return movies;
    }
    
    public void returnMovie() {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name=scanner.nextLine();
        System.out.print("Enter title of movie you want to return: ");
        String title=scanner.nextLine();
        String sqlStatement="SELECT id FROM person WHERE name = ? ;";
        String countID="select count(id_person) from person_movies where id_person=?;";
        String sqlStatement1="SELECT id FROM movies WHERE title = ? ;";
        String sqlStatement2="DELETE FROM person_movies WHERE id_person = ? AND id_movies = ? ;";
        String sqlStatement4 ="UPDATE movies SET Available=Available+1 WHERE Title = ? ;";
        String sqlStatement5 ="UPDATE person SET number_movies=number_movies-1 WHERE name = ? ;";
        String sqlStatement6="SELECT loan FROM movies WHERE title= ?;";
        String sqlStatement7="SELECT get_date from person_movies where id_person=? AND id_movies= ? ;";
        String sqlStatement8="SELECT timestampdiff(DAY,?,now());";
        String sqlStatement9="select available_days from movies where id=?;";

        try(Connection con = getConnection()){
        	PreparedStatement statement6=con.prepareStatement(sqlStatement6);
        	statement6.setString(1, title);
        	ResultSet rs6=statement6.executeQuery();
        	int loan=0;
        	if(rs6.next()){
        		loan=rs6.getInt("Loan");
        	}
        	PreparedStatement statement =con.prepareStatement(sqlStatement);
            statement.setString(1,name);
            ResultSet rs = statement.executeQuery();

            int id_person = 0;

            if (rs.next()) {
                id_person=rs.getInt("id");
            }
            PreparedStatement countid=con.prepareStatement(countID);
        	countid.setInt(1,id_person);
            ResultSet rscount=countid.executeQuery();
            int count=0;
            if(rscount.next()){
            	count=rscount.getInt(1);
            }
            PreparedStatement statement1=con.prepareStatement(sqlStatement1);
            statement1.setString(1, title);
            ResultSet rs1=statement1.executeQuery();

            int id_movies = 0;

            if (rs1.next()) {
                id_movies=rs1.getInt("id");
            }
            PreparedStatement statement7=con.prepareStatement(sqlStatement7);
        	statement7.setInt(1, id_person);
        	statement7.setInt(2, id_movies);
        	ResultSet rs7=statement7.executeQuery();
        	Timestamp date= null;
        	if(rs7.next()){
        		date=rs7.getTimestamp("get_date");
        	}
        	PreparedStatement statement8=con.prepareStatement(sqlStatement8);
			statement8.setTimestamp(1, date);
			ResultSet rs8=statement8.executeQuery();
			int result=0;
			if(rs8.next()){
				result=(rs8.getInt(1));
			}
			PreparedStatement statement9=con.prepareStatement(sqlStatement9);
			statement9.setInt(1, id_movies);
			ResultSet rs9=statement9.executeQuery();
			if(rs9.next()){
				id_movies=rs9.getInt("Available_days");
			}
			int tax=0;
			if(count==5){
				count=2;
				tax=result-count;
			}else{
				tax=result-id_movies;
			}
			if(tax<=0){
				tax=loan;
				System.out.printf("Your taxes are: %d BGN %n",tax);
			}else{
				tax+=loan;
				System.out.printf("Your taxes are:%d BGN %n" ,tax);
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