package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import com.sun.xml.internal.ws.developer.UsesJAXBContext;

import models.*;

public class Account extends Controller {

    public static void index() {
        if (Login.loggedIn() == false) {
            flash.success("Please sign-in first.");
            Login.index();
        }
       
        
        String currentUser = session.get("user");
        List<Item> items = Item.findAll();
        List<Item> userItem = new ArrayList();
        List<User> users = User.findAll();
        int j = 0;
        //System.out.println(""+currentUser);
        User theUser = null;
        for (int i = 0; i < users.size(); i++) {
            // 	System.out.println(users.get(i));
            if (users.get(i).mail.equals(currentUser)) {
                theUser = users.get(i);
            }
        }

        //System.out.println("the user: "+theUser);
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).mail.equals(currentUser)) {
                users.remove(i);
            }
        }


        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).userlist.equals(currentUser)) {
            userItem.add(j, items.get(i));
            j++;
            }
            
        }
        
        j = 0;
        List<Picture>pictures = Picture.findAll();
        List<Picture> picture_user = new ArrayList();
         
         	for(int i = 0; i < pictures.size(); i++){
            	if (pictures.get(i).user == (theUser.mail)){
            			if (pictures.get(i).use.equals("avatar")){
        	        		picture_user.add(j, pictures.get(i));
        	        		j++;
           			}
           	}
           }
            
        
        	
        	
        

        if (users.isEmpty()) {
            flash.success("Sorry, no other users");
        }

        if (items.isEmpty()) {
            flash.success("Sorry, no items");
        }


        render(users, currentUser, items, theUser, userItem, picture_user);
    }

    public static void delete(long itemId) {
        Item item = Item.findById(itemId);
        item.delete();
        index();
    }
    
   
    
    public static void uploadPicture(Picture picture) {
        picture.save();
      
        List<User>users = User.findAll();
        for(int i = 0; i < users.size(); i++){
        	if(users.get(i).mail.equals(session.get("user"))){
        		users.get(i).avatar = "http://localhost:9000/account/getpicture?id="+picture.id.toString();
        		picture.user = session.get("user");
        		picture.use = "avatar";
        		picture.save();
        		users.get(i).save();
        	}
        }
        
        Account.index();
    }
    
    public static void getPicture(long id) {
        Picture picture = Picture.findById(id);
        response.setContentTypeIfNotSet(picture.image.type());
        renderBinary(picture.image.get());
        System.out.println("get Picture");
    }
    
}