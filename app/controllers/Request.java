/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import com.sun.xml.internal.ws.developer.UsesJAXBContext;

import models.*;

/**
 *
 * @author Walter Kulisch
 */
public class Request extends Controller {
    
    static String pi;
    static String pd;
    static double pp;
    static String pn;
    static String psn;
    static String pb;
    static String pu;
    static long partnr;
    
    public static void add(String pi, String pd, String pp, String pn, String psn, String pb, String pu, String partnr) {
        Request.pi = pi;
        Request.pd = pd;
        Request.pp = Double.parseDouble(pp);
        Request.pn = pn;
        Request.psn = psn;
        Request.pb = pb;
        Request.pu = pu;
        Request.partnr = Long.parseLong(partnr);
        
        //System.out.println(Request.pi + Request.pd + Request.pp + Request.pn + Request.pu + Request.psn + Request.pb + Request.partnr);
        
        if(Login.loggedIn()) {            
            addItem();
        }
        
        Login.smartIndex();
    }
    
    public static void addItem() {
        //System.out.println(Request.pi + Request.pd + Request.pp + Request.pn + Request.pu + Request.psn + Request.pb + Request.partnr);
        Application.addItemBusiness(Request.partnr, Request.pn, Request.psn, "Men", Request.pb, Request.pi, Request.pp);
    }
    
    public static void success() {
        render();
    }
    
}