package com.newssite.db;

import com.newssite.model.Comment;
import com.newssite.model.Details;
import com.newssite.model.Post;
import com.newssite.utility.Util;

import java.util.*;
import java.util.List;
import java.sql.*;

public class DataAccess {
    public static void main(String[] args) {

    }

    public Details getPost(int id) throws SQLException {
        Details PostDetails = new Details();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        Connection con = null;
        Statement stmt = null;
        ResultSet postOne = null, rs = null;
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/NewsDB?useSSL=false", "root", "");

            stmt = con.createStatement();
            postOne = stmt.executeQuery("SELECT * FROM Posts WHERE ID = " + id);
            if (postOne.next()) {
                PostDetails.setId(postOne.getInt("ID"));
                PostDetails.setTittle(postOne.getString("Title"));
                PostDetails.setLink(postOne.getString("Link"));
                PostDetails.setUpvote(postOne.getInt("UpvoteCount"));
                PostDetails.setName(postOne.getString("Name"));
                System.out.println("ID = " + postOne.getInt("ID"));
                PostDetails.setTime(Util.getDifference(postOne.getTimestamp("Time")));
                PostDetails.setCommentsCount(postOne.getInt("CommentsCount"));
//                Date commetedTime = postOne.getDate("CommentedAt");
//                java.util.Date date = Calendar.getInstance().getTime();
//                "1 month ago";
//                System.out.println(PostDetails.commentsCount = postOne.getInt("commentscount"));
            }
            rs = stmt.executeQuery("SELECT * FROM Comments WHERE PostID = " + id);
            List<Comment> cmt = new ArrayList<Comment>();
            while (rs.next()) {
                Comment cmdDetaile = new Comment();
                cmdDetaile.setCmd(rs.getString("Comment"));
                System.out.println(".............Comment Content :: " + rs.getString("Comment") + "  And its time below ........");
                int uId = rs.getInt("UserID");
                cmdDetaile.setName(UserIDNames(uId));
                cmdDetaile.setTime(Util.getDifference(rs.getTimestamp("Time")));
                cmt.add(cmdDetaile);
            }
            PostDetails.setComments(cmt);
        } catch (SQLException e) {
            System.out.println("SQL exception occured" + e);
        }
        finally {
            if (rs != null)
            {
                rs.close();
            }
            if (postOne != null){
                postOne.close();
            }
            if (stmt != null){
                stmt.close();
            }
            if (con != null){
                con.close();
            }
        }
        return PostDetails;
    }

    public static List<Post> getPosts() throws SQLException {
        List<Post> posts = new ArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try (Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/NewsDB?useSSL=false", "root", "");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Posts "))
        {
            while (rs.next()) {
                Post postObj = new Post();
                postObj.setId(rs.getInt("ID"));
                postObj.setTittle(rs.getString("Title"));
                postObj.setLink(rs.getString("Link"));
                postObj.setUpvote(rs.getInt("UpvoteCount"));
                postObj.setName(rs.getString("Name"));
                System.out.println("ID = " + rs.getInt("ID"));
                postObj.setTime(Util.getDifference(rs.getTimestamp("Time")));
                postObj.setCommentsCount(rs.getInt("CommentsCount"));
                posts.add(postObj);
            }
        } catch (SQLException e) {
            System.out.println("SQL exception occured" + e);
        }
        return posts;
    }

    public static String UserIDNames(int userID) throws SQLException {
        String name = new String();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/NewsDB?useSSL=false", "root", "");

            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Users WHERE UserID = " + userID);
            if (rs.next()) {
                name = rs.getString("Name");
                System.out.println("user name  " + name);
            }
        } catch (SQLException e) {
            System.out.println("SQL exception occured" + e);
        }
        finally {
            if(rs != null)
            {
                rs.close();
            }
            if (stmt != null)
            {
                stmt.close();
            }
            if (con != null)
            {
                con.close();
            }
        }
        return name;
    }

    public static boolean CheckIdnum(String tableName, String columnName, int id) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/NewsDB?useSSL=false", "root", "");
            stmt = con.createStatement();

            rs = stmt.executeQuery("SELECT " + columnName + " FROM " + tableName + " WHERE ID = " + id);

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("SQL exception occured" + e);
        }
        finally {
            if(rs != null)
            {
                rs.close();
            }
            if (stmt != null)
            {
                stmt.close();
            }
            if (con != null)
            {
                con.close();
            }
        }
        return false;
    }

    public static boolean addCmt(String tableName, int postID, int userID, String text) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/NewsDB?useSSL=false", "root", "");
            pst = con.prepareStatement("insert into Comments(PostID,UserID,Comment,Time) values(?,?,?,?)");
            pst.setInt(1, postID);
            pst.setInt(2, userID);
            pst.setString(3, text);
            pst.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            System.out.println("The time formeted time is ::  " + new Timestamp(System.currentTimeMillis()));
            pst.executeUpdate();
            // increment commentcount in mysql Post table
            pst = con.prepareStatement("UPDATE Posts SET CommentsCount = CommentsCount + 1 WHERE ID = " + postID);

            synchronized (Integer.valueOf(postID)) {
                pst.executeUpdate();
            }
            System.out.println(" Comment added successfully.....");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (pst != null){
                pst.close();
            }
            if (con != null){
                con.close();
            }
        }
        return true;
    }

    public static boolean addNewPost(int id, String title, String link) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con = null;
        PreparedStatement pst = null;
        try {
            System.out.println("Uploading new post into DB..................");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/NewsDB?useSSL=false", "root", "");
            pst = con.prepareStatement("insert into Posts(Title,Link,UpvoteCount,Name,Time,CommentsCount) values(?,?,?,?,?,?)");
            pst.setString(1, title);
            pst.setString(2, link);
            pst.setInt(3, 0);
            pst.setString(4, UserIDNames(id));
            pst.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            pst.setInt(6, 0);
            pst.executeUpdate();
            System.out.println(" New Post added successfully.....");
            System.out.println("New post Title ::  " + title);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (pst != null){
                pst.close();
            }
            if (con != null){
                con.close();
            }
        }
        return true;
    }

    public static void AddVote(int PostID,int userID ) throws SQLException {
        if (insertVote(PostID, userID)) {
            System.out.println("Function addvote is called...... and id num is  " + PostID);
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Connection con = null;
            PreparedStatement pst = null;
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/NewsDB?useSSL=false", "root", "");
//                pst = con.prepareStatement("get PostID from Vote where UserID=" )
                // increment upvote in mysql Post table
                pst = con.prepareStatement("UPDATE Posts SET UpvoteCount = UpvoteCount + 1 WHERE ID = " + PostID);

                synchronized (Integer.valueOf(PostID)) {
                    pst.executeUpdate();
                }
                System.out.println(" upvote added successfully.....");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            }
        }
        else {
            System.out.println("Already Vote is collected from this user and uder id is :: "+ userID + "  for the post of  :: " + PostID);
        }
    }
    public static void DownVote(int PostID,int UserID) throws SQLException {
        if (DelectVote(PostID, UserID)) {
            System.out.println("Function Down vote is called...... and id num is  " + PostID);
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Connection con = null;
            PreparedStatement pst = null;
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/NewsDB?useSSL=false", "root", "");
                // increment upvote in mysql Post table
                pst = con.prepareStatement("UPDATE Posts SET UpvoteCount = UpvoteCount - 1 WHERE ID = " + PostID);

                synchronized (Integer.valueOf(PostID)) {
                    pst.executeUpdate();
                }
                System.out.println(" unvote update successfully..... for the postId :: " + PostID);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            }
        }
        else {
            System.out.println("The User UserID ::  " + UserID + "Vote is not voted for the Posts PostID ::  " + PostID);
        }
    }

    private static boolean DelectVote(int postID, int userID) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con = null;
        PreparedStatement pst = null;
        try {
//            System.out.println("Uploading new post into DB..................");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/NewsDB?useSSL=false", "root", "");
            pst = con.prepareStatement("DELETE FROM Votes where (PostID = " + postID +" and UserID = " + userID +")");
            int checkStatus = pst.executeUpdate();
            System.out.println("Vote deleted successfully....." + checkStatus);
            return checkStatus == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (pst != null){
                pst.close();
            }
            if (con != null){
                con.close();
            }
        }
        return false;
    }

    public static Integer isUserValid(String loggedInUser) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/NewsDB?useSSL=false", "root", "");

            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT UserID FROM Users WHERE Name = '" + loggedInUser + "'");
            if (rs.next()) {
                return rs.getInt("UserID");
            }
        } catch (SQLException e) {
            System.out.println("SQL exception occured" + e);
        }
        finally {
            if(rs != null)
            {
                rs.close();
            }
            if (stmt != null)
            {
                stmt.close();
            }
            if (con != null)
            {
                con.close();
            }
        }
        return null;
    }
    public static boolean insertVote(int PostID,int UserID) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con = null;
        PreparedStatement pst = null;
        try {
//            System.out.println("Uploading new post into DB..................");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/NewsDB?useSSL=false", "root", "");
            pst = con.prepareStatement("insert ignore into Votes(PostID,UserID) values(?,?)");
            pst.setInt(1, PostID);
            pst.setInt(2, UserID);
            int checkStatus = pst.executeUpdate();
            System.out.println("New Post added successfully....." + checkStatus);
            return checkStatus == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (pst != null){
                pst.close();
            }
            if (con != null){
                con.close();
            }
        }
        return false;
    }
    public static boolean AddNewUserName(String name) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/NewsDB?useSSL=false", "root", "");
            pst = con.prepareStatement("insert into Users(Name) value(?)");
            pst.setString(1, name);
            pst.executeUpdate();
            System.out.println(" New User added successfully.....");
            return true;
        } catch (SQLException e) {
            System.out.println("ERROR :: In Adding News User Name");
            e.printStackTrace();
        }
        finally {
            if (pst != null){
                pst.close();
            }
            if (con != null){
                con.close();
            }
        }
     return false;
    }
}