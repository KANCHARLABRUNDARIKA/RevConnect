package controller;

import model.User;
import service.AuthService;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthController {
    AuthService service = new AuthService();
    NotificationController notification = new NotificationController();
    Scanner sc = new Scanner(System.in);
    private static final Logger logger =
            LoggerFactory.getLogger(AuthController.class);

    public void register() {
        try {
            System.out.println("Enter Username:");
            String username = sc.nextLine();
            System.out.println("Enter Email:");
            String email = sc.nextLine();
            System.out.println("Enter Password:");
            String password = sc.nextLine();
            System.out.println("Account Type (PERSONAL / CREATOR / BUSINESS):");
            String accountType = sc.nextLine();
            System.out.println("Enter Security Question:");
            String question = sc.nextLine();
            System.out.println("Enter Security Answer:");
            String answer = sc.nextLine();
            User user = new User(
                    username, email, password,
                    accountType, question, answer
            );
            boolean status = service.register(user);
            if (status) {
                logger.info("User Registered: {}", email);
                System.out.println("Registration Successful");
            } else {
                logger.error("Registration Failed");
                System.out.println("Registration Failed");
            }
        } catch (Exception e) {
            logger.error("Registration Error", e);
            System.out.println("Error occurred during registration");
        }
    }
    public User login() {
        try {
            System.out.println("Enter Email:");
            String email = sc.nextLine();
            System.out.println("Enter Password:");
            String password = sc.nextLine();
            User user = service.login(email, password);
            if (user != null) {
                logger.info("User Logged in: {}", email);
                System.out.println("Login Successful");
                notification.viewNotifications(user);
                return user;
            } else {
                logger.warn("Invalid Login: {}", email);
                System.out.println("Invalid Credentials");
            }
        } catch (Exception e) {
            logger.error("Login Error", e);
            System.out.println("Error during login");
        }
        return null;
    }
    public void changePassword(User user){
        try {
            System.out.println("Enter Current Password:");
            String current = sc.nextLine();
            System.out.println("Enter New Password:");
            String newPass = sc.nextLine();
            boolean status =
                    service.changePassword(user.getId(),current,newPass);
            if(status)
                System.out.println("Password Updated");
            else
                System.out.println("Wrong Password");
        } catch (Exception e){
            logger.error("Change Password Error",e);
            System.out.println("Error updating password");
        }
    }

    public void forgotPassword(){
        try {
            System.out.println("Enter Email:");
            String email = sc.nextLine();
            System.out.println("Enter Security Answer:");
            String answer = sc.nextLine();
            if(service.verifySecurity(email,answer)){
                System.out.println("Enter New Password:");
                String pass = sc.nextLine();
                service.resetPassword(email,pass);
                System.out.println("Password Reset Success");
            }
            else{
                System.out.println("Invalid Answer");
            }
        } catch (Exception e){
            logger.error("Forgot Password Error",e);
            System.out.println("Error resetting password");
        }
    }
}