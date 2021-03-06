/*
	Sapir Ezra	313546194
	Coral Rubilar	316392877
	Moriel Turjeman	308354968
*/

package il.ac.shenkar.finalProject.costmanager.model;


import java.util.ArrayList;
import java.util.Arrays;

public class Category /**
 This class represents a category of the item that the user inserts (for example it could be rent, phone bill, etc).
 */{

     private String category;

    public Category(String category)
    /***
     * Initialize the Category. Receives a String, returns nothing.
     */
    {
         this.category= category;
    }


    public String getCategory()
    /***
     * Get the Category. Receives nothing, returns a String.
     */
    {
        return this.category;
     }

    @Override
    public String toString()
    /***
     * Returns the Category as a String.
     */
    {
        return category;
    }
}
