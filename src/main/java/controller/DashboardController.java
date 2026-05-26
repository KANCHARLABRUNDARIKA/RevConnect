package controller;

import model.User;
import java.util.Scanner;

public class DashboardController {
    Scanner sc = new Scanner(System.in);
    ProfileController profile = new ProfileController();
    PostController post = new PostController();
    LikeController like = new LikeController();
    CommentController comment = new CommentController();
    FollowController follow = new FollowController();
    SearchController search = new SearchController();
    NotificationController notification = new NotificationController();
    ConnectionController connection = new ConnectionController();
    MessageController message = new MessageController();
    BlockController block = new BlockController();
    AuthController auth = new AuthController();

    public void showDashboard(User user) {
        while (true) {
            try {
                System.out.println("\n===== RevConnect Dashboard =====");
                System.out.println("Welcome " + user.getUsername());
                System.out.println("1. Profile");
                System.out.println("2. Posts");
                System.out.println("3. Follow");
                System.out.println("4. Comments");
                System.out.println("5. Messages");
                System.out.println("6. Connections");
                System.out.println("7. Notifications");
                System.out.println("8. Settings");
                System.out.println("9. Logout");
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        profileMenu(user);
                        break;
                    case 2:
                        postMenu(user);
                        break;
                    case 3:
                        followMenu(user);
                        break;
                    case 4:
                        commentMenu(user);
                        break;
                    case 5:
                        messageMenu(user);
                        break;
                    case 6:
                        connectionMenu(user);
                        break;
                    case 7:
                        notification.viewNotifications(user);
                        break;
                    case 8:
                        settingsMenu(user);
                        break;
                    case 9:
                        return;
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Invalid Input");
            }
        }
    }

    public void profileMenu(User user){
        System.out.println("\n===== Profile =====");
        System.out.println("1. View Profile");
        System.out.println("2. Edit Profile");
        System.out.println("3. Set Privacy");
        System.out.println("4. Back");
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice){
            case 1:
                profile.viewProfile(user);
                break;
            case 2:
                profile.editProfile(user);
                break;
            case 3:
                profile.setPrivacy(user);
                break;
            case 4:
                return;
        }
    }

    public void postMenu(User user){
        System.out.println("\n===== Posts =====");
        System.out.println("1. Create Post");
        System.out.println("2. View Feed");
        System.out.println("3. Update Post");
        System.out.println("4. Repost / Share");
        System.out.println("5. View Repost");
        System.out.println("6. Like Post");
        System.out.println("7. Unlike Post");
        System.out.println("8. Back");
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice){
            case 1:
                post.createPost(user);
                break;
            case 2:
                post.viewFeed();
                break;
            case 3:
                post.updatePost(user);
                break;
            case 4:
                post.shareOrRepost(user);
                break;
            case 5:
                post.viewReposts(user);
                break;
            case 6:
                like.likePost(user);
                break;
            case 7:
                like.unlikePost(user);
                break;
            case 8:
                return;
        }
    }

    public void followMenu(User user){
        System.out.println("\n===== Follow =====");
        System.out.println("1. Follow User");
        System.out.println("2. Unfollow User");
        System.out.println("3. View Followers");
        System.out.println("4. View Following");
        System.out.println("5. Search Users");
        System.out.println("6. Back");
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice){
            case 1:
                follow.followUser(user);
                break;
            case 2:
                follow.unfollowUser(user);
                break;
            case 3:
                follow.viewFollowers(user);
                break;
            case 4:
                follow.viewFollowing(user);
                break;
            case 5:
                search.searchUsers(user);
                break;
            case 6:
                return;
        }
    }

    public void commentMenu(User user){
        System.out.println("\n===== Comments =====");
        System.out.println("1. Add Comment");
        System.out.println("2. View Comments");
        System.out.println("3. Delete Comment");
        System.out.println("4. Back");
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice){
            case 1:
                comment.addComment(user);
                break;
            case 2:
                comment.viewComments();
                break;
            case 3:
                comment.deleteComment();
                break;
            case 4:
                return;
        }
    }

    public void messageMenu(User user){
        System.out.println("\n===== Messages =====");
        System.out.println("1. Send Message");
        System.out.println("2. View Messages");
        System.out.println("3. Delete Chat");
        System.out.println("4. Back");
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice){
            case 1:
                message.sendMessage(user);
                break;
            case 2:
                message.viewMessages(user);
                break;
            case 3:
                message.deleteChat(user);
                break;
            case 4:
                return;
        }
    }

    public void connectionMenu(User user){
        System.out.println("\n===== Connections =====");
        System.out.println("1. Send Request");
        System.out.println("2. View Requests");
        System.out.println("3. Accept Request");
        System.out.println("4. Reject Request");
        System.out.println("5. Back");
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice){
            case 1:
                connection.sendRequest(user);
                break;
            case 2:
                connection.viewRequests(user);
                break;
            case 3:
                connection.accept(user);
                break;
            case 4:
                connection.reject(user);
                break;
            case 5:
                return;
        }
    }

    public void settingsMenu(User user){
        System.out.println("\n===== Settings =====");
        System.out.println("1. Change Password");
        System.out.println("2. Forgot Password");
        System.out.println("3. Block User");
        System.out.println("4. View Blocked");
        System.out.println("5. Back");
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice){
            case 1:
                auth.changePassword(user);
                break;
            case 2:
                auth.forgotPassword();
                break;
            case 3:
                block.block(user);
                break;
            case 4:
                block.viewBlocked(user);
                break;
            case 5:
                return;
        }
    }
}