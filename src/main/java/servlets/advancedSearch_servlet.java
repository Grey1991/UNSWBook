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
import javax.servlet.http.HttpSession;

/**
 *
 * @author yiqunrong
 */
@WebServlet(name = "advancedSearch", urlPatterns = {"/advancedSearch_servlet"})
public class advancedSearch_servlet extends HttpServlet {

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

        String ipaddress=(String)request.getSession().getServletContext().getAttribute("ipaddress");
        request.setAttribute("ipaddress", ipaddress);

        String action = request.getParameter("action");
        System.out.println("Action is: " + action);

        if (action.equals("search")||action.equals("show_all")){
            String userName = request.getParameter("userName");
            String yourName = request.getParameter("yourName");
            String email = request.getParameter("email");
            String gender = request.getParameter("gender");
            String DoB = request.getParameter("DoB");
            int page = 0;
            int total = 0;


//            System.out.println(userName);

//            type nothing, return nothing
            if (action.equals("search") && userName == "" && yourName == "" &&
                    email == "" && gender == "" && DoB == ""){
                System.out.println("User did not key in anything");
                request.setAttribute("content", "nothing");
//                request.setAttribute("result_full_list", "nothing");

            } else {
                System.out.println("User input is not empty");
                ArrayList<userBean> result_list = database.advancedSearch(userName, yourName, email,
                        gender, DoB);
//                System.out.println(result_list.size());
//                System.out.println(result_list.get(0).userName);
                ArrayList<userBean> subresult_list = new ArrayList<>();
                request.getSession().setAttribute("result_list", result_list);
                if (result_list.size() == 0) {
                    System.out.println("found nothing");
                    request.setAttribute("content", "nothing");
//                    request.setAttribute("result_full_list", "nothing");

                } else if (result_list.size() <= 12) {
                    total = result_list.size();
                    request.setAttribute("result_list", result_list);
//                    request.setAttribute("result_full_list", result_list);

                } else {
                    total = result_list.size();
                    for (int i=0; i<12; i++) {
                        subresult_list.add(result_list.get(i));
                    }
                    request.setAttribute("result_list", subresult_list);
//                    request.setAttribute("result_full_list", result_list);
                }
            }
            request.setAttribute("page", page);
            request.setAttribute("total", total);

            RequestDispatcher requestdispatcher = request.getRequestDispatcher("/results.jsp");
            requestdispatcher.forward(request, response);

        } else if (action.equals("simple_search")) {
            String yourName = request.getParameter("yourName");
            int page = 0;
            int total = 0;
            System.out.println(yourName);

//            type nothing, return nothing
            if (yourName == ""){
                System.out.println("User did not key in anything");
                request.setAttribute("content", "nothing");

            } else {
                System.out.println("User input is not empty");
                ArrayList<userBean> result_list = database.searchByYourName(yourName);
//                System.out.println(result_list.size());
//                System.out.println(result_list.get(0).userName);
                ArrayList<userBean> subresult_list = new ArrayList<>();
                request.getSession().setAttribute("result_list", result_list);
                if (result_list.size() == 0) {
                    System.out.println("found nothing");
                    request.setAttribute("content", "nothing");
//                    request.setAttribute("result_full_list", "nothing");

                } else if (result_list.size() <= 12) {
                    total = result_list.size();
                    request.setAttribute("result_list", result_list);
//                    request.setAttribute("result_full_list", result_list);

                } else {
                    total = result_list.size();
                    for (int i=0; i<12; i++) {
                        subresult_list.add(result_list.get(i));
                    }
                    request.setAttribute("result_list", subresult_list);
//                    request.setAttribute("result_full_list", result_list);
                }
            }
            request.setAttribute("page", page);
            request.setAttribute("total", total);

            RequestDispatcher requestdispatcher = request.getRequestDispatcher("/results.jsp");
            requestdispatcher.forward(request, response);

        }else if (action.equals("Next") || action.equals("Previous")) {
            int page = Integer.parseInt(request.getParameter("page"));
            ArrayList<userBean> result_list = (ArrayList<userBean>) request.getSession().getAttribute("result_list");
            ArrayList<userBean> subresult_list = new ArrayList<>();
            if (action.equals("Next")) {
                page += 1;
                if (result_list.size() <= (page + 1)*12) {
                    for (int x = page*12; x < result_list.size(); x++) {
                        subresult_list.add(result_list.get(x));
                    }
                } else {
                    for (int x = page*12; x < page*12 + 12; x++) {
                        subresult_list.add(result_list.get(x));
                    }
                }
            } else if (action.equals("Previous")) {
                page -= 1;
                for (int x = page*12; x < page*12 + 12; x++) {
                    subresult_list.add(result_list.get(x));
                }
            }
            request.setAttribute("result_list", subresult_list);
            request.setAttribute("page", page);
            request.setAttribute("total", result_list.size());

            RequestDispatcher requestdispatcher = request.getRequestDispatcher("/results.jsp");
            requestdispatcher.forward(request, response);
        } else if (action.equals("addfriend")) {
            System.out.println("addfriend");
            int friend_id = Integer.parseInt(request.getParameter("friend_id"));
//            int userID = (int) request.getSession().getAttribute("userID");
            int userID = Integer.parseInt(request.getSession().getAttribute("userID").toString());
            String user_yourName = request.getSession().getAttribute("userName").toString();
            String friend_yourName = request.getParameter("friend_yourName");
            String friend_email = request.getParameter("friend_email");

            database.addFriend(friend_id, userID);
            String friendConfirmPage = "<p>"+user_yourName+" want add you as his/her friend in UNSWBOOK, click this link to accept this request</p>\n" +
                    "<a href=\"http://"+ipaddress+":8080/unswbook/friendConfirm_servlet?senderID="+Integer.toString(userID)+"&receiverID="+Integer.toString(friend_id)+"&receiverName="+friend_yourName+"\">Accept</a>";
            emailSender friendRequest = new emailSender(friend_email, "FriendRequest", friendConfirmPage, "");
            //friendRequest.send();
            database.addLog(userID, "add user " + request.getParameter("friend_id") + friend_yourName + " as friend");
            request.setAttribute("email_sent", "email_sent");

            RequestDispatcher requestdispatcher = request.getRequestDispatcher("/wait_confirm.jsp");
            requestdispatcher.forward(request, response);
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
