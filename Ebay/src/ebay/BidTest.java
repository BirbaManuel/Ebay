/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ebay;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * 
 */
public class BidTest {

    public BidTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of outbid method, of class Bid.
     */

    // aucune offre en dessous du prix minimum
    @Test
    public void testOutbid() {
        Date deadline = new Date(2040, 12, 30, 3, 55);
        Object object = new Object(01, "Lait");
        User seller = new User("VENDEUR", "Miam", "");
        Bid bid = new Bid(deadline , object, seller, 400, 400);
        bid.publishBid();
        
        Offer offer0 = new Offer(seller, 300);
        assertFalse(bid.outbid(offer0));

        User user = new User("Steeve", "Jobs", "");

        offer0 = new Offer(user, 300);
        assertFalse(bid.outbid(offer0));

        Offer offer1 = new Offer(user, 500);
        assertTrue(bid.outbid(offer1));
        
        Offer offer2 = new Offer(user, 1000);
        assertTrue(bid.outbid(offer2));
        assertFalse(bid.outbid(offer1));
    }

    // Vendeur peut annule l'enchere
    @Test
    public void testCancelBid() {
        Date deadline = new Date(2040, 12, 30, 3, 55);
        Object object = new Object(01, "MacBook");
        User seller = new User("VENDEUR", "Steph", "");
        Bid bid = new Bid(deadline , object, seller, 400, 400);
        
        assertTrue(bid.cancelBid());
        
        User user = new User("Steeve", "Jobs", "");
        Offer offer1 = new Offer(user, 300);
        bid.outbid(offer1);
        assertTrue(bid.cancelBid());

        Offer offer2 = new Offer(user, 1000);
        bid.outbid(offer2);
        assertFalse(bid.cancelBid());
    }

    // Le vendeur a la possibilit� de pr�ciser un prix minimum pour son ench�re. Il n'est pas
    // possible d'�mettre une offre en dessous du prix minimum. Le prix minimum d'une ench�re
    // est visible pour tous les utilisateurs du syst�me.
    @Test
    public void testGetOffer() {
        Date deadline = new Date(2040, 12, 30, 3, 55);
        Object object = new Object(01, "MacBook");
        User seller = new User("VENDEUR", "Steph", "");
        Bid bid = new Bid(deadline , object, seller, 400, 200);

        assertTrue(bid.getOffer() == null);

        User user = new User("Steeve", "Jobs", "");
        Offer offer0 = new Offer(user, 600);
        bid.outbid(offer0);
        assertFalse(bid.getOffer() != null);
        
        bid.publishBid();
        bid.outbid(offer0);
        assertTrue(bid.getOffer().equals(offer0));
        
        User user2 = new User("tap", "man", "");
        Offer offer1 = new Offer(user, 400);
        bid.outbid(offer1);
        assertFalse(bid.getOffer().equals(offer1));
        
        Offer offer2 = new Offer(user, 800);
        bid.outbid(offer2);
        assertTrue(bid.getOffer().equals(offer2));
    }

    /**
     * Test of getPrice method, of class Bid.
     */
    @Test
    public void testGetPrice() {
        Date deadline = new Date(2040, 12, 30, 3, 55);
        Object object = new Object(01, "MacBook");
        User seller = new User("VENDEUR", "Steph", "");
        Bid bid = new Bid(deadline , object, seller, 400, 200);
        bid.publishBid();

        assertTrue(bid.getPrice() == 0);

        User user = new User("Steeve", "Jobs", "");
        Offer offer0 = new Offer(user, 600);
        bid.outbid(offer0);
        assertTrue(bid.getPrice() == 600);
    }

    //Le vendeur a la possibilit� de pr�ciser un prix minimum pour son ench�re. Il n'est pas
    //possible d'�mettre une offre en dessous du prix minimum. Le prix minimum d'une ench�re
    //est visible pour tous les utilisateurs du syst�me.
    @Test
    public void testGetReservePrice() {
        Date deadline = new Date(2040, 12, 30, 3, 55);
        Object object = new Object(01, "MacBook");
        User seller = new User("VENDEUR", "Steph", "");
        Bid bid = new Bid(deadline , object, seller, 400, 200);

        assertTrue(bid.getReservePrice() == 200);

        User user = new User("Steeve", "Jobs", "");
        Offer offer0 = new Offer(user, 600);
        bid.outbid(offer0);
        assertTrue(bid.getReservePrice() == 200);
    }

    // Le vendeur � la possibilit� d'annuler une ench�re, si et seulement si, aucune offre sur
    // cette ench�re n'a atteint le prix de r�serve de l'ench�re.
    // Un vendeur ne peut pas �mettre une offre sur une de ses ench�res.
    // Voici les diff�rents �tats possibles d'une ench�re :
    // Etat cr��e : l'ench�re n'est visible que par le vendeur.
    // Etat publi�e : l'ench�re est visible par tous les utilisateurs du syst�me
    // Etat annul�e : l'ench�re est annul�e par le vendeur Cette ench�re reste visible
    // dans le syst�me par le vendeur et tous les acheteurs ayant �mis au moins une offre
    // pour cette ench�re.
    // Etat termin�e : la date limite de l'ench�re a �t� atteinte. L'offre la plus haute est
    // celle qui remporte l'ench�re.
    @Test
    public void testGetEtat() {
    	Date deadline = new Date();
    	deadline.setDate(deadline.getDate());
    	deadline.setHours(0);
        Object object = new Object(01, "MacBook");
        User seller = new User("VENDEUR", "Steph", "");
        Bid bid = new Bid(deadline , object, seller, 400, 200);

        assertFalse(bid.getEtat() == "cree");

        assertTrue(bid.getEtat().equals("terminee"));
        
    	Date deadline2 = new Date(2040, 12, 30, 3, 55);
    	Object object2 = new Object(01, "MacBook");
        User seller2 = new User("VENDEUR", "Steeve", "");
        Bid bid2 = new Bid(deadline2, object2, seller2, 400, 200);

        assertTrue(bid2.getEtat() == "cree");

        bid2.publishBid();
        assertTrue(bid2.getEtat().equals("publie"));

        assertFalse(bid2.getEtat().equals("terminee"));

        bid2.cancelBid();
        assertTrue(bid2.getEtat().equals("annulee"));

        //publishBid, cancelBid, endBid;
    }
}