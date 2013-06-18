package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Account extends Controller {

    public static void index() {
	    	List<User> users = User.findAll();
	    	//List<Items> items = Items.findAll();
	    	String currentUser = session.get("user");
        render(users, currentUser);
    }
    
   
    
   
    

}