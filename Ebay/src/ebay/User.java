/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ebay;

/**
 *
 * 
 */

public class User {
    String surname, firstname, login;

    User(String surname, String firstname, String login){
        this.surname = surname;
        this.firstname = firstname;
        this.login = login;
    }

    @Override
    public String toString(){
        return surname + " "+ firstname;
    }

    @Override
    public boolean equals(java.lang.Object obj){
        if(this == obj) {
                 return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User objetother = (User) obj;
        if(this.firstname.equals(objetother.firstname) 
        		&& this.surname.equals(objetother.surname)
        		&& this.surname.equals(objetother.login))
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }
}
