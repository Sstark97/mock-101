package printdate

type PrintDate struct {
	calendar Calendar
	printer  Printer
}

func NewPrintDate(calendar Calendar, printer Printer) *PrintDate {
	return &PrintDate{
		calendar: calendar,
		printer:  printer,
	}
}

func (p *PrintDate) PrintCurrentDate() {
	line := p.calendar.Today().String()
	p.printer.PrintLine(line)
}
