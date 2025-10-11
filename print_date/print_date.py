from print_date.calendar import Calendar
from print_date.printer import Printer


class PrintDate:
    
    def __init__(self, calendar: Calendar, printer: Printer):
        self._calendar = calendar
        self._printer = printer
    
    def print_current_date(self) -> None:
        line = str(self._calendar.today())
        self._printer.print_line(line)
