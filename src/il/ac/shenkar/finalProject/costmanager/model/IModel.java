package il.ac.shenkar.finalProject.costmanager.model;

import java.util.Vector;

public interface IModel {
    /**
     * Add a new CostItem to the database
     * @param item The CostItem to be added
     * @throws CostManagerException in case of an error
     */
    public void addCostItem(CostItem item) throws CostManagerException;

    /**
     *
     * @return Returns all CostItems in the database
     * @throws CostManagerException in case of an error
     */
    public Vector<CostItem> getCostItems() throws CostManagerException;

    /**
     * Get a filtered list of CostItems
     * @param filters a ReportFilters object
     * @return A list of all CostItems matching the filters
     * @throws CostManagerException in case of an error
     */
    public Vector<CostItem> getCostItems(ReportFilters filters) throws CostManagerException;

    /**
     * Delete a cost item
     * @param id The CostItem id
     * @throws CostManagerException in case of an error
     */
    public void deleteCostItem(int id) throws CostManagerException;

    /**
     * Create a new category in the database
     * @param newCategory the Category to be added
     * @throws CostManagerException in case of an error
     */
    public  void  addCategory(Category newCategory) throws CostManagerException;

    /**
     * Get a list of all categories
     * @return List of categories
     * @throws CostManagerException in case of an error
     */
    public Vector<Category> getCategories() throws CostManagerException;

    //..
}
