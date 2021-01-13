package il.ac.shenkar.finalProject.costmanager.model;


import java.util.ArrayList;
import java.util.Arrays;

public class Category {
     private String category;

    public Category(String category) throws CostManagerException {
         this.category= category;
    }


     public String getCategory(){
        return this.category;
     }



}
