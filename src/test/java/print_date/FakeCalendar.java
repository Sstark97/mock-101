package print_date;

import java.util.Date;

public class FakeCalendar implements Calendar {
  private Date currentDate;

  @Override
  public Date today() {
    return currentDate;
  }

  public void fillWith(Date date) {
    currentDate = date;
  }
}
