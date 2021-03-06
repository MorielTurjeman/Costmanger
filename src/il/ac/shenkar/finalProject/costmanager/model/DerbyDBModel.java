package il.ac.shenkar.finalProject.costmanager.model;

import java.sql.*;
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
        } catch (SQLException e) {
            throw new CostManagerException(e.getMessage());
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
        connection.close();

        } catch (SQLException e) {
            throw new CostManagerException(e.getMessage());
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
            connection.close();
        } catch (SQLException e) {
            throw new CostManagerException(e.getMessage());
        }
        return categories;

    }

    public Vector<CostItem> getCostItemsFromDb(ResultSet set) throws SQLException {
        Vector<CostItem> costItems =new Vector<>();

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

        return costItems;
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
            Vector<CostItem> items = getCostItemsFromDb(set);
            connection.close();
            return items;
        } catch (SQLException e) {
            throw new CostManagerException(e.getMessage());
        }


    }

    @Override
    public Vector<CostItem> getCostItems(ReportFilters filters) throws CostManagerException {
        String jdbcURL= "jdbc:derby:costManager;create=true";
        try {
            Connection connection = DriverManager.getConnection(jdbcURL);
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            String sql = String.format("Select id,description,sumPrice,currency,category,timestamp from costItem where timestamp between '%s 00:00:00' and '%s 00:00:00'",
                    simpleDateFormat.format(filters.getFrom()),
                    simpleDateFormat.format(filters.getTo()));

            if (!filters.getCategory().getCategory().equals("All"))
                sql = sql.concat(String.format( " and category = '%s'", filters.getCategory()));

            Statement statement = connection.createStatement();

            ResultSet set = statement.executeQuery(sql);
            Vector<CostItem> items = getCostItemsFromDb(set);
            connection.close();
            return items;
        } catch (SQLException e) {
            throw new CostManagerException(e.getMessage());
        }


    }

    @Override
    public void deleteCostItem(int id) throws CostManagerException {
        String jdbcURL= "jdbc:derby:costManager;create=true";
        try {
            Connection connection = DriverManager.getConnection(jdbcURL);
            String sql ="DELETE FROM costItem WHERE id = "+ id;
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            if(rows>0) {
                System.out.println("row in  product removed");
            }else{
                System.out.println("row in  product NOT removed");
            }

        } catch (SQLException e) {
            throw new CostManagerException(e.getMessage());
        }
    }



    public DerbyDBModel() {
        String jdbcURL= "jdbc:derby:costManager;create=true";
        try {
            Connection connection = DriverManager.getConnection(jdbcURL);
            String createTable = "CREATE TABLE costItem (id int not null,description varchar(255)," +
                "sumPrice double," +
                "currency varchar(255) not null," +
                "category varchar(255) not null)";
            Statement statement = connection.createStatement();
            statement.executeUpdate(createTable);
            connection.close();


        } catch (SQLException e) {
            if(e.getSQLState().equals("X0Y32")) {
                System.out.println("Table costItem already exists!");
            }else{
                System.out.println("Could not connect to database, exiting application");
            }
        }

    }
    //...

}
