package il.ac.shenkar.finalProject.costmanager.view;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import il.ac.shenkar.finalProject.costmanager.model.Category;
import il.ac.shenkar.finalProject.costmanager.model.CostItem;


import il.ac.shenkar.finalProject.costmanager.model.CostManagerException;
import il.ac.shenkar.finalProject.costmanager.model.Currency;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;


public class PieChart extends JFrame
/**
 * Pie Chart to display to the user of his past purchases.
 * */
{
    private static final long serialVersionUID = 6294689542092367723L;
    ArrayList<Category> categoryList = new ArrayList<Category>();
    ArrayList<CostItem> items = new ArrayList<CostItem>();
    HashMap<Category, Double> pieChartData = new HashMap();

    public PieChart(String title) {
        super(title);

    }

    public void setCategoryList(ArrayList<Category> categoryList)
    /**
     * Set the category list. Receives an ArrayList<Category>, returns nothing.
     * */
    {
        this.categoryList = categoryList;
    }

    public void setItems(ArrayList<CostItem> items)
    /**
     * Set the items. Receives an ArrayList<CostItem>, returns nothing
     * */
    {
        this.items = items;
    }

    public  void setData(HashMap<Category, Double> pieChartData)
    /**
     * Set the data. Receives a HashMap<Category, Double>, returns nothing.
     * */
    {
        this.pieChartData= pieChartData;
    }

    public void showPieChart()
    /**
     * Display the pie chart.
     * */
    {
        // Create dataset   change
        PieDataset dataset = createDataset();

        // Create chart
        JFreeChart chart = ChartFactory.createPieChart(
                "Pie Chart",
                dataset,
                true,
                true,
                false);

        //Format Label
        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(
                " {0} - {1} : ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        ((PiePlot) chart.getPlot()).setLabelGenerator(labelGenerator);

        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    //change!!!!!!!!!!!!!!!!
    private PieDataset createDataset() {


        DefaultPieDataset dataset=new DefaultPieDataset();
        for (Category c : pieChartData.keySet()) {

            dataset.setValue(c.getCategory(), pieChartData.get(c));
        }

        return dataset;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //1
            PieChart example = new PieChart("hii");


            /* 2 -> data*/

            ArrayList<Category> categoryList = new ArrayList<Category>();
            ArrayList<CostItem> costItems = new ArrayList<CostItem>();

            Category a = new Category("a");
            Category b = new Category("b");
            categoryList.add(a);
            categoryList.add(b);
            CostItem item = new CostItem("nice1",90, Currency.USD,a);
            CostItem item2 = new CostItem("nice2",990,Currency.USD,b);
            CostItem item3 = new CostItem("nice carpet3",990,Currency.USD,a);
            CostItem item4 = new CostItem("nice carpet4",990,Currency.USD,b);
            costItems.add(item);
            costItems.add(item2);
            costItems.add(item3);
            costItems.add(item4);


            //3 -> set data
            example.setCategoryList(categoryList);
            example.setItems(costItems);

            //4 -> show pie chart
            example.showPieChart();
            example.setSize(800, 400);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }
}