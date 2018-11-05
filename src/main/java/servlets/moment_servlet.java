package servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.unswbook.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author yiqunrong
 */
@WebServlet(name = "moment", urlPatterns = {"/moment_servlet"})
public class moment_servlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet moment_servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet moment_servlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        int userID = (int) request.getSession().getAttribute("userID");
        System.out.println(userID);

        ArrayList<postBean> postList = database.getMoment(userID);
        request.setAttribute("momentList",postList);
        request.setAttribute("userID",userID);
        String ipaddress=(String)request.getSession().getServletContext().getAttribute("ipaddress");
        request.setAttribute("ipaddress", ipaddress);
        RequestDispatcher requestdispatcher = request.getRequestDispatcher("/moment.jsp");
        requestdispatcher.forward(request, response);

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
        System.out.print(action);

        if (action.equals("like")){
            int postID = Integer.parseInt( request.getParameter("postID"));
            int userID = (int) request.getSession().getAttribute("userID");
            String userName = (String) request.getSession().getAttribute("userName");
            String ownername = request.getParameter("postowner");
            ArrayList<userBean> ownerList= database.advancedSearch(ownername,"","","","");
            userBean owner = ownerList.get(0);
            ArrayList<userBean> senderList = database.advancedSearch(userName,"","","","");
            userBean sender = senderList.get(0);
            String senderName = sender.yourName;
            int ownerID = owner.userID;
            String content = senderName + " like your post";
            System.out.print(postID);
            System.out.print(userID);
            System.out.print(content);
            database.addLike(userID,postID);
            database.addNotification(content,userID,ownerID);
            database.addLog(userID,"like a post, postID: " + request.getParameter("postID"));
            ArrayList<postBean> postList = database.getMoment(userID);
            request.setAttribute("momentList",postList);
            request.setAttribute("userID",userID);
        }else if(action.equals("unlike")){
            int postID = Integer.parseInt( request.getParameter("postID"));
            int userID = (int) request.getSession().getAttribute("userID");
            String userName = (String) request.getSession().getAttribute("userName");
            String ownername = request.getParameter("postowner");
            System.out.print(ownername);
            ArrayList<userBean> ownerList= database.advancedSearch(ownername,"","","","");
            userBean owner = ownerList.get(0);
            ArrayList<userBean> senderList = database.advancedSearch(userName,"","","","");
            userBean sender = senderList.get(0);
            String senderName = sender.yourName;
            int ownerID = owner.userID;
            String content = senderName + " unlike your post";
            System.out.print(postID);
            System.out.print(userID);
            System.out.print(content);
            database.unLike(userID,postID);
            database.addNotification(content,userID,ownerID);
            database.addLog(userID,"unlike a post, postID: " + request.getParameter("postID"));
            ArrayList<postBean> postList = database.getMoment(userID);
            request.setAttribute("momentList",postList);
            request.setAttribute("userID",userID);
        }
        String ipaddress=(String)request.getSession().getServletContext().getAttribute("ipaddress");
        request.setAttribute("ipaddress", ipaddress);
        RequestDispatcher requestdispatcher = request.getRequestDispatcher("/moment.jsp");
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