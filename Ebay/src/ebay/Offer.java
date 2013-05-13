/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ebay;

/**
 *
 * 
 */
public class Offer {
    User user;
    int price;

    Offer(User user, int price){
        this.user = user;
        this.price = price;
    }

    @Override
    public String toString(){
        return "Encherisseur : "+ user + ", Prix :" + price;
    }
}
