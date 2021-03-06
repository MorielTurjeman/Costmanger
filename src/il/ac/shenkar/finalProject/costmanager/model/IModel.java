package il.ac.shenkar.finalProject.costmanager.model;

import java.util.Vector;

public interface IModel {
    public void addCostItem(CostItem item) throws CostManagerException;
    public Vector<CostItem> getCostItems() throws CostManagerException;
    public Vector<CostItem> getCostItems(ReportFilters filters) throws CostManagerException;
    public void deleteCostItem(int id) throws CostManagerException;
    public  void  addCategory(Category newCategory) throws CostManagerException;
    public Vector<Category> getCategories() throws CostManagerException;

    //..
}
