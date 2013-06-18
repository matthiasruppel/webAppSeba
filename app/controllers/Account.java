package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Account extends Controller {

    public static void index() {
    		String currentUser = session.get("user");
	    	List<User> users = User.findAll();
	    	for(int i = 0; i < users.size(); i++){
	    	 if(users.get(i).mail.equals(currentUser)){
	    		 users.remove(i);
	    	 }
	    	}
	    
	    	//List<Items> items = Items.findAll();
	    	
        render(users, currentUser);
    }
    
   
    
   
    

}