package service;

import dao.NotificationDAO;
import model.Notification;
import java.util.List;

public class NotificationService {

    NotificationDAO dao = new NotificationDAO();

    public List<Notification> getNotifications(int id){
        return dao.getNotifications(id);
    }

    public void markRead(int id){
        dao.markAllRead(id);
    }
}