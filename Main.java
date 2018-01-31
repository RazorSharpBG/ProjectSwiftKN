package projekt;

import projekt.UserServiceImpl;
import projekt.MovieServiceImpl;

import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class Main {
    private static final String DBNS_CONN_STRING = "jdbc:mysql://localhost:3306/project";
    private static final String DBNS_USERNAME = "root";
    private static final String DBNS_PASSWORD = "balon4e1";

	
	public static void main(String[] args) {
		String sql = "SELECT id FROM person WHERE name = ?";
		
		try {
			Connection con = DriverManager.getConnection(DBNS_CONN_STRING, DBNS_USERNAME, DBNS_PASSWORD);
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "petka");
			
			ResultSet rs = ps.executeQuery();
			
			int person_id = 0;
			
			if (rs.next()) {
				person_id = rs.getInt("id");
				System.out.println(person_id);
			}
			
			
		} catch (Exception e) {
			//Ignore
		}
	}
	
	
    public static void main1(String[] args){
    	Scanner scanner = new Scanner(System.in);
        int counter = 0;
        UserServiceImpl userService = new UserServiceImpl();
        MovieServiceImpl movieService= new MovieServiceImpl();

        while(counter != -1){
        	System.out.println();
            System.out.println("\n---------Menu---------");
            System.out.println("---------Press 1 for all user information---------");
            System.out.println("---------Press 2 to create user---------");
            System.out.println("---------Press 3 to find user---------");
            System.out.println("---------Press 4 to create movie---------");
            System.out.println("---------Press 5 to find movie---------");
            System.out.println("---------Press 6 to list movie---------");
            System.out.println("---------Press 7 to get movie---------");
            System.out.println("---------Press 0 to exit---------");


            counter = scanner.nextInt();
            if(counter == 1){
                userService.listUsers();
            }else if(counter == 2){
                userService.createUser();
            }else if(counter == 3){
                userService.findUser();
            }else if(counter == 4){
            	movieService.createMovie();
            }
            else if(counter == 5){
            	movieService.findMovie();
            }
            else if(counter == 6){
            	movieService.listMovies();
            }else if(counter==7){
            	userService.loanMovie();
            }else if(counter == 0){
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date();
                Date date2 = new Date();
                if(date.compareTo(date2) == -1)
                System.out.println(dateFormat.format(date));
                return;
            }
        }
    }
}
