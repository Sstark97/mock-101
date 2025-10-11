package random_number

class GuessingNumberGame(generator: RandomNumberGenerator) {
    private val randomNumber: Int = generator.randomNumber()
}
