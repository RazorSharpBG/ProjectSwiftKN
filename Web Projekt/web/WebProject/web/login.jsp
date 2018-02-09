<%@page import="webprojektkn.MovieServiceImpl"%>
<%@page import="webprojektkn.UserServiceImpl"%>
<%@page import="webprojektkn.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>web</title>
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>
        <div class="container" style="margin-top: 10%">
            <h5>Hello :) ! Please login or create a new account!</h5>
            <form method="post" action="login.jsp">
                <div class="form-group">
                  <label for="exampleInputEmail1">Username</label>
                  <input type="text" name="username" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
                </div>
                <div class="form-group">
                  <label for="exampleInputPassword1">Password</label>
                  <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                </div>
                <button type="submit" class="btn btn-primary">Login</button>
                <a href="./register.jsp">Register</a>
            </form>
<!--        <form method="post" action="index.jsp">
            <label>Enter Username:</label>
            <input type="text" name="username" value=""/>
            </br>
            </br>
            <label>Enter Password:</label>
            <input type="password" name="password" value=""/>
            </br>
            </br>
            <input type="submit" value="Login"/>
            </br>
            </br>
            <input type="submit" value="Create Account"/>
        </form>-->
        <%
            Boolean failed =(Boolean) session.getAttribute("authentication-failed");
            if(failed!=null && failed){
                out.print("Failed authentication!");
            }
        %>
        <%
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            UserServiceImpl user = new UserServiceImpl(); 
            User found_user = user.findUser(username, password);
            
            if(found_user != null){
                session.setAttribute("fullName", found_user.getName());
                session.setAttribute("background-color","#A9E2F3");
                session.setAttribute("authentication-failed",false);
                response.sendRedirect("home.jsp");
                out.print(found_user.getName());
                out.print(found_user.getName());
            }else{
                session.setAttribute("authentication-failed", true);
            }
        %>
        </div>
    </body>
</html>
