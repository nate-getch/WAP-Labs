<%-- 
    Document   : form.jsp
    Created on : Jul 30, 2017, 1:19:54 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Number Quiz</title>
        
    </head>
    <body>
        <div>   
            <h1>The Number Quiz</h1>
            <form id="quiz_form" method="post" action="quiz">
                <p>Your current Score is: ${scoreTotal} </p>
                <p>Guess the next number in the sequence <br/> ${currentQue} </p>
                <p>Your Answer <input required size="7" type="number" name="ans"/></p>
                <input type="submit" >
            </form>
        </div>
    </body>
</html>
