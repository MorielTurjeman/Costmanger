package il.ac.shenkar.finalProject.costmanager.model;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.Vector;


public class CostItemDemo {
    public static void main(String args[])  {
        Category c = null;
            c = new Category("hi");

        // Category d = new Category("hi2");
        //CostItem item = new CostItem("nice carpet",990,Currency.USD,c);
        //CostItem item2 = new CostItem("nice carpet",990,Currency.USD,c);
        //CostItem item3 = new CostItem("nice carpet",990,Currency.USD,c);
        //CostItem item4 = new CostItem("nice carpet",990,Currency.USD,c);
     /*   System.out.println(item);
        System.out.println(item2);
        System.out.println(item3);
        System.out.println(item4);

      */
        /*DerbyDBModel ddb = new DerbyDBModel();
        try {
            //ddb.addCostItem(item);
            ddb.addCostItem(item2);
            ddb.addCostItem(item3);
            ddb.addCostItem(item4);

            ddb.deleteCostItem(item);
//            ddb.deleteCostItem(item);

            Vector<CostItem> items = ddb.getCostItems();
            for (CostItem i: items
                 ) {
                System.out.println(i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
*/

    }
}
