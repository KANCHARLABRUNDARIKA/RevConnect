package controller;

import model.User;
import service.SearchService;
import java.util.List;
import java.util.Scanner;

public class SearchController {
    SearchService service = new SearchService();
    Scanner sc = new Scanner(System.in);

    public void searchUsers(User user){
        System.out.println("Search:");
        String key = sc.nextLine();
        List<User> list =
                service.search(key,user.getId());
        for(User u : list){
            System.out.println(u.getUsername());
        }
    }
}