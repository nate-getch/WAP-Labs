/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.lab;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class ImprovedCalcServlet extends HttpServlet {
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        try {
            long add1 = (!request.getParameter("add1").equals("") ) ? Long.parseLong(request.getParameter("add1").toString()) : 0;
            long add2 = (!request.getParameter("add2").equals("")  ) ?  Long.parseLong(request.getParameter("add2").toString()) : 0;
            long sum = add1+add2;
            
            long mul1 = (!request.getParameter("mul1").equals("") ) ? Long.parseLong(request.getParameter("mul1").toString()) : 0;
            long mul2 = (!request.getParameter("mul2").equals("")  ) ?  Long.parseLong(request.getParameter("mul2").toString()) : 0;
            long mul = mul1*mul2;
            
            request.setAttribute("mul", mul);
            request.setAttribute("sum", sum);
            request.setAttribute("add1", add1);
            request.setAttribute("add2", add2);
            request.setAttribute("mul1", mul1);
            request.setAttribute("mul2", mul2);
            request.getRequestDispatcher("improvedCalc.jsp").forward(request, response);
        }
        catch(NumberFormatException e){
            response.sendRedirect("error.html");
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
        processRequest(request, response);
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
