package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Account extends Controller {

    public static void index() {
	    	List<User> users = User.findAll();

        render(users);
    }

}