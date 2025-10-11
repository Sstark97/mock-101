import { RandomNumberGenerator } from './RandomNumberGenerator';

export class GuessingNumberGame {
  private readonly randomNumber: number;

  constructor(generator: RandomNumberGenerator) {
    this.randomNumber = generator.randomNumber();
  }
}
