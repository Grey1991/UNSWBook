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
import javax.servlet.RequestDispatcher;

@WebServlet(name = "checkNotification",urlPatterns = "/checkNotification_servlet")
public class checkNotification_servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userID = (int) request.getSession().getAttribute("userID");
        String flag = request.getParameter("flag");;
        if(database.anyNotificationNotRead(userID)){
            String message = "new notification";
            System.out.print(message);
            response.setContentType("text/plain");
            response.getWriter().write(message);
        }else {
            String message = "no new notification";
            System.out.print(message);
            response.setContentType("text/plain");
            response.getWriter().write(message);
        }
    }
}
