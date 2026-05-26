package controller;

import model.User;
import service.LikeService;
import java.util.Scanner;

public class LikeController {
    Scanner sc = new Scanner(System.in);
    LikeService service = new LikeService();
    public void likePost(User user) {
        try {
            System.out.println("Enter Post ID to Like:");
            int postId = Integer.parseInt(sc.nextLine());
            if (!service.postExists(postId)) {
                System.out.println("Post not found");
                return;
            }
            boolean status =
                    service.likePost(user.getId(), postId);
            if (status) {
                System.out.println("Post Liked");
            } else {
                System.out.println("Failed to like post");
            }
        } catch (Exception e) {
            System.out.println("Error while liking post");
        }
    }

    public void unlikePost(User user) {
        try {
            System.out.println("Enter Post ID:");
            int postId = Integer.parseInt(sc.nextLine());
            boolean status =
                    service.unlikePost(user.getId(), postId);
            if (status)
                System.out.println("Post Unliked");
            else
                System.out.println("Failed");
        } catch (Exception e){
            System.out.println("Error unliking post");
        }
    }
}