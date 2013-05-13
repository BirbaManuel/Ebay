/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ebay;

/**
 *
 * 
 */
public class ReservedPriceAlert implements AlertBid{

    public void Alert(Bid bid) {
        System.out.println("Le prix de reserve de l'ojet"+ bid.object + " a ete atteint");
    }

}
