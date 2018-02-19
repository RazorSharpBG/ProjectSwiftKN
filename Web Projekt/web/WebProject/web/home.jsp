<%@page import="webprojektkn.Movie"%>
<%@page import="java.util.*"%>
<%@page import="webprojektkn.MovieServiceImpl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WebProject</title>
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <style type="text/css">
        table, td, th { border: 1px solid #bbb; border-collapse: collapse; }    
        </style>
    </head>
    <body>
        
        
        <% String fullName=(String) session.getAttribute("fullName");
           String bgColor=(String) session.getAttribute("background-color");
        %>
        
        <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
            <h5 class="my-0 mr-md-auto font-weight-normal">Nai-qkiq sait</h5>
            <a class="btn btn-outline-primary" href="#">
                <%=fullName%>
            </a>
        </div>
        
        <div class="container">
        <table class="table">
            <thead class="thead-dark">
                <tr>
                    <th>Title</th>
                    <th>Loan</th>
                    <th>Available</th>
                    <th>Available for days</th>
                </tr>
            </thead>
            <tbody>
                <%
                    MovieServiceImpl msi = new MovieServiceImpl();
                    List<Movie> list = msi.exportMoviesToList();

                    for (Movie movie : list) {
                        out.print("<tr>");
                        out.print("<td>" + movie.title + "</td>");
                        out.print("<td>" + movie.loan + "</td>");
                        out.print("<td>" + movie.available + "</td>");
                        out.print("<td>" + movie.available_for_days + "</td>");
                        
                        out.print("<td></td>");
                        out.print("</tr>");
                    }

                %>
            </tbody>
        </table>
        <a href="logout.jsp">Logout</a>
        </div>
    </body>
</html>
