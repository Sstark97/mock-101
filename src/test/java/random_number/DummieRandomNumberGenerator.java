package random_number;

record DummieRandomNumberGenerator() implements RandomNumberGenerator {
  @Override
  public int randomNumber() {
    return 5;
  }
}