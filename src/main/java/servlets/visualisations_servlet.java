/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;
import com.mycompany.unswbook.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yiqunrong
 */
@WebServlet(name = "viusalisation_servlet", urlPatterns = {"/visualisations_servlet"})
public class visualisations_servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

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
        String action = request.getParameter("action");
        System.out.println(action);
        String ipaddress=(String)request.getSession().getServletContext().getAttribute("ipaddress");
        
        
        if (action.equals("searchForPeople")||action.equals("show_all")) {
            String userName = request.getParameter("userName");
            String yourName = request.getParameter("yourName");
            String email = request.getParameter("email");
            String gender = request.getParameter("gender");
            String DoB = request.getParameter("DoB");

            if (userName == null){
                userName = "";
            }
            if (yourName == null){
                yourName = "";
            }
            if (email == null){
                email = "";
            }
            if (gender == null){
                gender = "";
            }
            if (DoB == null){
                DoB = "";
            }

            if (action.equals("search") && userName == "" && yourName == "" &&
                    email == "" && gender == "" && DoB == ""){
                System.out.println("User did not key in anything");
                request.setAttribute("content", "nothing");
            } else{
               ArrayList<ArrayList<String>> peopleNmessage=new ArrayList<ArrayList<String>>();
               ArrayList<ArrayList<String>> edgeReturn=new ArrayList<ArrayList<String>>();
               database.graphPeopleSearch(peopleNmessage, edgeReturn, userName, yourName, email, gender, DoB);
               request.setAttribute("peopleNmessage", peopleNmessage);
               request.setAttribute("edgeReturn", edgeReturn);
            }            
        }else if(action.equals("searchForMessage")){
            String message = request.getParameter("message");
            ArrayList<ArrayList<String>> peopleNmessage=new ArrayList<ArrayList<String>>();
            ArrayList<ArrayList<String>> edgeReturn =new ArrayList<ArrayList<String>>();
            database.graphMessageSearch(peopleNmessage, edgeReturn, message);
            request.setAttribute("peopleNmessage", peopleNmessage);
            request.setAttribute("edgeReturn", edgeReturn);         
        }else if(action.equals("searchForFriendsOfFriends")){
            int userID=(int)request.getSession().getAttribute("userID");
            ArrayList<ArrayList<String>> peopleNmessage=new ArrayList<ArrayList<String>>();
            ArrayList<ArrayList<String>> edgeReturn =new ArrayList<ArrayList<String>>();
            database.graphFriendSearch(peopleNmessage, edgeReturn, userID);
            request.setAttribute("peopleNmessage", peopleNmessage);
            request.setAttribute("edgeReturn", edgeReturn);     
        }
        request.setAttribute("ipaddress", ipaddress);
        RequestDispatcher requestdispatcher = request.getRequestDispatcher("/graph.jsp");
            requestdispatcher.forward(request, response);
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
