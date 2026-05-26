package dao;

import util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LikeDAO {

    public boolean unlikePost(int userId, int postId) {
        try {
            Connection con = DBConnection.getConnection();
            String query = "DELETE FROM likes WHERE user_id=? AND post_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, postId);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean postExists(int postId) {
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT id FROM posts WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, postId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean likePost(int userId, int postId){
        try{
            Connection con = DBConnection.getConnection();
            String query =
                    "INSERT INTO likes(user_id,post_id) VALUES(?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,userId);
            ps.setInt(2,postId);
            int rows = ps.executeUpdate();

            String getOwner =
                    "SELECT user_id FROM posts WHERE id=?";
            PreparedStatement ps2 =
                    con.prepareStatement(getOwner);
            ps2.setInt(1,postId);
            ResultSet rs = ps2.executeQuery();
            if(rs.next()){
                int ownerId = rs.getInt("user_id");

                String notify =
                        "INSERT INTO notifications(user_id,message,type) VALUES(?,?,?)";
                PreparedStatement ps3 =
                        con.prepareStatement(notify);
                ps3.setInt(1,ownerId);
                ps3.setString(2,"Someone liked your post");
                ps3.setString(3,"LIKE");
                ps3.executeUpdate();
            }
            return rows > 0;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}