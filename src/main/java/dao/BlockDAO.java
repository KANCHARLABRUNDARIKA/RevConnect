package dao;

import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;

public class BlockDAO {


    public boolean blockUser(int blocker,int blocked){
        try{
            Connection con = DBConnection.getConnection();
            String query =
                    "INSERT INTO blocked_users(blocker_id,blocked_id) VALUES(?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,blocker);
            ps.setInt(2,blocked);
            int rows = ps.executeUpdate();
            return rows>0;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }


    public List<Integer> getBlockedUsers(int userId){
        List<Integer> list = new ArrayList<>();
        try{
            Connection con = DBConnection.getConnection();
            String query =
                    "SELECT blocked_id FROM blocked_users WHERE blocker_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,userId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(rs.getInt("blocked_id"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
}