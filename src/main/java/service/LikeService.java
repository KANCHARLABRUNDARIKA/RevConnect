package service;

import dao.LikeDAO;

public class LikeService {

    LikeDAO likeDAO = new LikeDAO();
    public boolean postExists(int postId){
        return likeDAO.postExists(postId);
    }

    public boolean likePost(int userId,int postId){
        return likeDAO.likePost(userId,postId);
    }

    public boolean unlikePost(int userId,int postId){
        return likeDAO.unlikePost(userId,postId);
    }
}