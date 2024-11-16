package random_number;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuessingNumberGameTest {

  @Test
  void should_show_the_player_win_at_the_first_time() {
    GuessingNumberGame player = new GuessingNumberGame(new DummieRandomNumberGenerator());
    int numberToGuess = 5;

    assertEquals("✅ Correct, you won!", player.guessNumber(numberToGuess));
  }

  @Test
  void should_show_that_number_is_greater_if_the_player_did_not_guess_right() {
    GuessingNumberGame player = new GuessingNumberGame(new StubRandomNumberGenerator(5));

    assertEquals("✚ The guess number is higher", player.guessNumber(4));
  }

  @Test
  void should_show_that_number_is_lower_if_the_player_did_not_guess_right() {
    GuessingNumberGame player = new GuessingNumberGame(new StubRandomNumberGenerator(5));

    assertEquals("- The guess number is lower", player.guessNumber(6));
  }

  @Test
  void should_show_that_the_player_lose_when_it_has_spent_three_intents() {
    GuessingNumberGame player = new GuessingNumberGame(new DummieRandomNumberGenerator());

    player.guessNumber(4);
    player.guessNumber(3);

    assertEquals("You lose", player.guessNumber(2));
  }
}