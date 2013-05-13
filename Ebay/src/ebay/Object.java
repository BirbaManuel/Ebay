/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ebay;

/**
 *
 * 
 */
public class Object {
    int ID;
    String description;

    Object(int ID, String description){
        this.ID = ID;
        this.description = description;
    }

    @Override
    public String toString(){
        return "ID : "+ ID + ", Description : " + description;
    }
}
