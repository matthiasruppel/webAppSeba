package models;

import play.db.jpa.*;
import play.data.validation.*;
import javax.persistence.*;

@Entity
public class User extends Model {
    
	@Required
    @MaxSize(100)
    @MinSize(4)
    public String mail;
	
    @Required
    @MaxSize(50)
    @MinSize(2)
    public String firstName;
    
    @Required
    @MaxSize(50)
    @MinSize(2)
    public String lastName;
    
    @Required
    @MaxSize(20)
    @MinSize(5)
    public String password;
    
    
   
    public User(String firstName, String lastName, String password, String mail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.mail = mail;
    }

    public String toString()  {
        return "User(" + firstName +" "+ lastName + ")";
    }
    
}
