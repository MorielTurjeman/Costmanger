package il.ac.shenkar.finalProject.costmanager.viewmodel;

import il.ac.shenkar.finalProject.costmanager.model.Category;
import il.ac.shenkar.finalProject.costmanager.model.CostItem;
import il.ac.shenkar.finalProject.costmanager.model.IModel;
import il.ac.shenkar.finalProject.costmanager.view.IView;

public interface IViewModel {

    public void setView(IView view);
    public void setModel(IModel model);
    public void addCostItem(CostItem item);
    public  void addCategory(Category newCategory);



}
