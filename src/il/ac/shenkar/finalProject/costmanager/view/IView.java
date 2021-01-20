package il.ac.shenkar.finalProject.costmanager.view;

import il.ac.shenkar.finalProject.costmanager.model.Category;
import il.ac.shenkar.finalProject.costmanager.model.CostItem;
import il.ac.shenkar.finalProject.costmanager.viewmodel.IViewModel;

import java.util.Vector;

public interface IView {

    //public void displayPieChart(Map map);
    public void setViewModel(IViewModel vm);
    public void showMessage(String text);
    public void showItems(CostItem[] vec);
    public void showCategories(Vector<Category> categoryVec);
    //..
}
