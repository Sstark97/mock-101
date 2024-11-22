package print_date;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PrintDateTest {

  @Nested
  class WithSpyAndStrictMock {

    @Test
    void should_check_printer_is_called() {
      PrinterSpy printerSpy = new PrinterSpy();
      PrintDate printDate = new PrintDate(new CalendarDummy(), printerSpy);

      printDate.printCurrentDate();

      assertTrue(printerSpy.isCalled());
    }

    @Test
    void should_use_calendar_to_get_date() {
      CalendarStrictMock calendarStrictMock = new CalendarStrictMock();
      PrintDate printDate = new PrintDate(calendarStrictMock, new PrinterDummy());

      printDate.printCurrentDate();

      calendarStrictMock.verify();
    }

    @Test
    void should_print_current_date() {
      PrinterSpy printerSpy = new PrinterSpy();
      CalendarStrictMock calendarStrictMock = new CalendarStrictMock();
      PrintDate printDate = new PrintDate(calendarStrictMock, printerSpy);

      printDate.printCurrentDate();

      calendarStrictMock.verify();
      assertTrue(printerSpy.isCalled());
    }
  }

  @Nested
  class WithFakeObjects {

    @Test
    void should_use_calendar_to_get_date() {
      FakeCalendar fakeCalendar = new FakeCalendar();
      fakeCalendar.fillWith(anyDate());
      PrinterSpy printerSpy = new PrinterSpy();
      PrintDate printDate = new PrintDate(fakeCalendar, printerSpy);

      printDate.printCurrentDate();

      assertTrue(printerSpy.haveBeenCalledWith(anyDate().toString()));
    }

    private Date anyDate() {
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      return formatter.parse("2023-10-10", new ParsePosition(0));
    }
  }
}