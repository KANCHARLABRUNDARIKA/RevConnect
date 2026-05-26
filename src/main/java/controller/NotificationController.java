package controller;

import model.Notification;
import model.User;
import service.NotificationService;
import java.util.List;
import java.util.Scanner;

public class NotificationController {
    NotificationService service = new NotificationService();
    Scanner sc = new Scanner(System.in);

    public void viewNotifications(User user){
        try {
            List<Notification> list =
                    service.getNotifications(user.getId());
            if(list.isEmpty()){
                System.out.println("No Notifications");
                return;
            }
            for(Notification n : list){
                System.out.println("Type: " + n.getType());
                System.out.println("Message: " + n.getMessage());
            }
            markAsRead(user);
        } catch (Exception e){
            System.out.println("Error fetching notifications");
        }
    }

    public void markAsRead(User user){
        try {
            System.out.println("Mark read? yes/no");
            String ch = sc.nextLine();
            if(ch.equalsIgnoreCase("yes"))
                service.markRead(user.getId());
        } catch (Exception e){
            System.out.println("Error marking");
        }
    }
}