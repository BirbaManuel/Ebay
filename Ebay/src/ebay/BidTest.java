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

    // Le vendeur a la possibilité de préciser un prix minimum pour son enchère. Il n'est pas
    // possible d'émettre une offre en dessous du prix minimum. Le prix minimum d'une enchère
    // est visible pour tous les utilisateurs du système.
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

    //Le vendeur a la possibilité de préciser un prix minimum pour son enchère. Il n'est pas
    //possible d'émettre une offre en dessous du prix minimum. Le prix minimum d'une enchère
    //est visible pour tous les utilisateurs du système.
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

    // Le vendeur ‡ la possibilité d'annuler une enchère, si et seulement si, aucune offre sur
    // cette enchère n'a atteint le prix de réserve de l'enchère.
    // Un vendeur ne peut pas émettre une offre sur une de ses enchères.
    // Voici les différents états possibles d'une enchère :
    // Etat créée : l'enchère n'est visible que par le vendeur.
    // Etat publiée : l'enchère est visible par tous les utilisateurs du système
    // Etat annulée : l'enchère est annulée par le vendeur Cette enchère reste visible
    // dans le système par le vendeur et tous les acheteurs ayant émis au moins une offre
    // pour cette enchère.
    // Etat terminée : la date limite de l'enchère a été atteinte. L'offre la plus haute est
    // celle qui remporte l'enchère.
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