package il.ac.shenkar.finalProject.costmanager.model;
import java.util.Date;

/**
 * The ReportFilters class details the filters required for the main report
 */
public class ReportFilters {
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
    {
        this.from = from;
        this.to = to;
        this.category = category;
    }


    public Category getCategory() {
        return category;
    }

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }
}
