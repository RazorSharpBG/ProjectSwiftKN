<%@page import="webprojektkn.UserServiceImpl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <h4>Register</h4>
            <form>
            <div class="form-row">
              <div class="form-group col-md-6">
                <label for="inputEmail4">Username</label>
                <input type="text" name="username" class="form-control" id="inputEmail4" placeholder="Username">
              </div>
              <div class="form-group col-md-6">
                <label for="inputPassword4">Password</label>
                <input type="password" name="password" class="form-control" id="inputPassword4" placeholder="Password">
              </div>
            </div>
            <div class="form-row">
              <div class="form-group col-md-6">
                <label for="inputEmail4">Phone</label>
                <input type="text" name="phone" class="form-control" id="inputEmail4" placeholder="Phone">
              </div>
              <div class="form-group col-md-6">
                <label for="inputPassword4">E-mail</label>
                <input type="email" name="email" class="form-control" id="inputPassword4" placeholder="E-mail">
              </div>
            </div>
            <div class="form-group">
              <label for="inputAddress">Address</label>
              <input type="text" name="address" class="form-control" id="inputAddress" placeholder="1234 Main St, Sofia">
            </div>
            <button type="submit" class="btn btn-primary">Register</button>
        </form>
        <%
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            
            UserServiceImpl user = new UserServiceImpl();
            user.createNewUser(username, password, email, phone, address);
            out.print("<a href=\"./login.jsp\">Log In</a>");
//                response.sendRedirect("login.jsp");

        %>
        </div>
    </body>
</html>
