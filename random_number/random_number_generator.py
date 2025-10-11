from abc import ABC, abstractmethod


class RandomNumberGenerator(ABC):
    
    @abstractmethod
    def random_number(self) -> int:
        pass
