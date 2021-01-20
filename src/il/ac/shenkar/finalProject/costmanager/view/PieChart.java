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


public class PieChart extends JFrame {
    private static final long serialVersionUID = 6294689542092367723L;
    ArrayList<Category> categoryList = new ArrayList<Category>();
    ArrayList<CostItem> items = new ArrayList<CostItem>();

    public PieChart(String title) {
        super(title);

    }

    public void setCategoryList(ArrayList<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public void setItems(ArrayList<CostItem> items) {
        this.items = items;
    }

    public void showPieChart(){
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
                "Marks {0} : ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        ((PiePlot) chart.getPlot()).setLabelGenerator(labelGenerator);

        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    //change!!!!!!!!!!!!!!!!
    private PieDataset createDataset() {
        Map<String, Double> map = new HashMap<String, Double>();
        for(Category catgory : categoryList){
            double i=0;
            String s = catgory.getCategory();
            for(CostItem item: items){
                if(s.equals(item.getCategory().getCategory())){
                    //i++;
                    i=i+item.getSum();
                }
            }
            map.put(s,i);
        }

        DefaultPieDataset dataset=new DefaultPieDataset();
        for (String c : map.keySet()) {
            System.out.println("key: " + c + " value: " + map.get(c));
            dataset.setValue(c, map.get(c));
        }
        /*
        dataset.setValue("80-100", 120);
        dataset.setValue("60-79", 80);
        dataset.setValue("40-59", 20);
        dataset.setValue("20-39", 7);
        dataset.setValue("0-19", 3);
         */
        return dataset;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //1
            PieChart example = new PieChart("hii");


            /* 2 -> data*/

            ArrayList<Category> categoryList = new ArrayList<Category>();
            ArrayList<CostItem> costItems = new ArrayList<CostItem>();
            try {
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
            } catch (CostManagerException e) {
                e.printStackTrace();
            }

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