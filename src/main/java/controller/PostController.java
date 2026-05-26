package controller;

import model.Post;
import model.User;
import service.PostService;
import java.util.List;
import java.util.Scanner;

public class PostController {

    Scanner sc = new Scanner(System.in);
    PostService service = new PostService();

    public void createPost(User user) {
        try {
            System.out.println("Enter Post Content:");
            String content = sc.nextLine();
            if(content.trim().isEmpty()){
                System.out.println("Post content cannot be empty");
                return;
            }
            System.out.println("Enter Hashtags:");
            String hashtags = sc.nextLine();
            Post post = new Post(user.getId(),content,hashtags);
            boolean status = service.createPost(post);
            if(status)
                System.out.println("Post Created Successfully");
            else
                System.out.println("Failed to create post");
        }catch(Exception e){
            System.out.println("Error creating post");
        }
    }

    public void viewFeed(){
        try {
            List<Post> list = service.getAllPosts();
            if(list.isEmpty()){
                System.out.println("No posts available");
                return;
            }
            System.out.println("\n===== Feed =====");
            for(Post p : list){
                System.out.println("--------------------");
                System.out.println("User ID : " + p.getUserId());
                System.out.println("Post    : " + p.getContent());
                System.out.println("Tags    : " + p.getHashtags());
                System.out.println("Time    : " + p.getCreatedAt());
            }
        }catch(Exception e){
            System.out.println("Error loading feed");
        }
    }

    public void updatePost(User user){
        try {
            System.out.println("Enter Post ID:");
            int id = Integer.parseInt(sc.nextLine());
            System.out.println("Enter Content:");
            String content = sc.nextLine();
            System.out.println("Enter Hashtags:");
            String hashtags = sc.nextLine();
            boolean status =
                    service.updatePost(id,user.getId(),content,hashtags);
            if(status)
                System.out.println("Post Updated Successfully");
            else
                System.out.println("Invalid Post ID or Unauthorized");
        }catch(NumberFormatException e){
            System.out.println("Invalid Post ID");
        }catch(Exception e){
            System.out.println("Error updating post");
        }
    }

    public void shareOrRepost(User user){
        try {
            System.out.println("Enter Post ID:");
            int id = Integer.parseInt(sc.nextLine());
            System.out.println("1. Repost");
            System.out.println("2. Share");
            int ch = Integer.parseInt(sc.nextLine());
            if(ch==1)
                repost(user,id);
            else if(ch==2)
                share(user,id);
            else
                System.out.println("Invalid Choice");
        }catch(NumberFormatException e){
            System.out.println("Invalid Input");
        }catch(Exception e){
            System.out.println("Error processing request");
        }
    }

    public void repost(User user,int id){
        try {
            boolean status =
                    service.repost(user.getId(),id);
            if(status)
                System.out.println("Reposted Successfully");
            else
                System.out.println("Invalid Post ID");
        }catch(Exception e){
            System.out.println("Error reposting");
        }
    }

    public void share(User user,int id){
        try {
            System.out.println("Enter Receiver ID:");
            int r = Integer.parseInt(sc.nextLine());
            boolean status =
                    service.share(user.getId(),r,id);
            if(status)
                System.out.println("Post Shared Successfully");
            else
                System.out.println("Invalid Receiver or Post ID");
        }catch(NumberFormatException e){
            System.out.println("Invalid User ID");
        }catch(Exception e){
            System.out.println("Error sharing post");
        }
    }

    public void viewReposts(User user){
        try {
            List<Post> list =
                    service.getReposts(user.getId());
            System.out.println("\n===== Your Reposts =====");
            if(list.isEmpty()){
                System.out.println("No reposts found");
                return;
            }
            for(Post p:list){
                System.out.println("--------------------");
                System.out.println("Repost ID : "+p.getId());
                System.out.println("Original Post : "+p.getOriginalPostId());
            }
        }catch(Exception e){
            System.out.println("Error loading reposts");
        }
    }
}