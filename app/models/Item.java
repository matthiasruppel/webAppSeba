package models;

import play.*;
import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Item extends Model {

	public long articleNr;
	
	@Required
	public String title;
	
	public String subtitle;
	
	public String description;
	
	@Required
	public String category;
	
	@Required
	public String brand;
	
	@Required
	public String shop;
	
	public double price;
	
	
    public String userlist;
    
    public int rating;
    
    public ArrayList<String> heartList;
	
	public Item(long articleNr, String title, String subtitle, String category,
			String brand, String shop, double price, String userlist) {
		
		this.articleNr = articleNr;
		this.title = title;
		this.subtitle = subtitle;
		this.category = category;
		this.brand = brand;
		this.shop = shop;
		this.price = price;
		this.userlist = userlist;
		this.heartList = new ArrayList();
		
		
	}
	
	
	
}
