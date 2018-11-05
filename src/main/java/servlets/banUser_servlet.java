package servlets;

import com.mycompany.unswbook.database;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "banUser",urlPatterns = {"/banUser_servlet"})
public class banUser_servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(action);
        if(action.equals("ban") || action.equals("unban")) {
            int userID = Integer.parseInt(request.getParameter("userID"));
            if (action.equals("ban")) {
                database.banUser(userID);
                String message = "success ban a user!";
                request.setAttribute("message", message);
            } else if (action.equals("unban")) {
                database.unbanUser(userID);
                String message = "success unban a user!";
                request.setAttribute("message", message);
            }

            RequestDispatcher requestdispatcher = request.getRequestDispatcher("/hint.jsp");
            requestdispatcher.forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
