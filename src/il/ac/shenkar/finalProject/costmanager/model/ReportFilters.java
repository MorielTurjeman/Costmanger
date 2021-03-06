package il.ac.shenkar.finalProject.costmanager.model;
import java.util.Date;

public class ReportFilters {
    Date from;
    Date to;
    Category category;

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
