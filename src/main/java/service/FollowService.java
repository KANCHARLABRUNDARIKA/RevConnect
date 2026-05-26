package service;

import dao.FollowDAO;
import model.User;
import java.util.List;

public class FollowService {

    FollowDAO followDAO = new FollowDAO();
    public boolean followUser(int followerId,int followingId){
        return followDAO.followUser(followerId,followingId);
    }

    public boolean unfollowUser(int followerId,int followingId){
        return followDAO.unfollowUser(followerId,followingId);
    }

    public boolean userExists(int id){
        return followDAO.userExists(id);
    }

    public List<User> getFollowers(int userId){
        return followDAO.getFollowers(userId);
    }

    public List<User> getFollowing(int userId){
        return followDAO.getFollowing(userId);
    }
}