package dao;

import model.Notification;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {

    public List<Notification> getNotifications(int userId){
        List<Notification> list = new ArrayList<>();
        String query =
                "SELECT * FROM notifications WHERE user_id=? ORDER BY created_at DESC";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1,userId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Notification n = new Notification();
                n.setId(rs.getInt("id"));
                n.setUserId(rs.getInt("user_id"));
                n.setMessage(rs.getString("message"));
                n.setType(rs.getString("type"));
                n.setRead(rs.getBoolean("is_read"));
                list.add(n);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public void markAllRead(int userId){
        String query =
                "UPDATE notifications SET is_read = TRUE WHERE user_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1,userId);
            ps.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}