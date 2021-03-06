/*
	Sapir Ezra	313546194
	Coral Rubilar	316392877
	Moriel Turjeman	308354968
*/

package il.ac.shenkar.finalProject.costmanager.model;
import java.util.Date;


public class ReportFilters
/**
 * The ReportFilters class details the filters required for the main report.
 */
{
    Date from;
    Date to;
    Category category;

    /**
     * Class Constructor
     * @param from Start date
     * @param to End date
     * @param category Selected Category
     */
    public ReportFilters(Date from, Date to, Category category)
    /***
     * Initialize filters. Receives two Dates and one Category.
     */
    {
        this.from = from;
        this.to = to;
        this.category = category;
    }


    public Category getCategory()
    /***
     * Get the category. Receives nothing, returns a Category.
     */
    {
        return category;
    }

    public Date getFrom()
    /***
     * Get the "from-date". Receives nothing, returns a Date.
     */
    {
        return from;
    }

    public Date getTo()
    /***
     * Get the "to-date". Receives nothing, returns a Date.
     */
    {
        return to;
    }
}
