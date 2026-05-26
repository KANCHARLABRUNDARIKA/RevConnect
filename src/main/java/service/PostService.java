package service;

import dao.PostDAO;
import model.Post;
import java.util.List;

public class PostService {

    PostDAO postDAO = new PostDAO();

    public boolean createPost(Post post){
        return postDAO.createPost(post);
    }

    public List<Post> getAllPosts(){
        return postDAO.getAllPosts();
    }

    public boolean updatePost(int id,int user,String content,String hashtags){
        return postDAO.updatePost(id,user,content,hashtags);
    }

    public boolean repost(int userId,int postId){
        return postDAO.repost(userId,postId);
    }

    public boolean share(int sender,int receiver,int postId){
        return postDAO.share(sender,receiver,postId);
    }

    public List<Post> getReposts(int userId){
        return postDAO.getReposts(userId);
    }
}