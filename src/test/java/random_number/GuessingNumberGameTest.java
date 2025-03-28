package random_number;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GuessingNumberGameTest {
    @Mock
    private RandomNumberGenerator randomNumberGenerator;
    private GuessingNumberGame player;
    final int numberToGuess = 5;

    @BeforeEach
    void setUp() {
        Mockito.when(randomNumberGenerator.randomNumber()).thenReturn(numberToGuess);
        player = new GuessingNumberGame(randomNumberGenerator);
    }

    @Test
    void should_show_the_player_win_at_the_first_time() {
        assertEquals("✅ Correct, you won!", player.guessNumber(numberToGuess));
    }

    @Test
    void should_show_that_number_is_greater_if_the_player_did_not_guess_right() {
        assertEquals("✚ The guess number is higher", player.guessNumber(4));
    }

    @Test
    void should_show_that_number_is_lower_if_the_player_did_not_guess_right() {
        assertEquals("- The guess number is lower", player.guessNumber(6));
    }

    @Test
    void should_show_that_the_player_lose_when_it_has_spent_three_intents() {
        player.guessNumber(4);
        player.guessNumber(3);

        assertEquals("You lose", player.guessNumber(2));
    }
}