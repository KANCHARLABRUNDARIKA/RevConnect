package service;

import dao.ConnectionDAO;
import model.Connection;

import java.util.List;

public class ConnectionService {

    ConnectionDAO dao = new ConnectionDAO();

    public boolean sendRequest(int senderId,int receiverId){
        return dao.sendRequest(senderId,receiverId);
    }

    public List<Connection> getPending(int userId){
        return dao.getPending(userId);
    }

    public boolean acceptRequest(int requestId,int userId){
        return dao.acceptRequest(requestId,userId);
    }

    public boolean rejectRequest(int requestId,int userId){
        return dao.rejectRequest(requestId,userId);
    }
}