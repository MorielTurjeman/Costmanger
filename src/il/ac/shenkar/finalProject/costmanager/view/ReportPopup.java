package il.ac.shenkar.finalProject.costmanager.view;

import il.ac.shenkar.finalProject.costmanager.model.*;
import il.ac.shenkar.finalProject.costmanager.viewmodel.IViewModel;
import org.jfree.data.statistics.SimpleHistogramDataset;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;

public class ReportPopup {
    private IViewModel vm;
    private  JLabel from;
    private  JLabel to;
    private JFrame fr;
    private  JComboBox startMonth;
    private  JComboBox startDay;
    private  JComboBox startYear;
    private  JComboBox endMonth;
    private  JComboBox endDay;
    private  JComboBox endYear;
    private JLabel categoryLable;
    private JComboBox categoryList;
    private  JButton getReport;
    private  JButton getChartPie;
    private JPanel panel;




    public ReportPopup(){
        panel = new JPanel();
        from= new JLabel("From: ");
        to= new JLabel("To: ");
        getReport = new JButton("Detailed Report");
        getChartPie= new JButton("Pie Chart Report");




        fr = new JFrame("Reports");
        Month[] months = { Month.of(1), Month.of(2), Month.of(3), Month.of(4), Month.of(5), Month.of(6),  Month.of(7), Month.of(8), Month.of(9), Month.of(10), Month.of(11), Month.of(12)};
        startMonth = new JComboBox(months);
        Integer[] days = new Integer[31];
        for (int i = 1; i <=31; i++)
            days[i-1] = i;

        startDay = new JComboBox(days);
        endDay= new JComboBox(days);
        Integer[] years={2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022};
        startYear= new JComboBox(years);
        endYear=new JComboBox(years);
        endMonth= new JComboBox(months);


        getReport.setBackground(Color.decode("#05c880"));
        getChartPie.setBackground(Color.decode("#05c880"));
        categoryLable= new JLabel("Category: ");
        String[] categories={"All","SuperMarket", "Education", "Taxes", "Communication", "Transportation"};
        categoryList= new JComboBox(categories);



    }


    public ReportFilters returnReportFilters() throws ParseException {
        SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");
        Date from = dateParser.parse(String.format("%d-%2d-%d", startYear.getSelectedItem(), ((Month) startMonth.getSelectedItem()).getValue(), startDay.getSelectedItem()));
        Date to = dateParser.parse(String.format("%d-%2d-%d", endYear.getSelectedItem(), ((Month)endMonth.getSelectedItem()).getValue(), endDay.getSelectedItem()));
        Category cat = new Category(categoryList.getSelectedItem().toString());
        ReportFilters rf = new ReportFilters(from, to, cat);
        return rf;
    }



    public void showDialog()
    {
        JDialog dialog = new JDialog(fr, "Reports");


        dialog.add(panel);
        panel.add(from);
        panel.add(startMonth);
        panel.add(startDay);
        panel.add(startYear);
        panel.add(to);
        panel.add(endMonth);
        panel.add(endDay);
        panel.add(endYear);
        panel.add(categoryLable);
        panel.add(categoryList);
        panel.add(getReport);

        getReport.addActionListener(l -> dialog.setVisible(false));


        dialog.setModal(true);

        dialog.setSize(300,250);
        dialog.setVisible(true);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));





    }





}
