package print_date;

import java.util.Date;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PrintDateTest {

  @Nested
  class WithSpyAndStrictMock {

    @Test
    void should_print_current_date() {
      Printer printer = Mockito.mock(Printer.class);
      Calendar calendar = Mockito.mock(Calendar.class);
      PrintDate printDate = new PrintDate(calendar, printer);

      Mockito.when(calendar.today()).thenReturn(new Date());

      printDate.printCurrentDate();

      Mockito.verify(calendar).today();
      Mockito.verify(printer).printLine(Mockito.anyString());
    }
  }
}