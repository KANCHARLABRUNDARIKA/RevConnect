package dao;

import model.Post;
import util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {

    public boolean createPost(Post post) {
        try {
            Connection con = DBConnection.getConnection();
            String query = "INSERT INTO posts(user_id,content,hashtags) VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, post.getUserId());
            ps.setString(2, post.getContent());
            ps.setString(3, post.getHashtags());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM posts ORDER BY created_at DESC";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setUserId(rs.getInt("user_id"));
                post.setContent(rs.getString("content"));
                post.setHashtags(rs.getString("hashtags"));
                post.setCreatedAt(rs.getString("created_at"));
                posts.add(post);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }


    public boolean updatePost(int postId, int userId, String content, String hashtags) {
        try {
            Connection con = DBConnection.getConnection();
            String query = "UPDATE posts SET content=?, hashtags=? WHERE id=? AND user_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, content);
            ps.setString(2, hashtags);
            ps.setInt(3, postId);
            ps.setInt(4, userId);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean repost(int userId, int postId){
        try{
            Connection con = DBConnection.getConnection();
            String query =
                    "INSERT INTO posts(user_id, content, hashtags, original_post_id, is_repost) " +
                            "SELECT ?, content, hashtags, id, true FROM posts WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, postId);
            return ps.executeUpdate() > 0;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean share(int sender,int receiver,int postId){
        try{
            Connection con = DBConnection.getConnection();
            String message = "Shared a post ID : " + postId;
            String query = "INSERT INTO messages(sender_id,receiver_id,message) VALUES(?,?,?)";
            PreparedStatement ps =con.prepareStatement(query);
            ps.setInt(1,sender);
            ps.setInt(2,receiver);
            ps.setString(3,message);
            return ps.executeUpdate()>0;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public List<Post> getReposts(int userId){
        List<Post> list = new ArrayList<>();
        try{
            Connection con = DBConnection.getConnection();
            String query =
                    "SELECT * FROM posts WHERE user_id=? AND is_repost=true";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,userId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Post p = new Post();
                p.setId(rs.getInt("id"));
                p.setOriginalPostId(rs.getInt("original_post_id"));
                list.add(p);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
}