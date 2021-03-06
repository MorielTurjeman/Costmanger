/*
	Sapir Ezra	313546194
	Coral Rubilar	316392877
	Moriel Turjeman	308354968
*/

package il.ac.shenkar.finalProject.costmanager;

import il.ac.shenkar.finalProject.costmanager.model.CostManagerException;
import il.ac.shenkar.finalProject.costmanager.model.DerbyDBModel;
import il.ac.shenkar.finalProject.costmanager.model.IModel;
import il.ac.shenkar.finalProject.costmanager.view.IView;
import il.ac.shenkar.finalProject.costmanager.view.View;
import il.ac.shenkar.finalProject.costmanager.viewmodel.IViewModel;
import il.ac.shenkar.finalProject.costmanager.viewmodel.ViewModel;

public class Application
/**
 * The purpose of this class is to create the different components of the application and then connect them to one another.
 * */
{
    public static void main(String args[]) {
        //creating the application components
        IModel model = new DerbyDBModel();
        IView view = new View();
        IViewModel vm = new ViewModel();

        //connecting the components with each other
        view.setViewModel(vm);
        vm.setModel(model);
        vm.setView(view);


    }
}
