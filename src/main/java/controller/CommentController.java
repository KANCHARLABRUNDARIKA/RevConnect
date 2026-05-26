package controller;

import model.Comment;
import model.User;
import service.CommentService;
import java.util.List;
import java.util.Scanner;

public class CommentController {
    Scanner sc = new Scanner(System.in);
    CommentService commentService = new CommentService();

    public void addComment(User user) {
        int postId;
        try {
            System.out.println("Enter Post ID:");
            postId = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid Post ID. Please enter number.");
            return;
        }
        try {
            System.out.println("Enter Comment:");
            String comment = sc.nextLine();
            boolean status =
                    commentService.addComment(user.getId(), postId, comment);
            if (status) {
                System.out.println("Comment Added");
            } else {
                System.out.println("Failed to add comment");
            }
        } catch (Exception e) {
            System.out.println("Error adding comment");
            e.printStackTrace();
        }
    }

    public void viewComments() {
        int postId;
        try {
            System.out.println("Enter Post ID:");
            postId = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid Post ID");
            return;
        }
        try {
            List<Comment> comments = commentService.getComments(postId);
            System.out.println("\n===== Comments =====");
            if(comments.isEmpty()){
                System.out.println("No comments found");
                return;
            }
            for (Comment c : comments) {
                System.out.println("----------------------");
                System.out.println("User ID: " + c.getUserId());
                System.out.println("Comment: " + c.getComment());
                System.out.println("Time: " + c.getCreatedAt());
            }
        } catch (Exception e) {
            System.out.println("Error fetching comments");
            e.printStackTrace();
        }
    }

    public void deleteComment() {
        int commentId;
        try {
            System.out.println("Enter Comment ID:");
            commentId = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid Comment ID");
            return;
        }
        try {
            boolean status = commentService.deleteComment(commentId);
            if (status) {
                System.out.println("Comment Deleted");
            } else {
                System.out.println("Failed to delete comment");
            }
        } catch (Exception e) {
            System.out.println("Error deleting comment");
            e.printStackTrace();
        }
    }
}