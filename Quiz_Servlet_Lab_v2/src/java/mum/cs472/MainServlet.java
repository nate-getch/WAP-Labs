/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package mum.cs472;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Natnael Demisse
 */
public class MainServlet extends HttpServlet {
    
    private Quiz quiz = new Quiz();
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //request.setAttribute("currentQue", quiz.getQuestion(0));
        request.getSession().setAttribute("quizStatus", "start");
        request.getSession().setAttribute("currentQue", quiz.getQuestion(0));
        request.getSession().setAttribute("queHint", quiz.getQuestionHint(0));
        request.getSession().setAttribute("currentQueNo", 0);
        request.getSession().setAttribute("scoreTotal", 0);
        request.getSession().setAttribute("validationMessage", "");
        request.getSession().setAttribute("age", 0);
        request.getSession().setAttribute("retryNum", 1);
        request.getSession().setAttribute("grade", "");
        //request.getRequestDispatcher("form.jsp").forward(request, response);
        response.sendRedirect("form.jsp");
        
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // if session has expired/null, lets refresh the page
        if(request.getSession().getAttribute("currentQueNo") == null ){
            request.getSession().invalidate();
            response.sendRedirect("quiz");
            return;
        }
        
        int ans = 0, age = 0;
        try{
            ans = Integer.parseInt(request.getParameter("ans"));
            age = Integer.parseInt(request.getParameter("age"));
            request.getSession().setAttribute("age", age);
        }
        catch(NumberFormatException e) {
            request.getSession().setAttribute("validationMessage", "<p class=\"red\">Please enter valid Number</p>");
            response.sendRedirect("form.jsp");
            return;
        }
        
        if(age<4 || age>100){
            request.getSession().setAttribute("validationMessage", "<p class=\"red\">Age should be greater than 4 and less than 100</p>");
            response.sendRedirect("form.jsp");
            return;
        }
        
        request.getSession().setAttribute("validationMessage","");
        int currQ =  Integer.parseInt(request.getSession().getAttribute("currentQueNo").toString());
        int currentScore = Integer.parseInt(request.getSession().getAttribute("scoreTotal").toString());
        int retryNum = Integer.parseInt(request.getSession().getAttribute("retryNum").toString());
        
        if(ans == quiz.getAnswer(currQ)) {
            if(retryNum==1){
                currentScore+=10;
                request.getSession().setAttribute("scoreTotal", currentScore);
            }
            else if (retryNum==2){
                currentScore+=5;
                request.getSession().setAttribute("scoreTotal", currentScore);
            }
            else if (retryNum==3){
                currentScore+=2;
                request.getSession().setAttribute("scoreTotal", currentScore);
            }
            request.getSession().setAttribute("retryNum",1);
            //request.getSession().setAttribute("scoreTotal", currentScore+1);
        }
        else{
            
            request.getSession().setAttribute("retryNum",retryNum+1);
            // retry starts from 0, and user cant retry more than three times.
            if(retryNum < 3) {
                response.sendRedirect("form.jsp");
                return;
            }
            else{
                request.getSession().setAttribute("retryNum",1);
            }
        }
        
        request.getSession().setAttribute("quiz", quiz);
        if (quiz.getQuestions().length > currQ+1){
            //request.setAttribute("currentQue", quiz.getQuestion(currQ+1));
            request.getSession().setAttribute("currentQue", quiz.getQuestion(currQ+1));
            request.getSession().setAttribute("queHint", quiz.getQuestionHint(currQ+1));
            request.getSession().setAttribute("currentQueNo", currQ+1);
            response.sendRedirect("form.jsp");
            // using forward method is posting the form during refresh between forms.. lets use redirect.
            // request.getRequestDispatcher("form.jsp").forward(request, response);
        }
        else{
            request.getSession().setAttribute("grade",quiz.generateGrade(currentScore));
            request.getSession().setAttribute("quizStatus", "end");
            response.sendRedirect("form.jsp");
            
        }
        
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
