/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ebay;

import java.util.ArrayList;
//import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.Timer;


/**
 *
 * 
 */
public class Bid {
    Date deadline;
    Object object;
    int minimumPrice, reservePrice;
    List<Offer> offer;
    Boolean publishBid, cancelBid, endBid;
    final User seller;
    Timer timer;

    Bid(Date deadline, Object object, User seller, int minimumPrice, int reservePrice){
        this.deadline = deadline;
        this.object = object;
        this.seller = seller;
        this.minimumPrice = minimumPrice;
        this.reservePrice = reservePrice;
        this.publishBid = false;
        this.cancelBid = false;
        this.endBid = false;
        this.offer = new ArrayList<Offer>();

        EndTimeAction actionPerformer = new EndTimeAction(this);
        this.timer = new Timer(60000, actionPerformer);
        this.timer.start();
    }

    Boolean cancelBid(){
        if(offer.isEmpty()){
            cancelBid = true;
            return true;
        }

        if(offer.get(offer.size()-1).price <= reservePrice){
            cancelBid = true;
            return true;
        }
        return false;
    }

    void reservedPrice(){
        if(reservePrice < offer.get(offer.size()-1).price)
            new ReservedPriceAlert().Alert(this);
    }

    Boolean outbid(Offer newOffer){ 
        if(seller == newOffer.user){
            return false;
        }
        if(this.getEtat() == "cree")
        	return false;
        if(minimumPrice > newOffer.price){
            return false;
        }
        if(offer.isEmpty()){
            
            offer.add(newOffer);
            return true;
        }
        if(offer.get(offer.size()-1).price < newOffer.price)
        {
                offer.add(newOffer);
                new BetterOfferAlert().Alert(this);
                reservedPrice();
                return true;
        }
        
        return false;
    }

    Offer getOffer(){
        if(offer.isEmpty())
            return null;
        return offer.get(offer.size()-1);
    }

    int getPrice(){
        if(offer.isEmpty())
            return 0;
        return offer.get(offer.size()-1).price;
    }

    int getReservePrice(){
        return reservePrice;
    }

    void publishBid(){
        publishBid = true;
    }

    void endBid(){
        endBid = true;
    }

    String getEtat(){
        String etat = "";

        etat = "cree";
        //Calendar date = Calendar.getInstance(); le bon usage serait un objet de type Calendar mais son implŽmentation est plus compliquŽ
        Date date = new Date();
        date.setDate(date.getDate());
        
        
        if(this.publishBid == true){
            etat = "publie";
        }
        if(this.deadline.before(date)){
            etat = "terminee";
        } 	
        if(this.cancelBid == true)
        {
            etat = "annulee";
        }
        
        return etat;
    }

    @Override
    public String toString(){
        if(offer.isEmpty()){
            return System.getProperty("line.separator" )
                + "*** Enchere : " + System.getProperty("line.separator" )
                + object + System.getProperty("line.separator" )
                + "Date limite : " + deadline + System.getProperty("line.separator" )
                + "Meilleur offre : aucune d'offre "+ System.getProperty("line.separator" )
                + "Prix de depart : " + minimumPrice + System.getProperty("line.separator" )
                + "Etat : "+ getEtat()+ System.getProperty("line.separator" );
        }

        return System.getProperty("line.separator" )
                + "*** Enchere : " + System.getProperty("line.separator" )
                + object + System.getProperty("line.separator" )
                + "Date limite : " + deadline + System.getProperty("line.separator" )
                + "Meilleur offre : "+ offer.get(offer.size()-1) + System.getProperty("line.separator" )
                + "Prix de depart : " + minimumPrice + System.getProperty("line.separator" )
                + "Etat : "+ getEtat()+ System.getProperty("line.separator" );
    }

    @Override
    public boolean equals(java.lang.Object obj){
        if(this == obj) {
                 return true;
        }
        if (!(obj instanceof Bid)) {
            return false;
        }
        Bid objetother = (Bid) obj;
        if(this.object.ID == objetother.object.ID
        		&& this.seller.firstname == objetother.seller.firstname
        		&& this.seller.surname == objetother.seller.surname)
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 4;
        return hash;
    }
}
