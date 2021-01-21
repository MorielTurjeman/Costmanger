package il.ac.shenkar.finalProject.costmanager.view;

import il.ac.shenkar.finalProject.costmanager.model.CostItem;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class CostItemTableModel extends AbstractTableModel {

    Vector <CostItem> costItems;
    String column[]={"ID","Date","Category", "Description", "Cost", "Currency"};

    public CostItemTableModel (Vector <CostItem> costItems){
        super();
        this.costItems= costItems;
    }

    @Override
    public int getRowCount() {
        return costItems.size();
    }

    @Override
    public int getColumnCount() {
       return column.length;

    }

    @Override
    public Object getValueAt(int row, int col) {
        CostItem costItem = costItems.get(row);
        switch (col) {
            case 0:
                return costItem.getId();
            case 1:
                return new SimpleDateFormat("yyyy-MM-dd").format(costItem.getDate());
            case 2:
                return costItem.getCategory();
            case 3:
                return costItem.getDescription();
            case 4:
                return costItem.getSum();
            case 5:
                return costItem.getCurrency();
            default:
                break;
        }
        return null;
    }

    @Override
    public String getColumnName(int index) {
        return column[index];
    }

}
