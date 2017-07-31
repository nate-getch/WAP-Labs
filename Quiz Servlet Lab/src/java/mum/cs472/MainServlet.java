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

        //request.setAttribute("currentQue", quiz.getQuestion(0));
        request.getSession().setAttribute("quizStatus", "start");
        request.getSession().setAttribute("currentQue", quiz.getQuestion(0));
        request.getSession().setAttribute("currentQueNo", 0);
        request.getSession().setAttribute("scoreTotal", 0);
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
        
        int currQ =  Integer.parseInt(request.getSession().getAttribute("currentQueNo").toString());
        int currentScore = Integer.parseInt(request.getSession().getAttribute("scoreTotal").toString());
        int ans = (!request.getParameter("ans").toString().equals("")) ? Integer.parseInt(request.getParameter("ans").toString()) : -1;
        if(ans == quiz.getAnswer(currQ)) {
            request.getSession().setAttribute("scoreTotal", currentScore+1);
        }
        if (quiz.getQuestions().length > currQ+1){
            //request.setAttribute("currentQue", quiz.getQuestion(currQ+1));
            request.getSession().setAttribute("currentQue", quiz.getQuestion(currQ+1));
            request.getSession().setAttribute("currentQueNo", currQ+1);
            request.getSession().setAttribute("quiz", quiz);
            response.sendRedirect("form.jsp");
            // using forward method is posting the form during refresh between forms.. lets use redirect.
           // request.getRequestDispatcher("form.jsp").forward(request, response);
        }
        else{
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
