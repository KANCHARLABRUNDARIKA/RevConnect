package service;

import dao.userDAO;
import model.User;

public class AuthService {

    userDAO UserDAO = new userDAO();
    public boolean register(User user){
        return UserDAO.register(user);
    }

    public User login(String email,String password){
        return UserDAO.login(email,password);
    }

    public boolean changePassword(int userId,String current,String newPass){
        return UserDAO.changePassword(userId,current,newPass);
    }

    public boolean verifySecurity(String email,String answer){
        return UserDAO.verifySecurity(email,answer);
    }

    public void resetPassword(String email,String pass){
        UserDAO.resetPassword(email,pass);
    }
}