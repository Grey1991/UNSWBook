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

@WebServlet(name = "adminsearch", urlPatterns = {"/adminsearch_servlet"})
public class adminsearch_servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(action);

        if (action.equals("search")||action.equals("show_all")) {
            String userName = request.getParameter("userName");
            String yourName = request.getParameter("yourName");
            String email = request.getParameter("email");
            String gender = request.getParameter("gender");
            String DoB = request.getParameter("DoB");
            int page = 0;
            int total = 0;

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
                ArrayList<userBean>  LogList = database.advancedSearch(userName,yourName,email,gender,DoB);
                ArrayList<userBean>  SubLogList = new ArrayList<>();
                request.getSession().setAttribute("result_list", LogList);
                if (LogList.size() == 0){
                    System.out.println("found nothing");
                    request.setAttribute("content", "nothing");
                } else if(LogList.size() < 10){
                    total = LogList.size();
                    request.setAttribute("result_list", LogList);
                }else{
                    total = LogList.size();
                    for (int i=0; i<10; i++) {
                        SubLogList.add(LogList.get(i));
                    }
                    request.setAttribute("result_list", SubLogList);
                }

            }
            request.setAttribute("page", page);
            request.setAttribute("total", total);

            RequestDispatcher requestdispatcher = request.getRequestDispatcher("/admin_search_results.jsp");
            requestdispatcher.forward(request, response);
        }
        else if (action.equals("Next") || action.equals("Previous")) {
            int page = Integer.parseInt(request.getParameter("page"));
            ArrayList<userBean> result_list = (ArrayList<userBean>) request.getSession().getAttribute("result_list");
            ArrayList<userBean> SubLogList = new ArrayList<>();
            if (action.equals("Next")) {
                page += 1;
                if (result_list.size() <= (page + 1)*10) {
                    for (int x = page*10; x < result_list.size(); x++) {
                        SubLogList.add(result_list.get(x));
                    }
                } else {
                    for (int x = page*10; x < page*10 + 10; x++) {
                        SubLogList.add(result_list.get(x));
                    }
                }
            } else if (action.equals("Previous")) {
                page -= 1;
                for (int x = page*10; x < page*10 + 10; x++) {
                    SubLogList.add(result_list.get(x));
                }
            }
            request.setAttribute("result_list", SubLogList);
            request.setAttribute("page", page);
            request.setAttribute("total", result_list.size());

            RequestDispatcher requestdispatcher = request.getRequestDispatcher("/admin_search_results.jsp");
            requestdispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
