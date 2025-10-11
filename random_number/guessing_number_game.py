from random_number.random_number_generator import RandomNumberGenerator


class GuessingNumberGame:
    
    def __init__(self, generator: RandomNumberGenerator):
        self._random_number = generator.random_number()
