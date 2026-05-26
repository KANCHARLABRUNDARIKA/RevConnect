package service;

import dao.MessageDAO;
import model.Message;
import java.util.List;

public class MessageService {

    MessageDAO dao = new MessageDAO();
    public boolean sendMessage(int sender,int receiver,String msg){
        return dao.sendMessage(sender,receiver,msg);
    }

    public List<Message> getMessages(int u1,int u2){
        return dao.getMessages(u1,u2);
    }

    public void markRead(int sender,int receiver){
        dao.markRead(sender,receiver);
    }

    public boolean deleteChat(int u1,int u2){
        return dao.deleteConversation(u1,u2);
    }
}