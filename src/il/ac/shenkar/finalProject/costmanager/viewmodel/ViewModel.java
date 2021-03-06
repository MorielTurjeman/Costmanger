package il.ac.shenkar.finalProject.costmanager.viewmodel;

import il.ac.shenkar.finalProject.costmanager.model.*;
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
                    Vector<CostItem> items = model.getCostItems();
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
                    view.showMessage(String.format("Category %s added successfully", newCategory.getCategory()));
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

    @Override
    public void getCategories() {

        pool.submit(new Runnable()
        {
            @Override
            public void run() {
                Vector<Category> res= null;
                try {
                    res = model.getCategories();
                } catch (CostManagerException e) {
                    view.showMessage(e.getMessage());
                }
                view.showCategories(res);
            }

        });
    }

    @Override
    public void getCostItems() {
        pool.submit((new Runnable() {
            @Override
            public void run() {
                Vector <CostItem> costItems= null;
                try {
                    costItems = model.getCostItems();
                    view.showItems(costItems);

                } catch (CostManagerException e) {
                    e.printStackTrace();
                    view.showMessage(e.getMessage());
                }

            }
        }));

    }

    @Override
    public void getCostItems(ReportFilters rf) {
        pool.submit((new Runnable() {
            @Override
            public void run() {
                Vector <CostItem> costItems= null;
                try {
                    costItems = model.getCostItems(rf);
                    view.showItems(costItems);
                } catch (CostManagerException e) {
                    e.printStackTrace();
                    view.showMessage(e.getMessage());
                }

            }
        }));

    }

    @Override
    public void deleteCostItem(int id) {
        pool.submit((new Runnable() {
            @Override
            public void run() {
                try {
                    model.deleteCostItem(id);

                    Vector<CostItem> costItems = model.getCostItems();
                    view.showItems(costItems);

                } catch (CostManagerException e) {
                    e.printStackTrace();
                    view.showMessage(e.getMessage());
                }
            }
        }));

    }

}


