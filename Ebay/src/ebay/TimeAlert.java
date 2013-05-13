/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ebay;

/**
 *
 *
 */
public class TimeAlert implements AlertBid{
    public void Alert(Bid bid){
        System.out.println("Attention : l'enchere "+bid.object+" est terminee");
    }
}
