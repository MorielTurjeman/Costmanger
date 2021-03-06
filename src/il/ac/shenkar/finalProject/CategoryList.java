package il.ac.shenkar.finalProject;

import java.util.ArrayList;

public class CategoryList
/**
 * A list of categories that already exist.
 * */
{

    private static ArrayList<String> categoryList = new ArrayList<String>();


    public void addCategory(String category)
    /**
     * Add a category. Receives a String, returns nothing.
     * */
    {
        if(categoryList.contains(category)==false) {
            categoryList.add(category);
            System.out.println("category created");
        }else {
            System.out.println("category already exist");
        }
    }

    public boolean Contain(String category)
    /**
     * Checks if the category is already in the CategoryList. Receives a String, returns a boolean.
     * */
    {
        return categoryList.contains(category);
    }
    public ArrayList<String> getCategoryList()
    /**
     * Get the category list. Receives nothing, returns an ArrayList<String>.
     * */
    {
        return categoryList;
    }


}
