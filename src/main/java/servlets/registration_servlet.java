/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import javax.servlet.jsp.*;
import java.io.*;
import java.net.InetAddress;
import java.util.*;
import javax.servlet.*;
import com.mycompany.unswbook.*;
import java.io.IOException;
import java.util.Properties;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.text.SimpleDateFormat;
//unswbook69
//96unswbook69
/**
 *
 * @author yiqunrong
 */
@WebServlet(name = "registration", urlPatterns = {"/registration_servlet"})
public class registration_servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username=request.getParameter("username");
        String email=request.getParameter("email");
        String url="";
        boolean isOK=true;
        if (username!=null){
            if (database.checkUserName(username))
            {
                url="/index.jsp";
                isOK=false;
            }
        }else if(email!=null){
            if (database.checkEmail(email)){
                url="/index.jsp";
                isOK=false;
            }
        }
            
        request.setAttribute("isOK", isOK);
                getServletContext().
                        getRequestDispatcher(url).
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



        String username=request.getParameter("username");
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        String yourName=request.getParameter("yourName");
        String gender=request.getParameter("gender");
        String DoB=request.getParameter("DoB");
        String message="database fail";
        if (fileName == "") {
            fileName = "/photo/default_photo.png";
        }
        if(database.addUser(username, password, email, yourName, gender, DoB, fileName)){
            message="emailSender fail";
            int uid = database.getUserID(username);
            String uids=Integer.toString(uid);
            String link="http://"+ipaddress+":8080/unswbook/confirmEmail.jsp?userID="+uids;
            String href="<a href="+link+">please click on following hyper link for confirmation</a>";
            emailSender emailSender=new emailSender(email,"confirmation for registration", "<h>welcome to unswBook</h>  "+href, "");
            if (emailSender.send()){
                message="the confirmation email have been sent, please check your email.";
            }
        }
        String url="/index.jsp";
        request.setAttribute("message", message);
        getServletContext().
                getRequestDispatcher(url).
                forward(request, response);
       
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */


    }
}
