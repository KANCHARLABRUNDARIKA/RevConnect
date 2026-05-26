package controller;

import model.User;
import service.BlockService;
import java.util.Scanner;
import java.util.List;

public class BlockController {

    BlockService blockService = new BlockService();
    Scanner sc = new Scanner(System.in);

    public void block(User user){
        try {
            System.out.println("Enter User ID to Block:");
            int id = Integer.parseInt(sc.nextLine());
            boolean status =
                    blockService.blockUser(user.getId(),id);
            if(status)
                System.out.println("User Blocked");
            else
                System.out.println("Failed");
        } catch (NumberFormatException e){
            System.out.println("Invalid ID format");
        } catch (Exception e){
            System.out.println("Error blocking user");
            e.printStackTrace();
        }
    }
    public void viewBlocked(User user){
        try {
            List<Integer> list =
                    blockService.getBlockedUsers(user.getId());
            System.out.println("\n===== Blocked Users =====");
            if(list.isEmpty()){
                System.out.println("No blocked users");
                return;
            }
            for(Integer id : list){
                System.out.println("User ID: " + id);
            }
        } catch (Exception e){
            System.out.println("Error fetching blocked users");
            e.printStackTrace();
        }
    }
}