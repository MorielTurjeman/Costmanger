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

    public CostItem(String description, double sum,Currency currency, Category category, Date date) {


        setDescription(description);
        setCurrency(currency);
        setSum(sum);
        id = -1;
        if(category.getCategory() != "") {
            this.category = category;
        }
        setDate(date);
    }

    public CostItem(int id,String description, double sum,Currency currency, Category category, Date date){
        this(description, sum, currency, category, date);
        this.id= id;
    }

    public CostItem(String description, double sum,Currency currency, Category category){
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

    public int getId(){
        return this.id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public Currency getCurrency() {
       return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Category getCategory(){
        return this.category;
    }

    public void setDate(Date date){this.date= date; }

    public  Date getDate() {return this.date;}

}

