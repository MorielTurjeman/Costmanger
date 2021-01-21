package il.ac.shenkar.finalProject.costmanager.model;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class DerbyDBModel implements IModel {
    @Override
    public void addCostItem(CostItem item) throws CostManagerException {
        String jdbcURL= "jdbc:derby:costManager;create=true";
        try {
            Connection connection = DriverManager.getConnection(jdbcURL);
            String sql = String.format("Insert into costItem (description,sumPrice,currency,category, timestamp) values ( '%s', %f, '%s', '%s', '%s')",
                    item.getDescription(), item.getSum(), item.getCurrency().toString(), item.getCategory().getCategory(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getDate()));
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            if(rows>0) {
                System.out.println("row in  product created");
            }else{
                System.out.println("row in  product NOT created");
            }
//            String shotdownURL = "jdbc:derby:;shutdown=true";
//            DriverManager.getConnection(shotdownURL);
        } catch (SQLException throwables) {
            //if(throwables.getSQLState().equals("XJ015")){
            //    System.out.println("Derby DB shutdown normally");
            //}else {
            throwables.printStackTrace();
            //}
        }

    }

    @Override
    public void addCategory(Category newCategory) throws CostManagerException {
        String jdbcURL= "jdbc:derby:costManager;create=true";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL);

        String sql=String.format("Insert into categories (NAME) values ('%s')", newCategory.getCategory());
        Statement statement = connection.createStatement();
        int rows = statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new CostManagerException(throwables.getMessage());
        }

    }
    public Vector<Category> getCategories() throws CostManagerException {
        Vector<Category> categories =new Vector<>();
        String jdbcURL= "jdbc:derby:costManager;create=true";
        Connection connection= null;
        try {
            connection = DriverManager.getConnection(jdbcURL);

            String sql= "Select NAME from  Categories";
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(sql);
            while (set.next()){

                String name= set.getString("NAME");
                Category newCategory= new Category(name);
                categories.add(newCategory);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categories;

    }


    @Override
    public Vector<CostItem> getCostItems() throws CostManagerException {
        Vector<CostItem> costItems =new Vector<>();
        String jdbcURL= "jdbc:derby:costManager;create=true";
        try {
            Connection connection = DriverManager.getConnection(jdbcURL);
            String sql = "Select id,description,sumPrice,currency,category,timestamp from costItem";
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(sql);
            while (set.next())
            {
                int id = set.getInt("id");
                String description= set.getString("description");
                float sumPrice= set.getFloat("sumPrice");
                String currency= set.getString("currency");
                String category= set.getString("category");
                Date date = set.getTimestamp("timestamp");
                Currency c=Currency.ILS;
                if (currency.equals("ILS"))
                        c = Currency.ILS;
                else if (currency.equals("USD"))
                        c=Currency.USD;
                else if (currency.equals("EURO"))
                        c=Currency.EURO;
                else if(currency.equals("GBP"))
                        c=Currency.GBP;
                CostItem item=new CostItem(id, description, sumPrice, c, new Category(category), date);
                costItems.add(item);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            return costItems;
        }

    }

    @Override
    public void deleteCostItem(CostItem item) throws CostManagerException {
        String jdbcURL= "jdbc:derby:costManager;create=true";
        try {
            Connection connection = DriverManager.getConnection(jdbcURL);
            String sql ="DELETE FROM costItem WHERE id = "+item.getId();
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            if(rows>0) {
                System.out.println("row in  product removed");
            }else{
                System.out.println("row in  product NOT removed");
            }
//            String shotdownURL = "jdbc:derby:;shutdown=true";
//            DriverManager.getConnection(shotdownURL);
        } catch (SQLException throwables) {
            //if(throwables.getSQLState().equals("XJ015")){
            //    System.out.println("Derby DB shutdown normally");
            //}else {
            throwables.printStackTrace();
            //}
        }
    }



    public DerbyDBModel(){
        String jdbcURL= "jdbc:derby:costManager;create=true";
        try {
            Connection connection = DriverManager.getConnection(jdbcURL);
            String createTable = "CREATE TABLE costItem (id int not null,description varchar(255)," +
                "sumPrice double," +
                "currency varchar(255) not null," +
                "category varchar(255) not null)";
            Statement statement = connection.createStatement();
            statement.executeUpdate(createTable);


        } catch (SQLException throwables) {
            if(throwables.getSQLState().equals("X0Y32")) {
                System.out.println("Table costItem already exists!");
            }else{
            throwables.printStackTrace();
            }
        }

    }
    //...

}
