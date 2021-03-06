package il.ac.shenkar.finalProject.costmanager.model;


import java.text.SimpleDateFormat;
import java.util.Date;

public class CostItem /**
 This class represents an item that the user inserted, of something which he spent money on.
 */
{

    private int id;
    private String description;
    private double sum;
    private Currency currency;
    private Category category;
    private Date date;

    public CostItem(String description, double sum,Currency currency, Category category, Date date)
    /**
     * Initializes the CostItem. Receives a String, double, Currency, Category, Date. Returns nothing.
     * */
    {
//        this.description = description;
//        this.sum = sum;
//        this.currency = currency;

        setDescription(description);
        setCurrency(currency);
        setSum(sum);
        id = -1;
        if(category.getCategory() != "") {
            this.category = category;
        }
        setDate(date);
    }

    public CostItem(int id,String description, double sum,Currency currency, Category category, Date date)
    /***
     * Initializes the CostItem. Receives int, String, double, Currency, Category, Date. Returns nothing.
     */
    {
        this(description, sum, currency, category, date);
        this.id= id;
    }

    public CostItem(String description, double sum,Currency currency, Category category)
    /***
     * Initializes the CostItem. Receives a String, double, Currency, Category. Returns nothing.
     */
    {
        this(description, sum, currency, category, new Date());

    }

    @Override
    public String toString()
    /**
     * Transforms the data into a string.
     * */
    {
        return "CostItem{" +
                " id=" + id +
                ", date=" +new SimpleDateFormat("yyyy-MM-dd").format(date) +
                ", category="+category.getCategory()+
                "description='" + description + '\'' +
                ", sum=" + sum +
                ", currency=" + currency +
                 " }";

    }

    public int getId()
    /***
     * Get the Id of the item. Receives nothing, returns an int.
     */
    {
        return this.id;
    }

    public String getDescription()
    /***
     * Get the description of the item. Receives nothing, returns a String.
     */
    {
        return description;
    }

    public void setDescription(String description)
    /***
     * Set the description of the item. Receives a String, returns nothing.
     */
    {
        this.description = description;
    }

    public double getSum()
    /***
     * Get the sum of the item. Receives nothing, returns a double.
     */
    {
        return sum;
    }

    public void setSum(double sum)
    /***
     * Set the sum of the item. Receives a double, returns nothing.
     */
    {
        this.sum = sum;
    }

    public Currency getCurrency()
    /***
     * Get the currency of the item. Recieves nothing, returns a Currency.
     */
    {
       return currency;
    }

    public void setCurrency(Currency currency)
    /***
     * Set the currency of the item. Receives a Currency, returns nothing.
     */
    {
        this.currency = currency;
    }

    public Category getCategory()
    /***
     * Get the category of the item. Receives nothing, returns a Category.
     */
    {
        return this.category;
    }

    public void setDate(Date date)
    /***
     * Set the date of the item. Receives a Date, returns nothing.
     */
    {
        this.date= date;
    }

    public  Date getDate()
    /***
     * Get the date of the item. Receives nothing, returns a Date.
     */
    {
        return this.date;
    }

}

