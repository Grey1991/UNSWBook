/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.mycompany.unswbook.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import static javax.servlet.SessionTrackingMode.URL;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sun.misc.IOUtils;

/**
 *
 * @author yiqunrong
 */
@WebServlet(name = "modification", urlPatterns = {"/modification_servlet"})
public class modification_servlet extends HttpServlet {

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
        int userID=(int)request.getSession().getAttribute("userID");
        userBean u=database.getUserInfo(userID);
        request.getSession().setAttribute("photo",u.photo);
        String ipaddress=(String)request.getSession().getServletContext().getAttribute("ipaddress");
        request.setAttribute("ipaddress", ipaddress);
        u.photo= "http://"+ipaddress+":8080/unswbook"+u.photo;
        request.setAttribute("userInfo", u);
        String urle="/modification.jsp";
        getServletContext().
            getRequestDispatcher(urle).
                     forward(request, response);
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
            String fileName="";
            if (request.getPart("file")!=null) {
                 try { 
                    // Parse the request to get file items.
                     InputStream fis=request.getPart("file").getInputStream();
                     InputStream fis2=request.getPart("file").getInputStream();
                     // Get the uploaded file parameters
                     String header = request.getPart("file").getHeader("content-disposition");
                     String[] tokens = header.split(";");
                     fileName="hello.data";
                     for (String token : tokens) {
                         if (token.trim().startsWith("filename")) {
                              fileName=token.substring(token.indexOf("=") + 2, token.length()-1);
                              break;
                         }
                     }
                     long sizeInBytes = request.getPart("file").getSize();
                     // Write the file
                     boolean isPicture=false;
                     try {
                         ImageIO.read(fis).toString();
                         isPicture=true;
                         // It's an image (only BMP, GIF, JPG and PNG are recognized).
                     } catch (Exception e) {
                         fileName="";
                         // It's not an image.
                     }
                     if(isPicture){
                         SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                         String timeStampString  = dateFormat.format(new Date());
                         String back=fileName.substring(fileName.lastIndexOf(".") + 1);
                         String tfront=(fileName+timeStampString);
                         String code=Integer.toHexString(tfront.hashCode());
                         fileName="/photo/"+code+'.'+back;
                         OutputStream rf= new FileOutputStream(getServletContext().getRealPath(".")+fileName);
                         byte[] bufferData = new byte[(int)sizeInBytes];
                         int read=0;
                         while((read = fis2.read(bufferData))!= -1){
                                 rf.write(bufferData, 0, read);
                         }
                         rf.flush();
                         rf.close();
                         fis.close();
                         fis2.close();
                     }
                 } catch(Exception ex) {
              System.out.println(ex);
           }


             String yourName=request.getParameter("yourName");
             String gender=request.getParameter("gender");
             String DoB=request.getParameter("DoB");
             userBean u=new userBean();
             u.setUserID(Integer.parseInt(request.getSession().getAttribute("userID").toString()));
             String ph=(String)request.getSession().getAttribute("photo");
             u.yourName=yourName;
             u.gender=gender;
             u.DoB=DoB;
             if(fileName!=""){
                u.setPhoto(fileName);
                request.setAttribute("photo",fileName);
             }else{
                u.setPhoto(ph);
             }

                String ipaddress=(String)request.getSession().getServletContext().getAttribute("ipaddress");
                request.setAttribute("ipaddress", ipaddress);

             database.update(u);
             userBean k=database.getUserInfo((int)request.getSession().getAttribute("userID"));
             k.photo=u.photo= "http://"+ipaddress+":8080/unswbook"+u.photo;
             int userID=(int)request.getSession().getAttribute("userID");
             database.addLog(userID, "user profile changed");
             request.setAttribute("userInfo", k);
             ArrayList<postBean> postList= database.getPost(userID);
             request.setAttribute("postList", postList);

             String url="/homepage.jsp";
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
