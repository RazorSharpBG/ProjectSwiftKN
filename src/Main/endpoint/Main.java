package Main.endpoint;

import Main.Service.UserServiceImpl;
import Main.Service.MovieServiceImpl;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        int counter = 0;
        Scanner scanner = new Scanner(System.in);
        UserServiceImpl userService = new UserServiceImpl();
        MovieServiceImpl movieService= new MovieServiceImpl();

        while(counter != -1){
            System.out.println("\n---------Menu---------");
            System.out.println("---------Press 1 for all user information---------");
            System.out.println("---------Press 2 to create user---------");
            System.out.println("---------Press 3 to find user---------");
            System.out.println("---------Press 4 to create movie---------");
            System.out.println("---------Press 5 to find movie---------");
            System.out.println("---------Press 6 to list movie---------");
            System.out.println("---------Press 7 to loan movie---------");
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
            }
            else if(counter==7) {
                userService.loanMovie();
            }
            else if(counter == 0){
//                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//                Date date = new Date();
//                Date date2 = new Date();
//                if(date.compareTo(date2) == -1)
//                    System.out.println(dateFormat.format(date));
                return;
            }
        }
    }
}
