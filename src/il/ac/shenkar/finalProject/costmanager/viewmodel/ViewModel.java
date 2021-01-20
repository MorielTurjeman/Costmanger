package il.ac.shenkar.finalProject.costmanager.viewmodel;

import il.ac.shenkar.finalProject.costmanager.model.Category;
import il.ac.shenkar.finalProject.costmanager.model.CostItem;
import il.ac.shenkar.finalProject.costmanager.model.CostManagerException;
import il.ac.shenkar.finalProject.costmanager.model.IModel;
import il.ac.shenkar.finalProject.costmanager.view.IView;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ViewModel implements IViewModel {

    private IModel model;
    private IView view;
    private ExecutorService pool;

    public ViewModel() {
        pool = Executors.newFixedThreadPool(10);
    }

    @Override
    public void setView(IView view) {
        this.view = view;
    }

    @Override
    public void setModel(IModel model) {
        this.model = model;
    }

    @Override
    public void addCostItem(CostItem item) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.addCostItem(item);
                    view.showMessage("cost item was added successfully");
                    CostItem[] items = (CostItem[]) model.getCostItems().toArray();
                    view.showItems(items);
                } catch(CostManagerException e) {
                    view.showMessage(e.getMessage());
                }
            }
        });

    }

    @Override
    public void addCategory(Category newCategory) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.addCategory(newCategory);
                    view.showMessage(String.format("Category %s added succesfully", newCategory.getCategory()));
                    Vector <Category> items= model.getCategories();
                    view.showCategories(items);

                }
                catch (CostManagerException e)
                {
                    view.showMessage(e.getMessage());
                }

            }
        });
    }
}
