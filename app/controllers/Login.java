package controllers;

import org.hibernate.loader.custom.Return;

import models.User;
import play.data.validation.Valid;
import play.mvc.*;
import play.mvc.Http.Cookie;
import play.mvc.results.Result;

public class Login extends Controller {
	
	static User user;
	
	@Before
    static void addUser() {
        User user = connected();
        if(user != null) {
            renderArgs.put("user", user);
        }
    }
	
	static User connected() {
        if(renderArgs.get("user") != null) {
            return renderArgs.get("user", User.class);
        }
        String mail = session.get("user");
        if(mail != null) {
            return User.find("mail", mail).first();
        } 
        return null;
    }

	public static void index() {
        if(connected() != null) {

        	index();
  
        }
        
        if(loggedIn()){
        	
        }
        
        render();
    }
	
	public static void register() {
        render();
    }
	
	 public static void saveUser(@Valid User user, String verifyPassword) {
	        validation.required(verifyPassword);
	        validation.equals(verifyPassword, user.password).message("Your password doesn't match");
	        if(validation.hasErrors()) {
	            render("@register", user, verifyPassword);
	        }
	        user.create();
	        session.put("user", user.mail);
	        flash.success("Welcome, " + user.firstName);
	       
	        Account.index();
	        
	    }
	    
	 	public static boolean loggedIn(){
	 		if (Http.Request.current().cookies.get("FriendsBoutiqueCookie") != null){
	 			String mail = Http.Request.current().cookies.get("FriendsBoutiqueCookie").value;
	 			user = User.find("byMail", mail).first();
	 			session.put("user", user.mail);
	 			flash.success("Welcome, " + user.firstName);
	 			Account.index(); 
	 			return true;
	 		}
	 		return false;
	 	}

	    public static void login(String mail, String password) {
	    	
	    	
	    	User user = User.find("byMailAndPassword",mail, password).first();
	        if(user != null) {
	            session.put("user", user.mail);
	            response.setCookie("FriendsBoutiqueCookie", mail, "30d");
	            flash.success("Welcome, " + user.firstName);

	            
	        Account.index();         
	         
	        }
	    	
	        // Oops
	        flash.put("mail", mail);
	        flash.error("Login failed");
	        index();
	    }
	    
	    
	    public static void logout() {
	        session.clear();
	        response.removeCookie("FriendsBoutiqueCookie");
	        index();
	    }
}
