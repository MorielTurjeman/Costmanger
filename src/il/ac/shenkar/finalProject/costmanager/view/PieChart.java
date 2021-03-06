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
    HashMap<Category, Double> pieChartData = new HashMap();

    /**
     * PieChart dialog class
     * @param title Chart title
     */
    public PieChart(String title) {
        super(title);
    }

    /**
     * Set the data of the chart
     * @param pieChartData a HashMap which links between category and sum for that category
     */
    public  void setData(HashMap<Category, Double> pieChartData) {this.pieChartData= pieChartData;}

    /**
     * Make the chart visible in the dialog
     */
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
                " {0} - {1} : ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        ((PiePlot) chart.getPlot()).setLabelGenerator(labelGenerator);

        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    /**
     * Build a private dataset for the chart
     * @return return a PieDataset based on the hashtable
     */
    private PieDataset createDataset() {


        DefaultPieDataset dataset=new DefaultPieDataset();
        for (Category c : pieChartData.keySet()) {

            dataset.setValue(c.getCategory(), pieChartData.get(c));
        }

        return dataset;
    }
}