package controller;

import model.User;
import service.ProfileService;
import java.util.Scanner;

public class ProfileController {
    Scanner sc = new Scanner(System.in);
    ProfileService service = new ProfileService();

    public void viewProfile(User user){
        try {
            User u = service.getProfile(user.getId());
            System.out.println("\n===== Profile =====");
            System.out.println("Username : " + u.getUsername());
            System.out.println("Email    : " + u.getEmail());
            System.out.println("Bio      : " +
                    (u.getBio()!=null ? u.getBio() : "Not set"));
            System.out.println("Location : " +
                    (u.getLocation()!=null ? u.getLocation() : "Not set"));
            System.out.println("Website  : " +
                    (u.getWebsite()!=null ? u.getWebsite() : "Not set"));
            System.out.println("Profile Pic : " +
                    (u.getProfilePic()!=null ? u.getProfilePic() : "Not set"));
            System.out.println("Privacy  : " +
                    (u.isPrivate() ? "Private" : "Public"));
        } catch (Exception e){
            System.out.println("Error loading profile");
        }
    }

    public void setPrivacy(User user){
        System.out.println("1.Public 2.Private");
        int ch = Integer.parseInt(sc.nextLine());
        service.setPrivacy(user.getId(),ch==2);
    }

    public void editProfile(User user){
        try {
            System.out.println("Leave blank to keep existing value");
            System.out.println("Enter Bio:");
            String bio = sc.nextLine();
            System.out.println("Enter Location:");
            String location = sc.nextLine();
            System.out.println("Enter Website:");
            String website = sc.nextLine();
            System.out.println("Enter Profile Picture URL:");
            String profilePic = sc.nextLine();

            if(!bio.trim().isEmpty()){
                user.setBio(bio);
            }
            if(!location.trim().isEmpty()){
                user.setLocation(location);
            }
            if(!website.trim().isEmpty()){
                user.setWebsite(website);
            }
            if(!profilePic.trim().isEmpty()){
                user.setProfilePic(profilePic);
            }
            boolean status = service.updateProfile(user);
            if(status){
                System.out.println("Profile Updated Successfully");
            }else{
                System.out.println("Update Failed");
            }
        } catch (Exception e){
            System.out.println("Error updating profile");
        }
    }
}