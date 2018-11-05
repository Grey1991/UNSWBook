package servlets;

import com.mycompany.unswbook.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "showLog",urlPatterns = {"/showLog_servlet"})
public class showLog_servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        ArrayList<logBean> LogList = database.searchLog(userName,"","","","");

        request.setAttribute("LogList", LogList);

        RequestDispatcher requestdispatcher = request.getRequestDispatcher("/log_report.jsp");
        requestdispatcher.forward(request, response);

    }
}
