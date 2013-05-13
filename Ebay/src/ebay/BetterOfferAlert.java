/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ebay;

/**
 *
 * 
 */
public class BetterOfferAlert implements AlertBid{

    public void Alert(Bid bid) {
        System.out.println("Une enchere meilleur que la votre a ete emise sur l'objet "+bid);
    }

}
