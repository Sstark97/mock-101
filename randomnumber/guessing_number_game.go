package randomnumber

type GuessingNumberGame struct {
	randomNumber int
}

func NewGuessingNumberGame(generator RandomNumberGenerator) *GuessingNumberGame {
	return &GuessingNumberGame{
		randomNumber: generator.RandomNumber(),
	}
}
