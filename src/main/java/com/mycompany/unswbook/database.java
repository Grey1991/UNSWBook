/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unswbook;

/**
 *
 * @author yiqunrong
 */
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;
import java.sql.*;

public class database {

    static private Connection conn = null;
    static private PreparedStatement stmt = null;
    static private ResultSet rs = null;

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/u";

    static final String USER = "root";
    static final String PASS = "";

    static {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
//        boolean r = database.checkUserName("aaa");
//        boolean r = database.checkEmail("aaa@test.com");
//        boolean r = database.addUser("ggg", "234", "ggg@test.com", "c_yn", "male", "19880214", "c_url");
//        int r = database.isValidUser("aaa", "123");
//        ArrayList<userBean> r = database.searchByYourName("y");
//        boolean r = database.createPost(1, "test_insert_content", "test_insert_url");
//        boolean r = database.deletePost(10);
//        ArrayList<userBean> r = database.advancedSearch("aaa", "", "", "", "");
//        boolean r = database.checkFriend(2, 1);
//        boolean r = database.addFriend(1, 2);
//        ArrayList<userBean> r = database.findAllFriends(8);
//        userBean r = database.getUserInfo(8);
//        boolean r = database.checkLike(7, 1);
//        boolean r = database.addLike(8, 6);
//        userBean t = new userBean();
//        t.userID = 7;
//        t.yourName = "ccc_yn";
//        t.gender = "female";
//        t.DoB = "19880215";
//        t.photo = "ccc_url";
//        boolean r = database.update(t);
//        ArrayList<notificationBean> r = getNotification(2);
//        boolean r = friendConfirm(7, 8);
//        boolean r = isValidAdmin("sijilao", "123");
//        ArrayList<logBean> r = searchLog(null, "y", null, null, null);
//        ArrayList<logBean> r = searchAllLog();
//        boolean r = database.checkBan(8);
//        boolean r = database.banUser(8);
//        boolean r = database.unbanUser(8);
//        ArrayList<postBean> r = getPost(1);
//
//        System.out.print("return: " + r);
//        addUsers(500);

        ArrayList<ArrayList<String>> peopleNmessge = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> edgeReturn = new ArrayList<ArrayList<String>>();
        Integer userID = 1;

        graphFriendSearch(peopleNmessge, edgeReturn, userID);

        database.close();
    }

