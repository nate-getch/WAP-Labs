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
 * @author Natnael Getachew
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
        
        request.getSession().setAttribute("quizStatus", "start");
        request.getSession().setAttribute("currentQue", quiz.getQuestion(0));
        request.getSession().setAttribute("currentQueNo", 0);
        request.getSession().setAttribute("scoreTotal", 0);
        generateForm(request,response);
        
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
        
        int currQ =  Integer.parseInt(request.getSession().getAttribute("currentQueNo").toString());
        int currentScore = Integer.parseInt(request.getSession().getAttribute("scoreTotal").toString());
        int ans = (!request.getParameter("ans").toString().equals("")) ? Integer.parseInt(request.getParameter("ans").toString()) : -1;
        if(ans == quiz.getAnswer(currQ)) {
            request.getSession().setAttribute("scoreTotal", currentScore+1);
        }
        if (quiz.getQuestions().length > currQ+1){
            request.getSession().setAttribute("currentQue", quiz.getQuestion(currQ+1));
            request.getSession().setAttribute("currentQueNo", currQ+1);
            request.getSession().setAttribute("quiz", quiz);
        }
        else{
            request.getSession().setAttribute("quizStatus", "end");
        }
        
        generateForm(request,response);
        
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
    protected void generateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        String scoreTotal = request.getSession().getAttribute("scoreTotal").toString();
        String currentQue = request.getSession().getAttribute("currentQue").toString();
        
        String t = "";
        t += "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>Number Quiz</title></head><body>";
        if(!request.getSession().getAttribute("quizStatus").equals("end")) {
            t += "<form id=\"quiz_form\" method=\"post\" action=\"quiz\">";
            t +=  "<p>Your current Score is: "+scoreTotal+" </p>";
            t +=  "<p>Guess the next number in the sequence <br/> "+currentQue+" </p>";
            t +=  "<p>Your Answer <input required size=\"7\" type=\"number\" name=\"ans\"/></p>";
            t +=  "<input type=\"submit\" >";
            t += "</form>";
            
        } else {
            t += "<p>Your Total Score is "+scoreTotal+" </p>";
            t += "<p>You have completed the Quiz, with Score "+scoreTotal+" out of "+quiz.numberOfQue()+"</p>";
            t += "<a href=\"quiz\">Click here to Re-Attempt Quiz</a>";
        }
        t += "</body></html>";
        
        out.print(t);
    }
    
}
