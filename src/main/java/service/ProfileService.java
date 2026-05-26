package service;

import dao.userDAO;
import model.User;

public class ProfileService {

    userDAO dao = new userDAO();

    public User getProfile(int id){
        return dao.getProfile(id);
    }

    public boolean updateProfile(User user){
        return dao.updateProfile(user);
    }

    public void setPrivacy(int id,boolean status){
        dao.updatePrivacy(id,status);
    }
}