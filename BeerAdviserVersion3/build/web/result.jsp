<%-- 
    Document   : result
    Created on : Aug 2, 2017, 10:47:47 PM
    Author     : Natnael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.*"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 align=”center”>Beer Recommendations JSP</h1>
        <p>
            <c:forEach var="style" items="${styles}">
                <br > try :   ${style}
            </c:forEach>
          <%
        //        List styles = (List) request.getAttribute("styles");
        //        Iterator it = styles.iterator();
        //        while (it.hasNext()) {
        //           out.print("<br > try : " + it.next());
        //    }
            %> 
        </p>           
    </body>
</html>
