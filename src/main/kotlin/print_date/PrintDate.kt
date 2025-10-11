package print_date

class PrintDate(
    private val calendar: Calendar,
    private val printer: Printer
) {
    fun printCurrentDate() {
        val line = calendar.today().toString()
        printer.printLine(line)
    }
}