    private static void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean checkUserName(String userName) {                     // completely matching
        try {
            stmt = conn.prepareStatement("SELECT * FROM users where userName = ?");
            stmt.setString(1, userName);
            rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.print(rs.getInt(1) + ", ");
                System.out.print(rs.getString(2) + ", ");
                System.out.print(rs.getString(3) + ", ");
                System.out.print(rs.getString(4) + ", ");
                System.out.print(rs.getString(5) + ", ");
                System.out.print(rs.getString(6) + ", ");
                System.out.print(rs.getString(7) + ", ");
                System.out.print(rs.getString(8) + ", ");
                System.out.print(rs.getInt(9) + ", ");
                System.out.println(rs.getInt(10));
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkEmail(String email) {                           // completely matching
        try {
            stmt = conn.prepareStatement("SELECT * FROM users where email = ?");
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.print(rs.getInt(1) + ", ");
                System.out.print(rs.getString(2) + ", ");
                System.out.print(rs.getString(3) + ", ");
                System.out.print(rs.getString(4) + ", ");
                System.out.print(rs.getString(5) + ", ");
                System.out.print(rs.getString(6) + ", ");
                System.out.print(rs.getString(7) + ", ");
                System.out.print(rs.getString(8) + ", ");
                System.out.print(rs.getInt(9) + ", ");
                System.out.println(rs.getInt(10));
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean addUser(String userName, String password, String email, String yourName, String gender, String DoB, String photo) {
        try {
            stmt = conn.prepareStatement("INSERT INTO users (userName, password, email, yourName, gender, DoB, photo)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, userName);
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.setString(4, yourName);
            stmt.setString(5, gender);
            stmt.setString(6, DoB);
            stmt.setString(7, photo);
            int row = stmt.executeUpdate();
            System.out.println("row added: " + row);
            return true;
        } catch (Exception e) {
            System.out.println("invalid data.");
            return false;
        }
    }

    public static int isValidUser(String userName,String password) {    //change
        try {
            stmt = conn.prepareStatement("SELECT * FROM users where userName = ? and password = ?");
            stmt.setString(1, userName);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.print(rs.getInt(1) + ", ");
                System.out.print(rs.getString(2) + ", ");
                System.out.print(rs.getString(3) + ", ");
                System.out.print(rs.getString(4) + ", ");
                System.out.print(rs.getString(5) + ", ");
                System.out.print(rs.getString(6) + ", ");
                System.out.print(rs.getString(7) + ", ");
                System.out.print(rs.getString(8) + ", ");
                System.out.print(rs.getInt(9) + ", ");
                System.out.println(rs.getInt(10));

                if (rs.getBoolean("ban")) {
                    System.out.println("ban");
                    return -1;
                } else if (!rs.getBoolean("confirm")) {
                    System.out.println("not confirm.");
                    return -2;
                } else {
                    System.out.println("found.");
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("not found.");
        return -3;
    }

    public static boolean userConfirm(int userID){    //change
        try {
            stmt = conn.prepareStatement("UPDATE users SET confirm = 1 WHERE userID = ?");
            stmt.setInt(1, userID);
            int row = stmt.executeUpdate();
            System.out.println("row updated: " + row);
            return true;
        } catch (Exception e) {
            System.out.println("invalid data.");
            return false;
        }
    }

    public static ArrayList<userBean> searchByYourName(String yourName) {
        ArrayList<userBean> userList = new ArrayList<userBean>();

        try {
            stmt = conn.prepareStatement("SELECT * FROM users WHERE confirm = TRUE AND LOWER(yourName) LIKE ?");   // partial matching, capital insensitive
            stmt.setString(1, "%" + yourName.toLowerCase() + "%");                  // partial matching, capital insensitive
            rs = stmt.executeQuery();

            while (rs.next()) {
                userBean elem = new userBean();
                elem.userID = rs.getInt("userID");
                elem.userName = rs.getString("userName");
                elem.email = rs.getString("email");
                elem.yourName = rs.getString("yourName");
                elem.gender = rs.getString("gender");
                elem.DoB = rs.getString("DoB");
                elem.photo = rs.getString("photo");
                elem.ban = rs.getInt("ban");
                elem.confirm = rs.getInt("confirm");
                userList.add(elem);
            }

            for (userBean userData : userList) {
                System.out.print(userData.userID + ", ");
                System.out.print(userData.userName + ", ");
                System.out.print(userData.email + ", ");
                System.out.print(userData.yourName + ", ");
                System.out.print(userData.gender + ", ");
                System.out.print(userData.DoB + ", ");
                System.out.print(userData.photo + ", ");
                System.out.print(userData.ban + ", ");
                System.out.println(userData.confirm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    public static boolean createPost(int userID,String content, String photo){
        try {
            Timestamp dtime = new Timestamp(System.currentTimeMillis());
            stmt = conn.prepareStatement("INSERT INTO post (content, userID, time, photo)" +
                    "VALUES (?, ?, ?, ?)");
            stmt.setString(1, content);
            stmt.setInt(2, userID);
            stmt.setTimestamp(3, dtime);
            stmt.setString(4, photo);
            int row = stmt.executeUpdate();
            System.out.println("row added: " + row);
            return true;
        } catch (Exception e) {
            System.out.println("invalid data.");
            return false;
        }
    }

    public static boolean deletePost(int postID){
        try {
            stmt = conn.prepareStatement("DELETE FROM post WHERE postID = ?");
            stmt.setInt(1, postID);
            int row = stmt.executeUpdate();
            System.out.println("row deleted: " + row);

            if (row > 0){
                return true;
            } else {
                System.out.println("postID not in database.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("invalid operation.");
            return false;
        }
    }

    public static ArrayList<userBean> advancedSearch(String userName, String yourName, String email, String gender, String DoB) {
        ArrayList<userBean> userList = new ArrayList<userBean>();

        try {
            stmt = conn.prepareStatement("SELECT * FROM users WHERE confirm = TRUE AND LOWER(userName) LIKE ? AND LOWER(yourName) LIKE ? " +
                    "AND email LIKE ? AND gender LIKE ? AND DoB LIKE ?");   // partial matching, capital insensitive
//changed
            if (userName != "") {
                stmt.setString(1, "%" + userName.toLowerCase() + "%");
            } else {
                System.out.println("no userName");
                stmt.setString(1, "%");
            }
            if (yourName != "") {
                stmt.setString(2, "%" + yourName.toLowerCase() + "%");
            } else {
                System.out.println("no yourName");
                stmt.setString(2, "%");
            }
            if (email != "") {
                stmt.setString(3, email);
            } else {
                System.out.println("no email");
                stmt.setString(3, "%");
            }
            if (gender != "") {
                stmt.setString(4, gender);
            } else {
                System.out.println("no gender");
                stmt.setString(4, "%");
            }
            if (DoB != "") {
                stmt.setString(5, DoB);
            } else {
                System.out.println("no DoB");
                stmt.setString(5, "%");
            }
            rs = stmt.executeQuery();
//changed
            while (rs.next()) {
                userBean elem = new userBean();
                elem.userID = rs.getInt("userID");
                elem.userName = rs.getString("userName");
                elem.email = rs.getString("email");
                elem.yourName = rs.getString("yourName");
                elem.gender = rs.getString("gender");
                elem.DoB = rs.getString("DoB");
                elem.photo = rs.getString("photo");
                elem.ban = rs.getInt("ban");
                elem.confirm = rs.getInt("confirm");
                userList.add(elem);
            }
//            System.out.println(userList.size());

            for (userBean userData : userList) {
                System.out.print(userData.userID + ", ");
                System.out.print(userData.userName + ", ");
                System.out.print(userData.email + ", ");
                System.out.print(userData.yourName + ", ");
                System.out.print(userData.gender + ", ");
                System.out.print(userData.DoB + ", ");
                System.out.print(userData.photo + ", ");
                System.out.print(userData.ban + ", ");
                System.out.println(userData.confirm);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  userList;
    }

    public static int getUserID(String userName) {                     // completely matching
        try {
            stmt = conn.prepareStatement("SELECT * FROM users where userName = ?");
            stmt.setString(1, userName);
            rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.print(rs.getInt(1) + ", ");
                System.out.print(rs.getString(2) + ", ");
                System.out.print(rs.getString(3) + ", ");
                System.out.print(rs.getString(4) + ", ");
                System.out.print(rs.getString(5) + ", ");
                System.out.print(rs.getString(6) + ", ");
                System.out.print(rs.getString(7) + ", ");
                System.out.print(rs.getString(8) + ", ");
                System.out.print(rs.getInt(9) + ", ");
                System.out.println(rs.getInt(10));
                return rs.getInt("userID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static boolean checkFriend(int user1ID, int user2ID) {
        try {
            stmt = conn.prepareStatement("SELECT * FROM friendship where (user1ID = ? and user2ID = ?) or (user2ID = ? and user1ID = ?)");
            stmt.setInt(1, user1ID);
            stmt.setInt(2, user2ID);
            stmt.setInt(3, user1ID);
            stmt.setInt(4, user2ID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.print(rs.getInt(1) + ", ");
                System.out.print(rs.getInt(2) + ", ");
                System.out.print(rs.getInt(3) + ", ");
                System.out.println(rs.getBoolean(4));
                if (rs.getBoolean("confirm")) {
                    System.out.println("friends.");
                    return true;
                } else {
                    System.out.println("friend request.");
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("not friend.");
        return false;
    }

    public static boolean addFriend(int user1ID, int user2ID){
        try {
            stmt = conn.prepareStatement("SELECT * FROM friendship where (user1ID = ? and user2ID = ?) or (user2ID = ? and user1ID = ?)");
            stmt.setInt(1, user1ID);
            stmt.setInt(2, user2ID);
            stmt.setInt(3, user1ID);
            stmt.setInt(4, user2ID);
            rs = stmt.executeQuery();
            if (!rs.next()){
                stmt = conn.prepareStatement("INSERT INTO friendship (user1ID, user2ID)" +
                        "VALUES (?, ?)");
                stmt.setInt(1, user1ID);
                stmt.setInt(2, user2ID);
                int row = stmt.executeUpdate();
                System.out.println("row added: " + row);
                return true;
            } else {
                System.out.println("already be friends or wait for confirmation.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("invalid data.");
            return false;
        }
    }

    public static ArrayList<userBean> findAllFriends(int userID) {
        ArrayList<userBean> userList = new ArrayList<userBean>();

        try {
            stmt = conn.prepareStatement("SELECT users.* FROM friendship, users WHERE friendship.confirm = TRUE AND user2Id = userID AND user1ID = ?" +
                    " UNION SELECT users.* FROM friendship, users WHERE friendship.confirm = TRUE AND user1Id = userID AND user2ID = ?");
            stmt.setInt(1, userID);
            stmt.setInt(2, userID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                userBean elem = new userBean();
                elem.userID = rs.getInt("userID");
                elem.userName = rs.getString("userName");
                elem.email = rs.getString("email");
                elem.yourName = rs.getString("yourName");
                elem.gender = rs.getString("gender");
                elem.DoB = rs.getString("DoB");
                elem.photo = rs.getString("photo");
                elem.ban = rs.getInt("ban");
                elem.confirm = rs.getInt("confirm");
                userList.add(elem);
            }

            for (userBean userData : userList) {
                System.out.print(userData.userID + ", ");
                System.out.print(userData.userName + ", ");
                System.out.print(userData.email + ", ");
                System.out.print(userData.yourName + ", ");
                System.out.print(userData.gender + ", ");
                System.out.print(userData.DoB + ", ");
                System.out.print(userData.photo + ", ");
                System.out.print(userData.ban + ", ");
                System.out.println(userData.confirm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userList;
    }

    public static userBean getUserInfo(int userID) {
        userBean elem = new userBean();
        try {
            stmt = conn.prepareStatement("SELECT * FROM users WHERE userID = ?");
            stmt.setInt(1, userID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                elem.userID = rs.getInt("userID");
                elem.userName = rs.getString("userName");
                elem.email = rs.getString("email");
                elem.yourName = rs.getString("yourName");
                elem.gender = rs.getString("gender");
                elem.DoB = rs.getString("DoB");
                elem.photo = rs.getString("photo");
                elem.ban = rs.getInt("ban");
                elem.confirm = rs.getInt("confirm");

                System.out.print(elem.userID + ", ");
                System.out.print(elem.userName + ", ");
                System.out.print(elem.email + ", ");
                System.out.print(elem.yourName + ", ");
                System.out.print(elem.gender + ", ");
                System.out.print(elem.DoB + ", ");
                System.out.print(elem.photo + ", ");
                System.out.print(elem.ban + ", ");
                System.out.println(elem.confirm);
            } else {
                System.out.println("userID not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elem;
    }

    public static ArrayList<postBean> getPost(int userID) {
        ArrayList<postBean> postList = new ArrayList<postBean>();
        ArrayList<postBean> postBasicList = new ArrayList<postBean>();

        try {
            stmt = conn.prepareStatement("SELECT post.*, users.userName FROM post, users WHERE post.userID = users.userID AND post.userID = ?");
            stmt.setInt(1, userID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                postBean elem = new postBean();
                elem.postID = rs.getInt("postID");
                elem.ownerName = rs.getString("userName");
                elem.content = rs.getString("content");
                elem.photo = rs.getString("photo");
                elem.time = rs.getTimestamp("time");
                postBasicList.add(elem);
            }

            for (postBean postSingle : postBasicList) {
                ArrayList<Integer> likesList = new ArrayList<Integer>();
                ArrayList<String> likesYourNameList = new ArrayList<String>();
                stmt = conn.prepareStatement("SELECT upVote.*, users.userName FROM upVote,users WHERE upVote.senderID = users.userID AND postID = ?");
                stmt.setInt(1, postSingle.postID);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    likesList.add(rs.getInt("senderID"));
                    likesYourNameList.add(rs.getString("userName"));
                }
                postSingle.likesList = likesList;
                postSingle.likesYourNameList = likesYourNameList;
                postList.add(postSingle);
            }

            postList.sort(Comparator.comparing(postBean::getPostID).reversed());


            for (postBean postPrint : postList) {
                System.out.print(postPrint.postID + ", ");
                System.out.print(postPrint.ownerName + ", ");
                System.out.print(postPrint.content + ", ");
                System.out.print(postPrint.photo + ", ");
                System.out.print(postPrint.likesList + ", ");
                System.out.print(postPrint.likesYourNameList + ", ");
                System.out.println(postPrint.time);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return postList;
    }

    public static boolean checkLike(int userID,int postID) {
        try {
            stmt = conn.prepareStatement("SELECT * FROM upVote where senderID = ? and postID = ?");
            stmt.setInt(1, userID);
            stmt.setInt(2, postID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.print(rs.getInt(1) + ", ");
                System.out.print(rs.getInt(2) + ", ");
                System.out.print(rs.getInt(3) + ", ");
                System.out.println(rs.getTimestamp(4));
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("no record.");
        return false;
    }

    public static boolean addLike(int userID,int postID){
        try {
            Timestamp dtime = new Timestamp(System.currentTimeMillis());
            stmt = conn.prepareStatement("INSERT INTO upVote (postID, senderID)" +
                    "VALUES (?, ?)");
            stmt.setInt(1, postID);
            stmt.setInt(2, userID);
            int row = stmt.executeUpdate();
            System.out.println("row added: " + row);
            return true;
        } catch (Exception e) {
            System.out.println("invalid data.");
            return false;
        }
    }

    public static boolean update(userBean updateInfo){
        try {
            stmt = conn.prepareStatement("UPDATE users SET yourName = ?, gender = ?, DoB = ?, photo = ? WHERE userID = ?");
            stmt.setString(1, updateInfo.yourName);
            stmt.setString(2, updateInfo.gender);
            stmt.setString(3, updateInfo.DoB);
            stmt.setString(4, updateInfo.photo);
            stmt.setInt(5, updateInfo.userID);
            int row = stmt.executeUpdate();
            System.out.println("row added: " + row);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("invalid data.");
            return false;
        }
    }

    public static ArrayList<notificationBean> getNotification(int userID) {
        ArrayList<notificationBean> infoList = new ArrayList<notificationBean>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM notification WHERE receiverID = ?");
            stmt.setInt(1, userID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                notificationBean elem = new notificationBean();
                elem.content = rs.getString("content");
                elem.time = rs.getTimestamp("time");
                elem.notificationID = rs.getInt("notificationID");
                elem.senderID = rs.getInt("senderID");
                elem.isRead = rs.getBoolean("isRead");
                infoList.add(elem);
            }

            for (notificationBean info : infoList) {
                System.out.print(info.content + ", ");
                System.out.print(info.time + ", ");
                System.out.print(info.notificationID + ", ");
                System.out.print(info.senderID + ", ");
                System.out.println(info.isRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return infoList;
    }

    public static boolean friendConfirm(int user1ID, int user2ID){  //add notification
        try {
            if (!database.checkFriend(user1ID, user2ID)){
                stmt = conn.prepareStatement("UPDATE friendship SET confirm = 1" +
                        " WHERE (user1ID = ? and user2ID = ?) or (user2ID = ? and user1ID = ?)");
                stmt.setInt(1, user1ID);
                stmt.setInt(2, user2ID);
                stmt.setInt(3, user1ID);
                stmt.setInt(4, user2ID);
                int row = stmt.executeUpdate();
                System.out.println("row updated: " + row);
                return true;
            } else {
                System.out.println("no friend request or already be friends.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("invalid data.");
            return false;
        }
    }

    public static boolean isValidAdmin(String userName,String password) {
        try {
            stmt = conn.prepareStatement("SELECT * FROM admin where adminName = ? and password = ?");
            stmt.setString(1, userName);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.print(rs.getInt(1) + ", ");
                System.out.print(rs.getString(2) + ", ");
                System.out.println(rs.getString(3));
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("no record.");
        return false;
    }

    public static ArrayList<logBean> searchLog(String userName, String yourName, String email, String gender, String DoB) {
        ArrayList<logBean> logList = new ArrayList<logBean>();
        ArrayList<userBean> usersList = database.advancedSearch(userName, yourName, email, gender, DoB);
        try {
            if (usersList.size() > 0) {
                for (userBean r : usersList) {
                    stmt = conn.prepareStatement("SELECT * FROM log WHERE userID = ?");
                    stmt.setInt(1, r.userID);
                    rs = stmt.executeQuery();
                    while (rs.next()) {
                        logBean elem = new logBean();
                        elem.logID = rs.getInt("logID");
                        elem.userID = rs.getInt("userID");
                        elem.activity = rs.getString("activity");
                        elem.time = rs.getTimestamp("time");
                        logList.add(elem);
                    }
                }
                System.out.println("from search logs.");
                for (logBean logSingle : logList) {
                    System.out.print(logSingle.logID + ", ");
                    System.out.print(logSingle.userID + ", ");
                    System.out.print(logSingle.activity + ", ");
                    System.out.println(logSingle.time);
                }
            } else {
                System.out.println("no log found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return logList;
    }

    public static ArrayList<logBean> searchAllLog() {
//        changed
        ArrayList<logBean> allLog = searchLog("", "", "", "", "");
        try {
            if (allLog.size() > 0) {
                System.out.println("from all logs.");
                for (logBean logSingle : allLog) {
                    System.out.print(logSingle.logID + ", ");
                    System.out.print(logSingle.userID + ", ");
                    System.out.print(logSingle.activity + ", ");
                    System.out.println(logSingle.time);
                }
            } else {
                System.out.println("no log found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allLog;
    }

    public static boolean checkBan(int userID) {
        try {
            stmt = conn.prepareStatement("SELECT * FROM users where userID = ?");
            stmt.setInt(1, userID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.print(rs.getInt(1) + ", ");
                System.out.print(rs.getString(2) + ", ");
                System.out.print(rs.getString(3) + ", ");
                System.out.print(rs.getString(4) + ", ");
                System.out.print(rs.getString(5) + ", ");
                System.out.print(rs.getString(6) + ", ");
                System.out.print(rs.getString(7) + ", ");
                System.out.print(rs.getString(8) + ", ");
                System.out.print(rs.getBoolean(9) + ", ");
                System.out.println(rs.getBoolean(10));

                if (rs.getBoolean("ban")) {
                    System.out.println("banned.");
                    return true;
                } else {
                    System.out.println("not banned.");
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("no record.");
        return false;
    }

    public static boolean banUser(int userID){
        try {
            stmt = conn.prepareStatement("UPDATE users SET ban = 1 WHERE userID = ?");
            stmt.setInt(1, userID);
            int row = stmt.executeUpdate();
            if (row > 0){
                System.out.println("banned: " + row);
                return true;
            } else {
                System.out.println("no record.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("invalid data.");
            e.printStackTrace();
            return false;
        }
    }

    public static boolean unbanUser(int userID){
        try {
            stmt = conn.prepareStatement("UPDATE users SET ban = 0 WHERE userID = ?");
            stmt.setInt(1, userID);
            int row = stmt.executeUpdate();
            if (row > 0){
                System.out.println("unbanned: " + row);
                return true;
            } else {
                System.out.println("no record.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("invalid data.");
            e.printStackTrace();
            return false;
        }    }

    public static ArrayList<postBean> getMoment(int userID) {
        ArrayList<postBean> postList = new ArrayList<postBean>();
        ArrayList<postBean> postBasicList = new ArrayList<postBean>();

        ArrayList<userBean> friendsList = findAllFriends(userID);

        try {
            for (userBean friendSingle : friendsList) {
                stmt = conn.prepareStatement("SELECT post.*, users.userName FROM post, users WHERE post.userID = users.userID AND post.userID = ?");
                stmt.setInt(1, friendSingle.userID);
                rs = stmt.executeQuery();

                while (rs.next()) {
                    postBean elem = new postBean();
                    elem.postID = rs.getInt("postID");
                    elem.ownerName = rs.getString("userName");
                    elem.content = rs.getString("content");
                    elem.photo = rs.getString("photo");
                    elem.time = rs.getTimestamp("time");
                    postBasicList.add(elem);
                }
            }

            for (postBean postSingle : postBasicList) {
                ArrayList<Integer> likesList = new ArrayList<Integer>();
                ArrayList<String> likesYourNameList = new ArrayList<String>();
                stmt = conn.prepareStatement("SELECT upVote.*, users.userName FROM upVote,users WHERE upVote.senderID = users.userID AND postID = ?");
                stmt.setInt(1, postSingle.postID);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    likesList.add(rs.getInt("senderID"));
                    likesYourNameList.add(rs.getString("userName"));
                }
                postSingle.likesList = likesList;
                postSingle.likesYourNameList = likesYourNameList;
                postList.add(postSingle);
            }

            postList.addAll(getPost(userID));

            postList.sort(Comparator.comparing(postBean::getPostID).reversed());

            System.out.println("print in monment.");
            for (postBean postPrint : postList) {
                System.out.print(postPrint.postID + ", ");
                System.out.print(postPrint.ownerName + ", ");
                System.out.print(postPrint.content + ", ");
                System.out.print(postPrint.photo + ", ");
                System.out.print(postPrint.likesList + ", ");
                System.out.print(postPrint.likesYourNameList + ", ");
                System.out.println(postPrint.time);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return postList;
    }

    public static boolean addLog(int userID, String activity) {
        try {
            Timestamp dtime = new Timestamp(System.currentTimeMillis());
            stmt = conn.prepareStatement("INSERT INTO log (userID, activity, time)" +
                    "VALUES (?, ?, ?)");
            stmt.setInt(1, userID);
            stmt.setString(2, activity);
            stmt.setTimestamp(3, dtime);
            int row = stmt.executeUpdate();
            System.out.println("row added: " + row);
            return true;
        } catch (Exception e) {
            System.out.println("invalid data.");
            return false;
        }
    }

    public static boolean anyNotificationNotRead (int userID) {
        ArrayList<notificationBean> userNotification = getNotification(userID);
        for (notificationBean notificationSingle : userNotification) {
            if (notificationSingle.isRead == false) {
                return true;
            }
        }
        return false;
    }

    public static boolean setNotificationIsRead(int userID) {
        try {
            stmt = conn.prepareStatement("UPDATE notification SET isRead = 1 WHERE receiverID = ?");
            stmt.setInt(1, userID);
            int row = stmt.executeUpdate();
            System.out.println("notification confirmed: " + row);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("invalid data.");
            return false;
        }
    }

    public static boolean addNotification(String content, int senderID, int receiverID) {
        try {
            Timestamp dtime = new Timestamp(System.currentTimeMillis());
            stmt = conn.prepareStatement("INSERT INTO notification (content, senderID, receiverID, time)" +
                    "VALUES (?, ?, ?, ?)");
            stmt.setString(1, content);
            stmt.setInt(2, senderID);
            stmt.setInt(3, receiverID);
            stmt.setTimestamp(4, dtime);
            int row = stmt.executeUpdate();
            System.out.println("row added: " + row);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("invalid data.");
            return false;
        }
    }

    public static boolean unLike(int userID,int postID){
        try {
            Timestamp dtime = new Timestamp(System.currentTimeMillis());
            stmt = conn.prepareStatement("DELETE FROM upVote WHERE postID = ? and senderID = ?");
            stmt.setInt(1, postID);
            stmt.setInt(2, userID);
            int row = stmt.executeUpdate();
            System.out.println("row deleted: " + row);
            return true;
        } catch (Exception e) {
            System.out.println("invalid data.");
            return false;
        }
    }

    public static int getPostUserID(String postID) {                     // completely matching
        try {
            stmt = conn.prepareStatement("SELECT * FROM post where postID = ?");
            stmt.setString(1, postID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.print(rs.getInt(1) + ", ");
                System.out.print(rs.getInt(2) + ", ");
                System.out.print(rs.getString(3) + ", ");
                System.out.print(rs.getTimestamp(4) + ", ");
                System.out.println(rs.getString(5));
                return rs.getInt("userID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    
    public static void graphPeopleSearch(ArrayList<ArrayList<String>> peopleNmessage, ArrayList<ArrayList<String>> edgeReturn, String userName, String yourName, String email, String gender, String DoB) {

        ArrayList<userBean> peopleSearch = database.advancedSearch(userName, yourName, email, gender, DoB);

        ArrayList<String> peopleNmessageLite = new ArrayList<String>();

        ArrayList<String> people = new ArrayList<String>();
        ArrayList<Integer> peopleID = new ArrayList<Integer>();
        ArrayList<String> message = new ArrayList<String>();
        ArrayList<String> messageID = new ArrayList<String>();
        ArrayList<ArrayList<String>> edge = new ArrayList<ArrayList<String>>();

        ArrayList<String> n = new ArrayList<>();
        n.add("unswbook");
        n.add("2");
        peopleNmessage.add(n);

        for (userBean p : peopleSearch) {
            people.add(p.userName);
            peopleID.add(p.userID);

            ArrayList<userBean> friendSearch = findAllFriends(p.userID);
            for (userBean f : friendSearch) {
                people.add(f.userName);
                peopleID.add(f.userID);
            }
        }

        for (int i = 0; i < peopleID.size(); i++) {
            for (int j = i + 1; j < peopleID.size(); j++) {
                if (checkFriend(peopleID.get(i), peopleID.get(j))) {
                    ArrayList<String> e = new ArrayList<>();
                    e.add(people.get(i));
                    e.add(people.get(j));
                    e.add("friendOf");
                    edge.add(e);
                }
            }
        }

        for (Integer pID : peopleID) {
            int flag;
            ArrayList<postBean> postSearch = getPost(pID);
            for (postBean po : postSearch) {
                message.add(po.content);
                messageID.add(String.valueOf(po.postID));

                ArrayList<String> e = new ArrayList<String>();
                e.add(getUserInfo(pID).userName);
                e.add(String.valueOf(po.postID));
                e.add("posted");
                edge.add(e);

                for (Integer l : po.likesList) {
                    if (peopleID.contains(l)) {
                        flag = 0;
                        ArrayList<String> ee = new ArrayList<String>();
                        ee.add(getUserInfo(l).userName);
                        ee.add(String.valueOf(po.postID));

                        for (int m = 0; m < edge.size(); m++) {
                            if (edge.get(m).get(0).equals(getUserInfo(l).userName) && edge.get(m).get(1).equals(String.valueOf(po.postID))) {
                                    ee.add("posted, liked");
                                edge.set(m, ee);
                                flag = 1;
                            }
                        }

                        if (flag == 0) {
                            ee.add("liked");
                            edge.add(ee);
                        }
                    }
                }
            }
        }

        for (int a = 0; a < people.size(); a++) {
            ArrayList<String> p = new ArrayList<>();
            p.add("0");
            p.add(String.valueOf(a + 1));
            p.add("");
            edgeReturn.add(p);
        }

        for (int b = 0; b < people.size(); b++) {
            ArrayList<String> p = new ArrayList<>();
            p.add(people.get(b));
            p.add("0");
            peopleNmessage.add(p);
        }

        for (int b = 0; b < message.size(); b++) {
            ArrayList<String> p = new ArrayList<>();
            p.add(message.get(b));
            p.add("1");
            peopleNmessage.add(p);
        }

        peopleNmessageLite.add("unsw");
        peopleNmessageLite.addAll(people);
        peopleNmessageLite.addAll(messageID);

        for (ArrayList<String> e : edge) {
            ArrayList<String> r = new ArrayList<>();
            r.add(String.valueOf(peopleNmessageLite.indexOf(e.get(0))));
            r.add(String.valueOf(peopleNmessageLite.indexOf(e.get(1))));
            r.add(e.get(2));
            edgeReturn.add(r);
        }

        System.out.print("\n\n\n===================");
        System.out.print("\nTest Start:\n\n");

        System.out.print("----------peopleNmessage-----------\n\n");
        for (int k = 0; k < peopleNmessage.size(); k++) {
            System.out.println(peopleNmessage.get(k));
        }
        System.out.print("\n\n");

        System.out.print("----------relation-----------\n\n");
        for (ArrayList<String> ss : edgeReturn) {
            for (String s : ss) {
                System.out.print(s + " -- ");
            }
            System.out.println();
        }
    }


    public static void graphMessageSearch(ArrayList<ArrayList<String>> peopleNmessage, ArrayList<ArrayList<String>> edgeReturn, String str) {
        try {
            int userID;

            ArrayList<String> peopleNmessageLite = new ArrayList<String>();

            ArrayList<String> people = new ArrayList<String>();
            ArrayList<Integer> peopleID = new ArrayList<Integer>();
            ArrayList<Integer> postID = new ArrayList<Integer>();
            ArrayList<String> message = new ArrayList<String>();
            ArrayList<String> messageID = new ArrayList<String>();
            ArrayList<ArrayList<String>> edge = new ArrayList<ArrayList<String>>();

            ArrayList<String> n = new ArrayList<>();
            n.add("unswbook");
            n.add("2");
            peopleNmessage.add(n);

            stmt = conn.prepareStatement("SELECT * FROM post, users where post.userID = users.userID AND content LIKE ?");
            stmt.setString(1, "%" + str + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                postID.add(rs.getInt("postID"));
                message.add(rs.getString("content"));
                messageID.add(String.valueOf(rs.getInt("postID")));

                if (!peopleID.contains(rs.getInt("userID"))) {
                    peopleID.add(rs.getInt("userID"));
                    people.add(rs.getString("userName"));
                }

                ArrayList<String> e = new ArrayList<String>();
                e.add(rs.getString("userName"));
                e.add(String.valueOf(rs.getInt("postID")));
                e.add("posted");
                edge.add(e);
            }

            for (Integer p : postID) {
                stmt = conn.prepareStatement("SELECT * FROM upVote, users where upVote.senderID = users.userID AND postID = ?");
                stmt.setInt(1, p);
                rs = stmt.executeQuery();
                int flag;

                while (rs.next()) {
                    if (!peopleID.contains(rs.getInt("userID"))) {
                        peopleID.add(rs.getInt("userID"));
                        people.add(rs.getString("userName"));
                    }

                    ArrayList<String> e = new ArrayList<String>();
                    e.add(rs.getString("userName"));
                    e.add(String.valueOf(rs.getInt("postID")));

                    flag = 0;
                    for (int m = 0; m < edge.size(); m++) {
                        if (edge.get(m).get(0).equals(rs.getString("userName")) && edge.get(m).get(1).equals(String.valueOf(rs.getInt("postID")))) {
                            e.add("posted, liked");
                            edge.set(m, e);
                            flag = 1;
                        }
                    }

                    System.out.println("=======================");
                    System.out.print(rs.getString("userName") + ", ");
                    System.out.print(String.valueOf(rs.getInt("postID")) + ", ");
                    System.out.println(flag);
                    System.out.println("=======================");

                    if (flag == 0) {
                        e.add("liked");
                        edge.add(e);
                    }


                }
            }

            for (int i = 0; i < peopleID.size(); i++) {
                for (int j = i + 1; j < peopleID.size(); j++) {
                    if (checkFriend(peopleID.get(i), peopleID.get(j))) {
                        ArrayList<String> e = new ArrayList<>();
                        e.add(people.get(i));
                        e.add(people.get(j));
                        e.add("friendOf");
                        edge.add(e);
                    }
                }
            }

            for (int a = 0; a < people.size(); a++) {
                ArrayList<String> p = new ArrayList<>();
                p.add("0");
                p.add(String.valueOf(a + 1));
                p.add("");
                edgeReturn.add(p);
            }

            for (int b = 0; b < people.size(); b++) {
                ArrayList<String> p = new ArrayList<>();
                p.add(people.get(b));
                p.add("0");
                peopleNmessage.add(p);
            }

            for (int b = 0; b < message.size(); b++) {
                ArrayList<String> p = new ArrayList<>();
                p.add(message.get(b));
                p.add("1");
                peopleNmessage.add(p);
            }

            peopleNmessageLite.add("unsw");
            peopleNmessageLite.addAll(people);
            peopleNmessageLite.addAll(messageID);

            for (ArrayList<String> e : edge) {
                ArrayList<String> r = new ArrayList<>();
                r.add(String.valueOf(peopleNmessageLite.indexOf(e.get(0))));
                r.add(String.valueOf(peopleNmessageLite.indexOf(e.get(1))));
                r.add(e.get(2));
                edgeReturn.add(r);
            }

            System.out.print("\n\n\n===================");
            System.out.print("\nTest Start:\n\n");

            System.out.print("----------peopleNmessage-----------\n\n");
            for (int k = 0; k < peopleNmessage.size(); k++) {
                System.out.println(peopleNmessage.get(k));
            }
            System.out.print("\n\n");

            System.out.print("----------relation-----------\n\n");
            for (ArrayList<String> ss : edgeReturn) {
                for (String s : ss) {
                    System.out.print(s + " -- ");
                }
                System.out.println();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public static void graphFriendSearch(ArrayList<ArrayList<String>> peopleNmessage, ArrayList<ArrayList<String>> edgeReturn, Integer userID) {
        ArrayList<String> peopleNmessageLite = new ArrayList<String>();

        ArrayList<String> people = new ArrayList<String>();
        ArrayList<String> peopleIntimate = new ArrayList<String>();
        ArrayList<Integer> peopleID = new ArrayList<Integer>();
        ArrayList<Integer> postID = new ArrayList<Integer>();
        ArrayList<String> message = new ArrayList<String>();
        ArrayList<String> messageID = new ArrayList<String>();
        ArrayList<ArrayList<String>> edge = new ArrayList<ArrayList<String>>();

//        ArrayList<String> n = new ArrayList<>();
//        n.add(getUserInfo(userID).userName);
//        n.add("2");
//        peopleNmessage.add(n);

        people.add(getUserInfo(userID).userName);
        peopleID.add(userID);

        ArrayList<userBean> friends = findAllFriends(userID);
        for (userBean f : friends) {
            peopleIntimate.add(f.userName);
            if (!peopleID.contains(f.userID)) {
                peopleID.add(f.userID);
                people.add(f.userName);
            }

            ArrayList<userBean> sub_friends = findAllFriends(f.userID);
            for (userBean sf : sub_friends) {
                if (!peopleID.contains(sf.userID)) {
                    peopleID.add(sf.userID);
                    people.add(sf.userName);
                }
            }
        }

        for (int i = 0; i < peopleID.size(); i++) {
            for (int j = i + 1; j < peopleID.size(); j++) {
                if (checkFriend(peopleID.get(i), peopleID.get(j))) {
                    ArrayList<String> e = new ArrayList<>();
                    e.add(people.get(i));
                    e.add(people.get(j));
                    e.add("friendOf");
                    edge.add(e);
                }
            }
        }

        for (Integer pID : peopleID) {
            int flag;
            ArrayList<postBean> postSearch = getPost(pID);
            for (postBean po : postSearch) {
                message.add(po.content);
                messageID.add(String.valueOf(po.postID));

                ArrayList<String> e = new ArrayList<String>();
                e.add(getUserInfo(pID).userName);
                e.add(String.valueOf(po.postID));
                e.add("posted");
                edge.add(e);

                for (Integer l : po.likesList) {
                    if (peopleID.contains(l)) {
                        flag = 0;
                        ArrayList<String> ee = new ArrayList<String>();
                        ee.add(getUserInfo(l).userName);
                        ee.add(String.valueOf(po.postID));

                        for (int m = 0; m < edge.size(); m++) {
                            if (edge.get(m).get(0).equals(getUserInfo(l).userName) && edge.get(m).get(1).equals(String.valueOf(po.postID))) {
                                ee.add("posted, liked");
                                edge.set(m, ee);
                                flag = 1;
                            }
                        }

                        if (flag == 0) {
                            ee.add("liked");
                            edge.add(ee);
                        }
                    }
                }
            }
        }

        for (int b = 0; b < people.size(); b++) {
            ArrayList<String> p = new ArrayList<>();
            p.add(people.get(b));
            if (b == 0) {
                p.add("3");
            } else if (peopleIntimate.contains(people.get(b))) {
                p.add("4");
            } else {
                p.add("0");
            }
            peopleNmessage.add(p);
        }

        for (int b = 0; b < message.size(); b++) {
            ArrayList<String> p = new ArrayList<>();
            p.add(message.get(b));
            p.add("1");
            peopleNmessage.add(p);
        }

        peopleNmessageLite.addAll(people);
        peopleNmessageLite.addAll(messageID);

        for (ArrayList<String> e : edge) {
            ArrayList<String> r = new ArrayList<>();
            r.add(String.valueOf(peopleNmessageLite.indexOf(e.get(0))));
            r.add(String.valueOf(peopleNmessageLite.indexOf(e.get(1))));
            r.add(e.get(2));
            edgeReturn.add(r);
        }

        System.out.print("\n\n\n===================");
        System.out.print("\nTest Start:\n\n");

        System.out.print("----------peopleNmessage-----------\n\n");
        for (int k = 0; k < peopleNmessage.size(); k++) {
            System.out.println(k + "--- " + peopleNmessage.get(k));
        }
        System.out.print("\n\n");

        System.out.print("----------relation ++ -----------\n\n");
        for (ArrayList<String> ss : edge) {
            for (String s : ss) {
                System.out.print(s + " -- ");
            }
            System.out.println();
        }
        System.out.print("\n\n");

        System.out.print("----------relation-----------\n\n");
        for (ArrayList<String> ss : edgeReturn) {
            for (String s : ss) {
                System.out.print(s + " -- ");
            }
            System.out.println();
        }
    }

    public static void addUsers(Integer N) {
        try {
            for (int i = 0; i < N; i++) {
                stmt = conn.prepareStatement("INSERT INTO users (userName, password, email, yourName, gender, DoB, photo, confirm)" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                stmt.setString(1, "test_sample_" + String.valueOf(i));
                stmt.setInt(2, 123);
                stmt.setString(3, String.valueOf(i) + "@test.com");
                stmt.setString(4, "test_sample_" + String.valueOf(i));
                stmt.setString(5, "male");
                stmt.setString(6, "19000101");
                stmt.setString(7, "/photo/default_photo.png");
                stmt.setInt(8, 1);
                int row = stmt.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("invalid data.");
        }
    }
}
