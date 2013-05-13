package ebay;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class EbayTest {

	//R1 : utilisateur doit s'identifier par son login (nom et prenom)
	@Test
	public void testUser() {
        Ebay e = new Ebay();
        
        User u = new User("Steve", "Job", "0000");
        User u2 = new User("X", "Men", "5500");
        
        assertFalse(e.logUser(u));
        
        assertTrue(e.newUser(u));
        
        assertFalse(e.newUser(u));
        
        assertTrue(e.logUser(u));
        assertFalse(e.logUser(u2));
	}

	//R2 : Un utilisateur dans son rôle de Vendeur peut créer une enchère et publier cette enchere.
	@Test
	public void testNewBid() {
        Ebay e = new Ebay();
        
        User u = new User("Steve", "Job", "0000");
        //User u2 = new User("X", "Men", "5500");
        
        Date date = new Date();
        date.setDate(date.getDate() - 2);
        Object objet = new Object((int)(Math.random() * (100-0)) + 0, "stylo");

        assertFalse(e.findBid(objet, u));
        
        //Bid bid = new Bid(date, objet, u, 1, 10);
        e.newBid(objet, 1, 100, date);
        
        assertTrue(e.findBid(objet, u));
        
        
        
	}
}
