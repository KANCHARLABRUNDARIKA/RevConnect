package controller;

import model.Message;
import model.User;
import service.MessageService;
import java.util.List;
import java.util.Scanner;

class MessageController {

    MessageService service = new MessageService();
    Scanner sc = new Scanner(System.in);
    public void sendMessage(User user){
        try {
            System.out.println("Enter Receiver ID:");
            int receiver = Integer.parseInt(sc.nextLine());
            System.out.println("Enter Message:");
            String msg = sc.nextLine();
            boolean status =
                    service.sendMessage(user.getId(),receiver,msg);
            if(status)
                System.out.println("Message Sent");
            else
                System.out.println("Failed");
        } catch (NumberFormatException e){
            System.out.println("Invalid User ID");
        } catch (Exception e){
            System.out.println("Error sending message");
        }
    }

    public void viewMessages(User user){
        try {
            System.out.println("Enter User ID:");
            int other = Integer.parseInt(sc.nextLine());
            List<Message> list = service.getMessages(user.getId(),other);
            if(list.isEmpty()){
                System.out.println("No messages found");
                return;
            }
            System.out.println("\n===== Messages =====");
            for(Message m : list){
                if(m.getSenderId() == user.getId()){
                    System.out.println("You : " + m.getMessage());
                }else{
                    System.out.println("User " + m.getSenderId() +
                            " : " + m.getMessage());
                }
            }
            service.markRead(other,user.getId());
        } catch (NumberFormatException e){
            System.out.println("Invalid User ID");
        } catch (Exception e){
            System.out.println("Error viewing messages");
        }
    }

    public void deleteChat(User user){
        try {
            System.out.println("Enter User ID:");
            int other = Integer.parseInt(sc.nextLine());
            boolean status =
                    service.deleteChat(user.getId(),other);
            if(status)
                System.out.println("Chat deleted");
            else
                System.out.println("No chat found");
        } catch (NumberFormatException e){
            System.out.println("Invalid User ID");
        } catch (Exception e){
            System.out.println("Error deleting chat");
        }
    }
}