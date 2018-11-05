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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import java.net.InetAddress;

/**
 *
 * @author yiqunrong
 */
@WebServlet(name = "login_servlet", urlPatterns = {"/login_servlet"})
public class login_servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *
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
        
        String userName = request.getParameter("username");
        if (userName!=null){
            if (userName.equals("")) {
                    userName = "user name cannot be empty";
            }else{
                if(database.checkUserName(userName)){
                    userName="user name have already existed";
                }else{
                    userName="";
                }
            }
            response.setContentType("text/plain");
            response.getWriter().write(userName);
            
        }else{
            String email = request.getParameter("email");
            if (email.equals("")) {
                    email = "email cannot be empty";
            }else{
                if(database.checkEmail(email)){
                    email="email have already existed";
                }else{
                    email="";
                }     
            } 
            response.setContentType("text/plain");
            response.getWriter().write(email);
        }
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
           String userName=request.getParameter("username");
           String password=request.getParameter("password");
           int userID=database.isValidUser(userName, password);
           if (userID>=0){
               ServletContext context = request.getSession().getServletContext();
               String ipaddress="";
               Object ipaddressOj = context.getAttribute("ipaddress");
               if(ipaddressOj==null){
                try{
                    InetAddress IP= InetAddress.getLocalHost();
                    ipaddress=IP.toString();
                }catch (Exception e){
                    System.out.println(e);
                }
                ipaddress=ipaddress.substring(ipaddress.lastIndexOf("/") + 1);
                 context.setAttribute("ipaddress", ipaddress);
               } else {
                   ipaddress = (String)ipaddressOj;
               }
               request.getSession().setAttribute("userID", userID);
               request.getSession().setAttribute("userName", userName);
               userBean u=database.getUserInfo(userID);
               request.getSession().setAttribute("photo",u.photo);
               u.photo= "http://"+ipaddress+":8080/unswbook"+u.photo;
               ArrayList<postBean> postList= database.getPost(userID);
               request.setAttribute("postList", postList);
               request.setAttribute("userInfo", u);
               String url="/homepage.jsp";
               getServletContext().getRequestDispatcher(url).forward(request, response);
           }else{
                String message="";
                    if(userID==-3){
                        message="incorrect username or password!";
                    }else if(userID==-1){
                        message="you are banned!";
                    }else{
                        message="email not comfirmed yet!";
                    }
                request.setAttribute("message", message); 
                String url="/index.jsp";
                getServletContext().
                        getRequestDispatcher(url).
                        forward(request, response);
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
