package random_number;

public class GuessingNumberGame {
  private final int generator;
  private int intents = 3;

  GuessingNumberGame(RandomNumberGenerator generator){
    this.generator = generator.randomNumber();
  }

  public String guessNumber(int guessedNumber) {
    spentIntent();
    if (isGameOver()) {
      return "You lose";
    }

    if (hasWin(guessedNumber)) {
      return  "✅ Correct, you won!";
    } else if (isTheNumberHigher(guessedNumber)) {
      return "✚ The guess number is higher";
    }
    return "- The guess number is lower";
  }

  private boolean isTheNumberHigher(int guessedNumber) {
    return generator > guessedNumber;
  }

  private boolean hasWin(int guessedNumber) {
    return guessedNumber == generator;
  }

  private boolean isGameOver() {
    return intents == 0;
  }

  private void spentIntent() {
    intents--;
  }
}