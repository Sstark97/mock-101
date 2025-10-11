namespace Mock101.RandomNumber;

public class GuessingNumberGame
{
    private readonly int _randomNumber;

    public GuessingNumberGame(IRandomNumberGenerator generator)
    {
        _randomNumber = generator.RandomNumber();
    }
}
