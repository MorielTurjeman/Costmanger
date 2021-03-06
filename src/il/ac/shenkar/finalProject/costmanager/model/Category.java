package il.ac.shenkar.finalProject.costmanager.model;


import java.util.ArrayList;
import java.util.Arrays;

public class Category /**
 This class represents a category of the item that the user inserts (for example it could be rent, phone bill, etc).
 */{

     private String category;

    public Category(String category)  {
         this.category= category;
    }


    public String getCategory(){
        return this.category;
     }

    @Override
    public String toString() {
        return category;
    }
}
