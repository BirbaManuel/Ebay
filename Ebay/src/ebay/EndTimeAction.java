/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ebay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 *
 * 
 */
public class EndTimeAction implements ActionListener{
    private final Bid bid;

    public EndTimeAction(Bid bid){
        this.bid = bid;
    }

    public void actionPerformed(ActionEvent e) {
        if(bid.deadline.after(new Date()) == false){
            bid.endBid();
            new TimeAlert().Alert(bid);
        }
    }


}
