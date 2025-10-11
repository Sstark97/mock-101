from abc import ABC, abstractmethod
from datetime import datetime


class Calendar(ABC):
    
    @abstractmethod
    def today(self) -> datetime:
        pass
