package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        List<User> users = User.findAll();
        render(users);
    }

    public static void notImplemented() {
//    	flash.success("Please sign-in first.");
//    	render("NotImplemented/index.html");
    }
    
    public static void addItemView(){
    	renderTemplate("Application/addItem.html");
    }
    	
    public static void addItem(long articleNr, String title, String subtitle, String category,
            String brand, String shop, double price, String userlist) {
        //render("@addItem", articleNr, title, subtitle, category, brand, shop, price);
        String currentUser = session.get("user");
        List<Item> items = Item.findAll();
        List<User> users = User.findAll();
        //System.out.println(""+currentUser);
        User theUser = null;
        for (int i = 0; i < users.size(); i++) {
            // 	System.out.println(users.get(i));
            if (users.get(i).mail.equals(currentUser)) {
                theUser = users.get(i);
            }
        }
        new Item(articleNr, title, subtitle, category, brand, shop, price, currentUser);
        //System.out.println(userlist);
        Account.index();
    }

    public static void addItemBusiness(long articleNr, String title, String subtitle, String category,
            String brand, String shop, double price) {

        render("@addItem", articleNr, title, subtitle, category, brand, shop, price);
        String currentUser = session.get("user");
        List<Item> items = Item.findAll();
        List<User> users = User.findAll();
        //System.out.println(""+currentUser);
        User theUser = null;
        for (int i = 0; i < users.size(); i++) {
            // 	System.out.println(users.get(i));
            if (users.get(i).mail.equals(currentUser)) {
                theUser = users.get(i);
            }
        }
        Item item = new Item(articleNr, title, subtitle, category, brand, shop, price, theUser.mail);
        item.create();
    }
	
	public static void about(){
		render();
		
	}
	public static void disclaimer(){
		render();
		
	}
	
	public static void privacy(){
		render();
		
	}
	
	public static void show(String cat){
		List<Item> items = Item.findAll();
		List<Item> itemsCat = new ArrayList();
		int j=0;
		for (int i = 0; i < items.size(); i++) {
	         if (items.get(i).category.equals(cat)) {
	            itemsCat.add(j, items.get(i));
	            j++;
	            }
		
		
	}
		render(itemsCat, cat);
	}
}

