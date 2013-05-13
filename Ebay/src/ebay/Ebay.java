/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ebay;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 *
 */
public class Ebay {
    static List<Bid> bidList = new ArrayList<Bid>();
    List<User> userList = new ArrayList<User>();
    static User user;

    Ebay(){

    }
    
    public boolean findBid(Object objet, User seller){
    	Bid b = new Bid(new Date(), objet, seller, 0, 0);
    	if(bidList.indexOf(b) == -1)
        	return false;
        else
        	return true;
    }
    
    public void newBid(Object objet, int minimumPrice, int reservePrice, Date date){
        bidList.add(new Bid(date, objet, user, minimumPrice, reservePrice));
    }

    public boolean newUser(User usertemp){
    	if(logUser(usertemp) == true)
    		return false;
    	
    	return userList.add(usertemp);
    }
    
    public boolean logUser(User usertemp){     
        if(userList.indexOf(usertemp) == -1)
        	return false;
        else{
        	user = usertemp;
        	return true;
        }
    }
}
