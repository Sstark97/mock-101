import { Calendar } from './Calendar';
import { Printer } from './Printer';

export class PrintDate {
  private readonly calendar: Calendar;
  private readonly printer: Printer;

  constructor(calendar: Calendar, printer: Printer) {
    this.calendar = calendar;
    this.printer = printer;
  }

  printCurrentDate(): void {
    const line = this.calendar.today().toString();
    this.printer.printLine(line);
  }
}
