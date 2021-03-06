package il.ac.shenkar.finalProject.costmanager.viewmodel;

import il.ac.shenkar.finalProject.costmanager.model.Category;
import il.ac.shenkar.finalProject.costmanager.model.CostItem;
import il.ac.shenkar.finalProject.costmanager.model.IModel;
import il.ac.shenkar.finalProject.costmanager.model.ReportFilters;
import il.ac.shenkar.finalProject.costmanager.view.IView;

import java.security.PublicKey;
import java.util.Vector;

public interface IViewModel {

    /**
     * Sets thew corresponding to the ViewModel
     * @param view The View
     */
    public void setView(IView view);

    /**
     * Sets the model corresponding to the ViewModel
     * @param model The Model
     */
    public void setModel(IModel model);

    /**
     * Add a new CostItem
     * @param item the CostItem to be added
     */
    public void addCostItem(CostItem item);

    /**
     * Add a new Category
     * @param newCategory The Category to be added
     */
    public  void addCategory(Category newCategory);

    /**
     * Get the list of categories
     */
    public void getCategories();

    /**
     * Get all CostItems
     */
    public void getCostItems();

    /**
     * Get all CostItems by filter
     * @param rf The filters for the request
     */
    public void getCostItems(ReportFilters rf);

    /**
     * Delete a cost item
     * @param id the CostItem id of the item to be deleted
     */
    public void deleteCostItem(int id);



}
