package controller;

import model.User;
import service.FollowService;
import java.util.List;
import java.util.Scanner;

public class FollowController {
    Scanner sc = new Scanner(System.in);
    FollowService service = new FollowService();

    public void followUser(User user) {
        try {
            System.out.println("Enter User ID to Follow:");
            int followingId = Integer.parseInt(sc.nextLine());
            if (followingId == user.getId()) {
                System.out.println("You cannot follow yourself");
                return;
            }
            if (!service.userExists(followingId)) {
                System.out.println("User not found");
                return;
            }
            boolean status =
                    service.followUser(user.getId(), followingId);
            if (status) {
                System.out.println("Followed Successfully");
            } else {
                System.out.println("Failed to Follow");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid User ID");
        } catch (Exception e) {
            System.out.println("Error while following user");
            e.printStackTrace();
        }
    }

    public void unfollowUser(User user) {
        try {
            System.out.println("Enter User ID to Unfollow:");
            int followingId = Integer.parseInt(sc.nextLine());
            boolean status =
                    service.unfollowUser(user.getId(), followingId);
            if (status) {
                System.out.println("Unfollowed Successfully");
            } else {
                System.out.println("Failed to Unfollow");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid User ID");
        } catch (Exception e) {
            System.out.println("Error while unfollowing");
            e.printStackTrace();
        }
    }

    public void viewFollowers(User user) {
        try {
            List<User> followers =
                    service.getFollowers(user.getId());
            System.out.println("\n===== Followers =====");
            if (followers == null || followers.isEmpty()) {
                System.out.println("No followers");
                return;
            }
            for (User u : followers) {
                System.out.println("ID: " + u.getId() + " Name: " + u.getUsername());
            }
        } catch (Exception e) {
            System.out.println("Error fetching followers");
            e.printStackTrace();
        }
    }

    public void viewFollowing(User user) {
        try {
            List<User> following =
                    service.getFollowing(user.getId());
            System.out.println("\n===== Following =====");
            if (following == null || following.isEmpty()) {
                System.out.println("Not following anyone");
                return;
            }
            for (User u : following) {
                System.out.println("ID: " + u.getId() + " Name: " + u.getUsername());
            }
        } catch (Exception e) {
            System.out.println("Error fetching following list");
            e.printStackTrace();
        }
    }
}