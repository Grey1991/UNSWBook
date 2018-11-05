/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.mycompany.unswbook.*;

import java.io.*;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import unsw.curation.api.tokenization.*;


/**
 *
 * @author yiqunrong
 */
@WebServlet(name = "myPage", urlPatterns = {"/myPage_servlet"})
public class myPage_servlet extends HttpServlet {

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
        String url="/homepage.jsp";
        int userID=(int)request.getSession().getAttribute("userID");
        String ipaddress=(String)request.getSession().getServletContext().getAttribute("ipaddress");

        userBean u=database.getUserInfo(userID);
        ArrayList<postBean> postList= database.getPost(userID);
        request.setAttribute("postList", postList);
        request.setAttribute("ipaddress", ipaddress);

        u.photo= "http://"+ipaddress+":8080/unswbook"+u.photo;
        request.setAttribute("userInfo", u);


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
        String url="/homepage.jsp";
        int userID=Integer.parseInt(request.getSession().getAttribute("userID").toString());
        String userName = request.getSession().getAttribute("userName").toString();
        String adminEmail = "grey1991ss@gmail.com";
        if(request.getParameter("postID")!=null){
            database.deletePost(Integer.parseInt(request.getParameter("postID")));
            database.addLog(userID,"delete post: postID "+request.getParameter("postID"));
        }else{
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
            }


            //create a post//

            String postContent=request.getParameter("postContent");
//            System.out.println(postContent);

            //extract keywords//
            ExtractionKeywordImpl keyWord= new ExtractionKeywordImpl();
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("/stopWords.txt").getFile());
            String key="";
            try {
                key = keyWord.ExtractSentenceKeyword(postContent,file);
                System.out.println("keywords is: "+ key);
            }catch(Exception e){
                System.out.println("Here is B ");
            }

            //check if contains bullying words//
            ExtractionKeywordImpl keyWord2= new ExtractionKeywordImpl();
            ClassLoader classLoader2 = getClass().getClassLoader();
            File file2 = new File(classLoader2.getResource("/bullyingWords.txt").getFile());
            int bullyingFlag = 0;
            try {
                String normalWords = keyWord2.ExtractSentenceKeyword(key,file2);
                System.out.println("normalWords is "+ normalWords);
                if (!normalWords.equals(key)) {
                    System.out.println("contain bullying words");
                    bullyingFlag = 1;
                    //send email to admin//
                    String bullyingNotificationPage = "<p>User "+userName+" just post a message: \""+postContent.trim()+"\" " +
                        "which contains bullying words.</p>\n";
                    emailSender bullyingNotification = new emailSender(adminEmail, "Notification", bullyingNotificationPage, "");
                    bullyingNotification.send();
                }
            }catch(Exception e){
                System.out.println("Here is B ");
            }

            //add log for post//
            if(fileName!="" || postContent.trim().length() > 0){
                request.setAttribute("message", "hello");
                database.createPost(userID, postContent.trim(), fileName);
                if (bullyingFlag == 0) {
                    database.addLog(userID, "add post: title " + postContent.trim() + " photo: " + fileName);
                }else {
                    database.addLog(userID, "add bullying post: title " + postContent.trim() + " photo: " + fileName);
                }
            }
        }
        userBean u=database.getUserInfo(userID);
        ArrayList<postBean> postList= database.getPost(userID);
        request.setAttribute("postList", postList);

        String ipaddress=(String)request.getSession().getServletContext().getAttribute("ipaddress");
        request.setAttribute("ipaddress", ipaddress);

        u.photo= "http://"+ipaddress+":8080/unswbook"+u.photo;
        request.setAttribute("userInfo", u);
        getServletContext().
                getRequestDispatcher(url).
                forward(request, response); 
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
