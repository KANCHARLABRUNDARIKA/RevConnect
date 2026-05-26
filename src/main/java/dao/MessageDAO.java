package dao;

import model.Message;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {

    public boolean sendMessage(int senderId,int receiverId,String message){
        try{
            Connection con = DBConnection.getConnection();
            String query =
                    "INSERT INTO messages(sender_id,receiver_id,message) VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,senderId);
            ps.setInt(2,receiverId);
            ps.setString(3,message);
            int rows = ps.executeUpdate();
            return rows>0;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }


    public List<Message> getMessages(int user1,int user2){
        List<Message> list = new ArrayList<>();
        try{
            Connection con = DBConnection.getConnection();
            String query =
                    "SELECT * FROM messages WHERE " + "(sender_id=? AND receiver_id=?) OR " + "(sender_id=? AND receiver_id=?) ORDER BY created_at";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,user1);
            ps.setInt(2,user2);
            ps.setInt(3,user2);
            ps.setInt(4,user1);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Message m = new Message();
                m.setId(rs.getInt("id"));
                m.setSenderId(rs.getInt("sender_id"));
                m.setReceiverId(rs.getInt("receiver_id"));
                m.setMessage(rs.getString("message"));
                m.setRead(rs.getBoolean("is_read"));
                list.add(m);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public void markRead(int sender,int receiver){
        try{
            Connection con = DBConnection.getConnection();
            String query =
                    "UPDATE messages SET is_read=true WHERE sender_id=? AND receiver_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,sender);
            ps.setInt(2,receiver);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public boolean deleteConversation(int user1,int user2){
        try{
            Connection con = DBConnection.getConnection();
            String query =
                    "DELETE FROM messages WHERE " + "(sender_id=? AND receiver_id=?) OR " +
                            "(sender_id=? AND receiver_id=?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,user1);
            ps.setInt(2,user2);
            ps.setInt(3,user2);
            ps.setInt(4,user1);
            int rows = ps.executeUpdate();
            return rows>0;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}