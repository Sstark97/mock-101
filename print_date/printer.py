from abc import ABC, abstractmethod


class Printer(ABC):
    
    @abstractmethod
    def print_line(self, line: str) -> None:
        pass
