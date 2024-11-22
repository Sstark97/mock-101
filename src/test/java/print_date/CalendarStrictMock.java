package print_date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarStrictMock implements Calendar {
    private Date currentDate;
    private int calls;

    @Override
    public Date today() {
        try {
            fillDateWithAny();
            calls++;
            return currentDate;
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private void fillDateWithAny() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        currentDate = formatter.parse("2023-10-10");
    }

    public void verify() {
        if (currentDate == null) {
            throw new IllegalArgumentException("Method today() was not called");
        }
        if (calls != 1) {
            throw new IllegalStateException("Method today() was not called");
        }
    }
}
