package controller;

import model.User;
import model.Connection;
import service.ConnectionService;
import java.util.List;
import java.util.Scanner;

public class ConnectionController {

    Scanner sc = new Scanner(System.in);
    ConnectionService service = new ConnectionService();

    public void sendRequest(User user){
        try {
            System.out.println("Enter User ID:");
            int id = Integer.parseInt(sc.nextLine());
            if(service.sendRequest(user.getId(), id)){
                System.out.println("Request Sent");
            }else{
                System.out.println("Failed");
            }
        } catch (Exception e){
            System.out.println("Error sending request");
        }
    }


    public void viewRequests(User user){
        try {
            List<Connection> list =
                    service.getPending(user.getId());
            System.out.println("\n===== Requests =====");
            if(list == null || list.isEmpty()){
                System.out.println("No requests found");
                return;
            }
            for(Connection c : list){
                System.out.println("------------------");
                System.out.println("Request ID : " + c.getId());
                System.out.println("From User  : " + c.getSenderId());
                System.out.println("Status     : " + c.getStatus());
            }
        } catch (Exception e){
            System.out.println("Error fetching requests");
        }
    }


    public void accept(User user){
        try {
            System.out.println("Enter Request ID:");
            int id = Integer.parseInt(sc.nextLine());
            boolean status =
                    service.acceptRequest(id,user.getId());
            if(status)
                System.out.println("Request Accepted");
            else
                System.out.println("Failed to accept request");
        } catch (Exception e){
            System.out.println("Error accepting request");
        }
    }


    public void reject(User user){
        try {
            System.out.println("Enter Request ID:");
            int id = Integer.parseInt(sc.nextLine());
            boolean status =
                    service.rejectRequest(id,user.getId());
            if(status)
                System.out.println("Request Rejected");
            else
                System.out.println("Failed to reject request");
        } catch (Exception e){
            System.out.println("Error rejecting request");
        }
    }
}