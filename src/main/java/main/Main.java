package main;

import controller.AuthController;
import controller.DashboardController;
import model.User;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AuthController auth = new AuthController();
        DashboardController dashboard = new DashboardController();
        while (true) {
            try {
                System.out.println("\n===== RevConnect =====");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Forgot Password");
                System.out.println("4. Exit");

                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        auth.register();
                        break;

                    case 2:
                        User user = auth.login();
                        if (user != null) {
                            dashboard.showDashboard(user);
                        } else {
                            System.out.println("Login Failed");
                        }
                        break;

                    case 3:
                        auth.forgotPassword();
                        break;

                    case 4:
                        System.out.println("Exiting Application...");
                        System.exit(0);

                    default:
                        System.out.println("Invalid Choice");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter valid number");
            } catch (Exception e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }
}