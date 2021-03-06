package il.ac.shenkar.finalProject.costmanager.view;

import il.ac.shenkar.finalProject.costmanager.model.Category;
import il.ac.shenkar.finalProject.costmanager.model.CostItem;
import il.ac.shenkar.finalProject.costmanager.viewmodel.IViewModel;

import java.util.Vector;

public interface IView {

    //public void displayPieChart(Map map);

    /**
     * Sets the ViewModel of the View Object
     * @param vm The ViewModel
     */
    public void setViewModel(IViewModel vm);

    /**
     * Show a message to the user in case of an error
     * @param text The message to be shown
     */
    public void showMessage(String text);

    /**
     * Show the list of CostItems in the report
     * @param costItems
     */
    public void showItems(Vector<CostItem> costItems);

    /**
     * Show the category list
     * @param categoryVec CategoryList
     */
    public void showCategories(Vector<Category> categoryVec);
    //..
}
