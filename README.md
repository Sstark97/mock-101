# Mock 101: El Arte del Testing

## Introducción
Este repositorio contiene los ejercicios a realizar para aprender a utilizar los diferentes dobles 
de test. 

Está estructurado de la siguiente manera:
- En el paquete random_number se encuentra el ejercicio para practicar dummies y stubs.
- En el paquete print_date se encuentra el ejercicio para practicar spies, mocks estrictos y fakes.
- En la rama **main** tendremos el setup inicial para realizar los ejercicios en **Java**.
- En la rama **random_number_solution** se encuentra la solución al ejercicio de random_number hecha por nosotros **(Java)**.
- En la rama **print_date_solution** se encuentra la solución al ejercicio de print_date hecha por nosotros **(Java)**.
- En la rama **solutions_with_mockito** se encuentran las soluciones a los ejercicios de random_number y print_date utilizando Mockito **(Java)**.

> **Nota:** Las soluciones a los ejercicios están disponibles únicamente en Java. Los templates en otros lenguajes están pensados para que practiques implementando las soluciones por tu cuenta.

## Templates en Otros Lenguajes

Este repositorio incluye implementaciones del template inicial en varios lenguajes de programación. Cada rama contiene la estructura base del proyecto con los tests configurados para que puedas practicar:

- **Python**: [Rama python](https://github.com/Sstark97/mock-101/tree/python) - Usando pytest como framework de testing
- **TypeScript**: [Rama typescript](https://github.com/Sstark97/mock-101/tree/typescript) - Usando Jest y ts-jest para testing
- **C#**: [Rama csharp](https://github.com/Sstark97/mock-101/tree/csharp) - Usando xUnit como framework de testing
- **Go**: [Rama go](https://github.com/Sstark97/mock-101/tree/go) - Usando el package testing estándar de Go
- **Kotlin**: [Rama kotlin](https://github.com/Sstark97/mock-101/tree/kotlin) - Usando JUnit 5 con Maven

## Ejercicio 1: Random Number Kata
Queremos crear un pequeño juego. El juego consiste en que un jugador intente adivinar un número aleatorio.
El jugador tendrá tres intentos para adivinar el número. Si el número es adivinado correctamente, el jugador gana, si no, el jugador pierde.

Si el jugador no adivina el número, el juego debe notificar al usuario si el número es mayor o menor.

Esta kata fue tomada de [Codurance](https://www.codurance.com/es/katas/numero-aleatorio-kata).
### Reglas de Negocio
- El usuario comienza a jugar, el juego genera un número aleatorio que no debe cambiar hasta que el juego termine.
- Si el usuario adivina el número, el jugador gana.
- Si el usuario no adivina el número, el sistema debe notificar al usuario si el número es mayor o menor.
- Si el usuario no adivina el número en tres intentos, perderá.

## Ejercicio 2: Print Date
Print date es un kata desarrollado por [Codium](https://github.com/CodiumTeam/print-date) para enseñar Dobles de prueba durante el entrenamiento 
de Trabajar con código heredado.

La idea es muy simple, cómo probar un método que imprime la fecha actual.

### Reglas de Negocio
Ser capaz de probar la función printCurrentDate sin cambiar la firma del método.