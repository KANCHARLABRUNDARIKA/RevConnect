package service;

import dao.userDAO;
import model.User;
import java.util.List;

public class SearchService {

    userDAO dao = new userDAO();

    public List<User> search(String keyword,int id){
        return dao.searchUsers(keyword,id);
    }
}