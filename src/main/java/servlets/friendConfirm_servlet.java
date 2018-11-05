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

@WebServlet(name = "friendConfirm", urlPatterns = {"/friendConfirm_servlet"})
public class friendConfirm_servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int senderID = Integer.parseInt(request.getParameter("senderID"));
        int receiverID = Integer.parseInt(request.getParameter("receiverID"));
        String receiverName = request.getParameter("receiverName");
        database.friendConfirm(senderID, receiverID);
        database.addLog(senderID, "Add user " + request.getParameter("receiverID") + " " + receiverName + " as friend.");
        database.addNotification(receiverName + " has accepted your request.", receiverID, senderID);
        request.setAttribute("accpet", "Request Accept");
        RequestDispatcher requestdispatcher = request.getRequestDispatcher("/accept.jsp");
        requestdispatcher.forward(request, response);

    }
}
