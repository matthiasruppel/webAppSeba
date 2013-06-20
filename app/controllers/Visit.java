package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import com.sun.xml.internal.ws.developer.UsesJAXBContext;

import models.*;

public class Visit extends Controller {

    public static void index(String mail) {
    	if (Login.loggedIn() == false) {
            flash.success("Please sign-in first.");
            Login.index();
        }
    	
    	
        String currentUser = session.get("user");
        List<Item> items = Item.findAll();
        List<Item> userItem = new ArrayList();
        List<User> users = User.findAll();
        int j = 0;
        User theUser = null;
        String visitedUserFirst = "";
        String visitedUserLast = "";
        for (int i = 0; i < users.size(); i++) {
            // 	System.out.println(users.get(i));
            if (users.get(i).mail.equals(currentUser)) {
                theUser = users.get(i);
            }
            if (users.get(i).mail.equals(mail)){
            	visitedUserFirst = users.get(i).firstName;
            	visitedUserLast = users.get(i).lastName;
            }
        }
        
        
        
        //System.out.println("the user: "+theUser);
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).mail.equals(currentUser)) {
                users.remove(i);
            }
        }


        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).userlist.equals(mail)) {
            userItem.add(j, items.get(i));
            j++;
            }
            
        }

        if (users.isEmpty()) {
            flash.success("Sorry, no other users");
        }

        if (items.isEmpty()) {
            flash.success("Sorry, no items");
        }


        render(users, currentUser, items, theUser, userItem, mail, visitedUserFirst, visitedUserLast);
    

    }
}
