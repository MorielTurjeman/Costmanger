package il.ac.shenkar.finalProject.costmanager.model;

import java.util.Vector;

public interface IModel {
    public void addCostItem(CostItem item) throws CostManagerException;
    public Vector<CostItem> getCostItems() throws CostManagerException;
    public void deleteCostItem(CostItem item) throws CostManagerException;
    //..
}
