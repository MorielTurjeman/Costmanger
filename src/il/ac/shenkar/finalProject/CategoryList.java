package il.ac.shenkar.finalProject;

import java.util.ArrayList;

public class CategoryList {

    private static ArrayList<String> categoryList = new ArrayList<String>();


    public void addCategory(String category){
        if(categoryList.contains(category)==false) {
            categoryList.add(category);
            System.out.println("category created");
        }else {
            System.out.println("category already exist");
        }
    }

    public boolean Contain(String category){
        return categoryList.contains(category);
    }
    public ArrayList<String> getCategoryList(){
        return categoryList;
    }


}
