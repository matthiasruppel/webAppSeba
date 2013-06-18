package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import com.sun.xml.internal.ws.developer.UsesJAXBContext;

import models.*;

public class Account extends Controller {
	
	

    public static void index() {
    		String currentUser = session.get("user");
	    	List<Item>items = Item.findAll();
	    	List<User> users = User.findAll();
	    	for(int i = 0; i < users.size(); i++){
	    	 if(users.get(i).mail.equals(currentUser)){
	    		 users.remove(i);
	    	 }
	    	}
	    	
	    	
	    	for(int i = 0; i < items.size(); i++){
		    		if(!items.get(i).userlist.equals(currentUser)){
		    		items.remove(i); 		    		 
		    		}
	    	}
	    	
	    	if (users.isEmpty()){
	            flash.success("Sorry, no other users");
	    	}
	    	
	    	if (items.isEmpty()){
	            flash.success("Sorry, no items");
	    	}
	    
	    	
        render(users, currentUser, items);
    }
    
   
    
   
    

}