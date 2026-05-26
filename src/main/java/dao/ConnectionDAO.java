package dao;

import util.DBConnection;
import model.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ConnectionDAO {

    public boolean sendRequest(int senderId, int receiverId){

        try{
            if(senderId == receiverId){
                System.out.println("Cannot send request to yourself");
                return false;
            }
            java.sql.Connection con = DBConnection.getConnection();
            String check =
                    "SELECT * FROM connections WHERE sender_id=? AND receiver_id=? AND status='PENDING'";
            PreparedStatement cps = con.prepareStatement(check);
            cps.setInt(1, senderId);
            cps.setInt(2, receiverId);
            ResultSet crs = cps.executeQuery();

            if(crs.next()){
                System.out.println("Request already sent");
                return false;
            }

            String query =
                    "INSERT INTO connections(sender_id,receiver_id,status) VALUES(?,?,?)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, senderId);
            ps.setInt(2, receiverId);
            ps.setString(3, "PENDING");

            int rows = ps.executeUpdate();

            String notify =
                    "INSERT INTO notifications(user_id,message,type) VALUES(?,?,?)";

            PreparedStatement nps = con.prepareStatement(notify);
            nps.setInt(1, receiverId);
            nps.setString(2, "New Connection Request from User " + senderId);
            nps.setString(3, "REQUEST");
            nps.executeUpdate();

            return rows > 0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }


    public boolean acceptRequest(int requestId, int userId){

        try{

            java.sql.Connection con = DBConnection.getConnection();

            String check =
                    "SELECT * FROM connections WHERE id=? AND receiver_id=? AND status='PENDING'";

            PreparedStatement ps = con.prepareStatement(check);

            ps.setInt(1,requestId);
            ps.setInt(2,userId);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                String query =
                        "UPDATE connections SET status='ACCEPTED' WHERE id=?";

                PreparedStatement ps2 =
                        con.prepareStatement(query);

                ps2.setInt(1,requestId);

                return ps2.executeUpdate()>0;

            }else{
                System.out.println("Request not found or already handled");
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }


    public boolean rejectRequest(int requestId, int userId){

        try{

            java.sql.Connection con = DBConnection.getConnection();

            String check =
                    "SELECT * FROM connections WHERE id=? AND receiver_id=? AND status='PENDING'";

            PreparedStatement ps = con.prepareStatement(check);

            ps.setInt(1,requestId);
            ps.setInt(2,userId);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                String query =
                        "DELETE FROM connections WHERE id=?";

                PreparedStatement ps2 =
                        con.prepareStatement(query);

                ps2.setInt(1,requestId);

                return ps2.executeUpdate()>0;

            }else{
                System.out.println("Request not found or already handled");
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }


    public List<Connection> getPending(int userId){

        List<Connection> list = new ArrayList<>();

        try{

            java.sql.Connection con = DBConnection.getConnection();

            String query =
                    "SELECT * FROM connections WHERE receiver_id=? AND status='PENDING'";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                Connection c = new Connection();

                c.setId(rs.getInt("id"));
                c.setSenderId(rs.getInt("sender_id"));
                c.setReceiverId(rs.getInt("receiver_id"));
                c.setStatus(rs.getString("status"));

                list.add(c);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
}