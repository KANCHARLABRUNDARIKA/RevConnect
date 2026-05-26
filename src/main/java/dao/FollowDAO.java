package dao;

import model.User;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FollowDAO {

    public boolean followUser(int followerId, int followingId) {
        try {
            Connection con = DBConnection.getConnection();
            String query = "INSERT INTO followers(follower_id, following_id) VALUES(?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, followerId);
            ps.setInt(2, followingId);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean unfollowUser(int followerId, int followingId) {
        try {
            Connection con = DBConnection.getConnection();
            String query = "DELETE FROM followers WHERE follower_id=? AND following_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, followerId);
            ps.setInt(2, followingId);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<User> getFollowing(int userId) {
        List<User> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT u.id, u.username FROM followers f " +
                    "JOIN users u ON f.following_id = u.id " +
                    "WHERE f.follower_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<User> getFollowers(int userId) {
        List<User> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT u.id, u.username FROM followers f " +
                    "JOIN users u ON f.follower_id = u.id " +
                    "WHERE f.following_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean userExists(int userId) {
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT id FROM users WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}