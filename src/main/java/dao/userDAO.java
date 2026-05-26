package dao;

import model.User;
import util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.ArrayList;

public class userDAO {
    public boolean register(User user) {
        try {
            Connection con = DBConnection.getConnection();
            String query =
                    "insert into users(username,email,password,account_type,security_question,security_answer) values(?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getAccountType());
            ps.setString(5, user.getSecurityQuestion());
            ps.setString(6, user.getSecurityAnswer());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLIntegrityConstraintViolationException e){
            System.out.println("Email already exists");
        } catch (SQLException e){
            System.out.println("Database error during registration");
        } catch (Exception e){
            System.out.println("Unexpected error");
        }
        return false;
    }

    public User login(String email, String password) {
        try {
            Connection con = DBConnection.getConnection();
            String query =
                    "select * from users where email=? and password=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setAccountType(rs.getString("account_type"));
                user.setSecurityQuestion(rs.getString("security_question"));
                user.setSecurityAnswer(rs.getString("security_answer"));
                user.setPrivate(rs.getBoolean("is_private"));
                return user;
            }
        } catch (SQLException e){
            System.out.println("Database error during login");
        } catch (Exception e){
            System.out.println("Unexpected login error");
        }
        return null;
    }

    public User getProfile(int userId) {
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM users WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setBio(rs.getString("bio"));
                user.setLocation(rs.getString("location"));
                user.setWebsite(rs.getString("website"));
                user.setProfilePic(rs.getString("profile_pic"));
                user.setSecurityQuestion(rs.getString("security_question"));
                user.setSecurityAnswer(rs.getString("security_answer"));
                user.setPrivate(rs.getBoolean("is_private"));
                return user;
            }
        } catch (SQLException e){
            System.out.println("Database error loading profile");
        } catch (Exception e){
            System.out.println("Unexpected profile error");
        }
        return null;
    }

    public boolean updateProfile(User user) {
        try {
            Connection con = DBConnection.getConnection();
            String query =
                    "UPDATE users SET bio=?, location=?, website=?, profile_pic=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, user.getBio());
            ps.setString(2, user.getLocation());
            ps.setString(3, user.getWebsite());
            ps.setString(4, user.getProfilePic());
            ps.setInt(5, user.getId());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e){
            System.out.println("Database error updating profile");
        } catch (Exception e){
            System.out.println("Unexpected profile update error");
        }
        return false;
    }

    public List<User> searchUsers(String keyword,int userId) {
        List<User> users = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String query =
                    "SELECT * FROM users WHERE username LIKE ? " +
                            "AND id NOT IN (" +
                            "SELECT blocked_id FROM blocked_users WHERE blocker_id=? )";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "%" + keyword + "%");
            ps.setInt(2, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                users.add(user);
            }
        } catch (SQLException e){
            System.out.println("Database error searching users");
        } catch (Exception e){
            System.out.println("Unexpected search error");
        }
        return users;
    }

    public boolean changePassword(int userId, String current, String newPass){
        try {
            Connection con = DBConnection.getConnection();
            String query =
                    "UPDATE users SET password=? WHERE id=? AND password=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,newPass);
            ps.setInt(2,userId);
            ps.setString(3,current);
            return ps.executeUpdate()>0;
        } catch (SQLException e){
            System.out.println("Database error changing password");
        } catch (Exception e){
            System.out.println("Unexpected password error");
        }
        return false;
    }

    public boolean verifySecurity(String email, String answer){
        try {
            Connection con = DBConnection.getConnection();
            String query =
                    "SELECT * FROM users WHERE email=? AND security_answer=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, answer);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e){
            System.out.println("Database error verifying security");
        } catch (Exception e){
            System.out.println("Unexpected security error");
        }
        return false;
    }

    public boolean updatePrivacy(int userId, boolean status) {
        try {
            Connection con = DBConnection.getConnection();
            String query =
                    "UPDATE users SET is_private=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setBoolean(1, status);
            ps.setInt(2, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e){
            System.out.println("Database error updating privacy");
        } catch (Exception e){
            System.out.println("Unexpected privacy error");
        }
        return false;
    }

    public boolean resetPassword(String email,String newPassword){
        try {
            Connection con = DBConnection.getConnection();
            String query =
                    "UPDATE users SET password=? WHERE email=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,newPassword);
            ps.setString(2,email);
            return ps.executeUpdate()>0;
        } catch (SQLException e){
            System.out.println("Database error resetting password");
        } catch (Exception e){
            System.out.println("Unexpected reset password error");
        }
        return false;
    }
}