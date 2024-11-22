package print_date;

public class PrinterSpy implements Printer {
    private int calls;
    private String currentLine;

    @Override
    public void printLine(String line) {
        calls ++;
        currentLine = line;
    }

    public boolean isCalled() {
        return calls == 1;
    }

  public boolean haveBeenCalledWith(String line) {
    return currentLine.equals(line);
  }
}
