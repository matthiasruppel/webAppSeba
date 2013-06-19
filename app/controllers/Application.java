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
    
    public static void notImplemented(){
//    	flash.success("Please sign-in first.");  	
//    	render("NotImplemented/index.html");
    }

	public static void addItem(long articleNr, String title, String subtitle, String category,
			String brand, String shop, double price, String userlist){
		
		render("@addItem", articleNr, title, subtitle, category,brand, shop, price);
		String currentUser = session.get("user");
    	List<Item>items = Item.findAll();
    	List<User> users = User.findAll();
    	//System.out.println(""+currentUser);
    	User theUser =null;
    	for(int i = 0; i < users.size(); i++){
	   // 	System.out.println(users.get(i));
	    	if(users.get(i).mail.equals(currentUser)){
	    		 theUser=users.get(i);
	    	 }
	    }
    	new Item( articleNr,  title,  subtitle,  category,brand,  shop,  price,  theUser.mail);
    	System.out.println(userlist);
    	Account.index();
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
	
}

