package random_number;

record StubRandomNumberGenerator(int number) implements RandomNumberGenerator {

  @Override
  public int randomNumber() {
    return number;
  }
}