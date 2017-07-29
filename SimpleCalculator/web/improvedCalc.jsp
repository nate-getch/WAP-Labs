<%-- 
    Document   : improvedCalc
    Created on : Jul 28, 2017, 9:57:22 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Improved Calculator</title>
    </head>
    <body>
        <div>
            <form action="ImprovedCalc" method="post">
                <input size="10" type="text" name="add1" value="${add1}"> 
                + 
                <input  size="10" type="text" name="add2" value="${add2}"> 
                = <input disabled size="10" type="text" name="sum" value="${sum}" >  
                <br/> <br/>
                <input  size="10" type="text" name="mul1" value="${mul1}"> 
                * 
                <input  size="10" type="text" name="mul2" value="${mul2}"> 
                = <input disabled size="10" type="text" name="mul" value="${mul}"> 
                <br/><br/>
                <input type="submit" value="Submit">
            </form>
                
            <a href="index.html">Normal Calculator</a>
        </div>
    </body>
</html>
