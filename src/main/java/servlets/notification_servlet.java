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

@WebServlet(name = "notification",urlPatterns = "/notification_servlet")
public class notification_servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

   }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userID = (int) request.getSession().getAttribute("userID");
        System.out.println(userID);
        ArrayList<notificationBean> notificationList = database.getNotification(userID);
        boolean r = database.setNotificationIsRead(userID);
        System.out.print(r);

        request.setAttribute("notificationList",notificationList);
        RequestDispatcher requestdispatcher = request.getRequestDispatcher("/notification.jsp");
        requestdispatcher.forward(request, response);
    }
}
