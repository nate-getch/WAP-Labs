<%-- 
    Document   : form.jsp
    Created on : Jul 30, 2017, 1:19:54 AM
    Author     : Natnael Demisse
--%>
<% 
    // if this page is directly accessed and session has expired, redirect to servlet to init data
    if(pageContext.findAttribute("quizStatus") ==null){
        response.sendRedirect("quiz");
        return;
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Number Quiz</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <body>
        <div class="container">   
            <h1>The Number Quiz</h1>
            <% if(!pageContext.findAttribute("quizStatus").equals("end")) { %> 
            <form id="quiz_form" method="post" action="quiz">
                <p>Enter Age <input required size="7" type="text" name="age" value="${age}"/></p>
                <p>Your current Score is: ${scoreTotal} </p>
                <p>Guess the next number in the sequence <br/> ${currentQue} </p>
                <p>Your Answer <input required size="7" type="number" name="ans"/></p>
                ${validationMessage}
                <input type="submit" > (Try # ${retryNum})
            </form>
            
            <% } else { %>
            <p>Your Total Score is ${scoreTotal} </p>
            <p>Total Number of Questions: ${quiz.numberOfQue()}</p>
            <p >Grade: <span class="red">${grade}</span> </p>
            <a href="quiz">Click here to Re-Attempt Quiz</a>
            <% } %>
            
        </div>
    </body>
</html>
