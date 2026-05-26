package dao;

import model.Comment;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {


    public List<Comment> getComments(int postId) {
        List<Comment> comments = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM comments WHERE post_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, postId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setUserId(rs.getInt("user_id"));
                comment.setPostId(rs.getInt("post_id"));
                comment.setComment(rs.getString("comment"));
                comment.setCreatedAt(rs.getString("created_at"));
                comments.add(comment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }

    public boolean deleteComment(int commentId) {
        try {
            Connection con = DBConnection.getConnection();
            String query = "DELETE FROM comments WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, commentId);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addComment(int userId,int postId,String comment){
        try{
            Connection con = DBConnection.getConnection();
            String query = "INSERT INTO comments(post_id,user_id,comment) VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,postId);
            ps.setInt(2,userId);
            ps.setString(3,comment);
            int rows = ps.executeUpdate();
            String getOwner ="SELECT user_id FROM posts WHERE id=?";
            PreparedStatement ps2 = con.prepareStatement(getOwner);
            ps2.setInt(1,postId);
            ResultSet rs = ps2.executeQuery();
            if(rs.next()){
                int ownerId = rs.getInt("user_id");

                String notify =
                        "INSERT INTO notifications(user_id,message,type) VALUES(?,?,?)";
                PreparedStatement ps3 =
                        con.prepareStatement(notify);
                ps3.setInt(1,ownerId);
                ps3.setString(2,"Someone commented on your post");
                ps3.setString(3,"COMMENT");
                ps3.executeUpdate();
            }
            return rows > 0;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}