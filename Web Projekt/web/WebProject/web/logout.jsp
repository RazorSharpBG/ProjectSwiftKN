<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            if (session.getAttribute("fullName") != null) {
                session.removeAttribute("fullName");
                out.print("You were logged out successfully.");
            }
            response.sendRedirect("index.jsp");
        %>
    </body>
</html>
