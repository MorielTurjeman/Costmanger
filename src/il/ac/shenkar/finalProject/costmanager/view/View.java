package il.ac.shenkar.finalProject.costmanager.view;

import il.ac.shenkar.finalProject.costmanager.model.*;
import il.ac.shenkar.finalProject.costmanager.model.Currency;
import il.ac.shenkar.finalProject.costmanager.viewmodel.IViewModel;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.time.Month;
import java.util.*;

public class View implements IView {

    private IViewModel vm;
    private ApplicationUI ui;

    @Override
    public void setViewModel(IViewModel vm) {
        this.vm = vm;
    }

    @Override
    public void showMessage(String text) {
        ui.showMessage(text);
    }

    @Override
    public void showItems(Vector<CostItem> costItems) {
        ui.showItems(costItems);
    }

    @Override
    public void showCategories(Vector<Category> categoryVec) {
        ui.updateCategoryList(categoryVec);
    }


    public View() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View.this.ui = new ApplicationUI();
                View.this.ui.start();
            }
        });
    }

    public class ApplicationUI //implements IView
    {

        private JFrame frame;
        private JPanel panelTop;
        private JPanel panelMiddle;
        private JPanel panelBottom;
        private JPanel panelMain;
        private JPanel panelMessage;
        private JTextField tfItemSum;
        private JComboBox tfItemCurrency;
        private JTextField tfItemDescription;
        private JTextField tfMessage;
        private JButton btAddCostItem;
        private JScrollPane scrollPane;
        private JTextArea textArea;
        private JLabel lbItemSum;
        private JLabel lbItemCurrency;
        private JLabel lbItemDescription;
        private JLabel lbMessage;
        private JLabel categoryLable;
        private JComboBox categoryList;

        private  JButton getReport;
        private JTable table;
        private  JButton deleteButton;
        private  JLabel purchaseDate;
        private  JComboBox month;
        private  JComboBox day;
        private  JComboBox year;
        private  JButton pieChartRep;
        private  JTextField sum;
        private  JLabel sumLable;





        //

        private JTextField mtf;
        private JButton mbtn;


        public ApplicationUI() {
            //creating the window
            frame = new JFrame("CostManager");
            //creating the four panels
            panelMain = new JPanel();
            panelBottom = new JPanel();
            panelTop = new JPanel();
            panelMiddle = new JPanel();
            panelMessage = new JPanel();
            //creating the main ui components
            tfItemSum = new JTextField(8);
            String[] CurrencyList={"ILS", "USD", "EURO"};
            tfItemCurrency = new JComboBox(CurrencyList);
            tfItemDescription = new JTextField(20);
            btAddCostItem = new JButton("Add Cost Item");
            btAddCostItem.setBackground(Color.decode("#05c880"));
            textArea = new JTextArea();
            scrollPane = new JScrollPane(textArea);
            lbItemCurrency = new JLabel("Item Currency:");
            lbItemDescription = new JLabel("Item Description:");
            lbItemSum = new JLabel("Item Sum:");
            //creating the messages ui components
            lbMessage = new JLabel("Message: ");
            tfMessage = new JTextField(30);
            categoryLable= new JLabel("Category: ");
            purchaseDate= new JLabel("Purchase Date: ");

            categoryList= new JComboBox();

            Month[] months = { Month.of(1), Month.of(2), Month.of(3), Month.of(4), Month.of(5), Month.of(6),  Month.of(7), Month.of(8), Month.of(9), Month.of(10), Month.of(11), Month.of(12)};
            month = new JComboBox(months);
            Integer[] days = new Integer[31];
            for (int i = 1; i <=31; i++)
                days[i-1] = i;

            day = new JComboBox(days);
            Integer[] years={2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022};
            year= new JComboBox(years);
            getReport= new JButton("Get Report");
            getReport.setBackground(Color.decode("#05c880"));
            getReport.addActionListener(l -> this.showReportDialog());
            pieChartRep= new JButton("Pie Chart Report");
            pieChartRep.addActionListener(l->this.showPieChartRepo());
            sum = new JTextField();
            sumLable= new JLabel("SUM:");




            //middle
            mtf = new JTextField(8);
            mbtn  = new JButton("Add Category");
            mbtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    String newCategory= mtf.getText();
                    if (newCategory.isEmpty()){
                        ui.showMessage("Category name empty");
                    }
                    else
                    vm.addCategory(new Category(newCategory));

                }
            });

            //table
            table =new JTable();
            table.setRowSelectionAllowed(true);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            //table.getSelectionModel().addListSelectionListener(s );
            table.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
                        table.clearSelection();
                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });
            deleteButton= new JButton("Delete Selected");
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    int row=table.getSelectedRow();
                    int id= (Integer)table.getModel().getValueAt(row, 0);
                    vm.deleteCostItem(id);

                }
            });

            vm.getCategories();
            vm.getCostItems();


        }

        public void start() {

            //adding the components to the top panel
            panelTop.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));
            panelTop.add(categoryLable);
            panelTop.add(categoryList);
            panelTop.add(lbItemSum);
            panelTop.add(tfItemSum);
            panelTop.add(lbItemDescription);
            panelTop.add(tfItemDescription);
            panelTop.add(lbItemCurrency);
            panelTop.add(tfItemCurrency);
            panelTop.add(purchaseDate);
            panelTop.add(month);
            panelTop.add(day);
            panelTop.add(year);
            panelTop.add(btAddCostItem);

            panelTop.add(Box.createHorizontalStrut(10));



            //middle
            panelTop.add(mtf);
            panelTop.add(mbtn);
            panelTop.setPreferredSize(new Dimension(3, 70));

            //setting BorderLayout as the LayoutManager for panelMain
            panelMain.setLayout(new BorderLayout());

            //setting GridLayout 1x1 as the LayoutManager for panelBottom
            panelBottom.setLayout(new GridLayout(1, 1));

            //adding the components to the bottom panel
            scrollPane.setViewportView(table);

            panelBottom.add(scrollPane);
            table.getSelectionModel().addListSelectionListener(x -> this.tableSelectionChanged());



            //adding the components to the messages panel
            panelMessage.add(lbMessage);
            panelMessage.add(tfMessage);
            panelMessage.add(getReport);
            panelMessage.add(deleteButton);
            panelMessage.add(pieChartRep);
            panelMessage.add(sumLable);
            panelMessage.add(sum);


            deleteButton.setVisible(false);



            //setting a different color for the panel message
            panelMessage.setBackground(Color.decode("#89cff0"));

            //setting the window layout manager
            frame.setLayout(new BorderLayout());

            //adding the two panels to the main panel
            //panelMain.add(panelTop);
            panelMain.add(panelBottom, BorderLayout.CENTER);

            //adding the main panel to the window
            frame.add(panelMain, BorderLayout.CENTER);

            //adding top panel to the window
            frame.add(panelTop, BorderLayout.NORTH);

            //adding middle panel to the window
            frame.add(panelMiddle,BorderLayout.WEST);

            //adding the message panel to the window
            frame.add(panelMessage, BorderLayout.SOUTH);

            //handling window closing
            frame.addWindowListener(new WindowAdapter() {
                /**
                 * Invoked when a window is in the process of being closed.
                 * The close operation can be overridden at this point.
                 *
                 * @param e
                 */
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });

            //handling cost item adding button click
            btAddCostItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String description = tfItemDescription.getText();
                        if(description==null || description.length()==0) {
                            throw new CostManagerException("description cannot be empty");
                        }
                        Month selectedMonth= (Month) month.getSelectedItem();
                        Integer selectedDay= (Integer) day.getSelectedItem();
                        Integer selectedYear= (Integer) year.getSelectedItem();
                        Date selectedDate= new Date(selectedYear - 1900, selectedMonth.getValue() - 1, selectedDay);
                        Category selectedCategory= (Category) categoryList.getSelectedItem();
                        double sum = Double.parseDouble(tfItemSum.getText());
                        String currencyStr = tfItemCurrency.getSelectedItem().toString();
                        Currency currency = null;
                        switch (currencyStr) {
                            case "EURO":
                                currency = Currency.EURO;
                                break;
                            case "ILS":
                                currency = Currency.ILS;
                                break;
                            //....
                            default:
                                currency = Currency.USD;

                        }
                        CostItem item = new CostItem(description, sum, currency, selectedCategory, selectedDate);
                        vm.addCostItem(item);


                    } catch (NumberFormatException ex) {
                        View.this.showMessage("problem with entered sum... "+ex.getMessage());
                    } catch(CostManagerException ex){
                        View.this.showMessage("problem with entered data... problem with description... "+ex.getMessage());
                    }
                }
            });

            //displaying the window
            frame.setSize(1600, 600);
            frame.setVisible(true);
        }

        public void showMessage(String text) {
            if (SwingUtilities.isEventDispatchThread()) {
                tfMessage.setText(text);
            } else {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        tfMessage.setText(text);
                    }
                });

            }

        }

        public  void showPieChartRepo(){
            TableModel data= table.getModel();
            HashMap<Category, Double> categorySums = new HashMap<>();
            if (data == null){
                View.this.showMessage("Table has no data");
            }

            else{

                for (int i=0; i<data.getRowCount(); i++){
                    Category cat = (Category) data.getValueAt(i, 2);
                    if (categorySums.containsKey(cat)){
                            double currSum=categorySums.get(cat);
                            currSum+=(Double) data.getValueAt(i, 4);
                            categorySums.put(cat, currSum);
                    }
                    else{
                        categorySums.put(cat, (Double) data.getValueAt(i, 4));
                    }


                }
            }


            PieChart pieChart= new PieChart("Pie Chart Report:");
            pieChart.setData(categorySums);
            pieChart.showPieChart();
            pieChart.setSize(800, 400);
            pieChart.setLocationRelativeTo(null);
            pieChart.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            pieChart.setVisible(true);
        }

        //Show the costItems at the table
        public void showItems(Vector <CostItem> items) {
            CostItemTableModel costItemTable= new CostItemTableModel(items);
            table.setModel(costItemTable);
            double sumItem=0;
            for (int i=0; i<items.size(); i++){
                sumItem += items.get(i).getSum();
            }
            sum.setText(String.format("%.2f₪", sumItem));

        }

        public void updateCategoryList(Vector<Category> categories)
        {
            ComboBoxModel<Category> categoriesModel = new DefaultComboBoxModel<>(categories);
            categoryList.setModel(categoriesModel);

        }

        public void tableSelectionChanged() {
            if (table.getSelectedRow() >= 0)
            {
                deleteButton.setVisible(true);
            }
            else
            {
                deleteButton.setVisible(false);
            }
        }

        public void showReportDialog()
        {
            ReportPopup rp = new ReportPopup();
            rp.showDialog();
            try {
                ReportFilters rf = rp.returnReportFilters();
                if (rf != null)
                    vm.getCostItems(rf);
            } catch (Exception e) {
                showMessage(e.getMessage());
            }

        }

    }

}