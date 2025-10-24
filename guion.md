# Mock 101

## Test Desiderata (Un buen test)
El término “Test Desiderata” hace referencia a un conjunto de principios o características deseables para las pruebas de software. Aunque no es un estándar formal, el concepto se ha popularizado en el mundo del Software Craftsmanship, con el objetivo de garantizar que las pruebas sean efectivas, mantenibles y confiables. Hay muchos principios pero nosotros vamos a destacar 3:

- Determinismo: Los dobles de prueba ayudan a eliminar la aleatoriedad y la dependencia de factores externos (como bases de datos, APIs o servicios externos). Esto hace que los tests sean predecibles y confiables, ya que siempre devolverán los mismos valores esperados.
- Aislado (Micro): El uso de dobles de prueba ayuda a que los tests sean más pequeños y específicos, evitando dependencias externas y asegurando que cada unidad de código se pruebe en aislamiento.
- Información Específica y de Comportamiento

### Test Sociales y Solitarios
- Un test social es aquel en el que se prueba el comportamiento de un artefacto, en el que se usan sus dependencias reales, con todas las implicaciones que puede tener esto (Pueden ser más costosos, pueden petar por alguna de esas dependencias, etc). Es un test más a alto nivel
- Un test solitatario es aquel en el que se prueba el comportamiento de un artefacto, en el que se usan dobles de test de sus dependencias, por lo que tendra un entorno más aislado, seguro y será menos costoso

> Esto no quiere decir que todos los test deban ser solitarios, en ocaciones te interesará probar con sus depencias reales para ver el flujo completo de las reglas de negocio

### Tipos de Pruebas
Para apoyarme voy a utilizar este pequeño esquema que saqué del libro "La artesanía del código limpio de Robert C. Martin", que representa los tipos de dobles como unan estructura jerárquica, como si estuvieramos hablando de herencia entre comillas. Para mí fue lo que termino de hacerme click en la cabeza, lo que termino por hacer encajar las piezas del puzzle.
Esto que vemos es la terminología de Meszaros, apareció por primera vez en el libro "xUnit Test Patterns: Refactoring Test Code" de Gerard Meszaros.
Creo que es importante conocer un poco cada tipo de doble, porque en las librerías de test generalmente están un poco difusos, y muchas veces es eso lo que nos lleva a confusión.

### LoginDialog
Para apoyarnos y explicar los diferentes tipos de doble,veremos un pequeño ejercicio de ejemplo. Tenemos está interfaz Authenticator y la clase LoginDialog, esa interfaz representa el contrato que debe seguir el colaborador que pasemos por constructor a la clase. El objetivo es testear el comportamiento del dialogo de Login y utilizar los distintos dobles para hacer nuestras pruebas. Al final de la sesión tendrán un repo con el boilerplate, como los diferentes ejemplos de código que veremos en la sesión.

### Dummies
Empecemos con el tipo de doble más sencillo, el dummy. Un dummy es un tipo de doble de prueba que se utiliza cuando necesitas pasar un objeto a un componente bajo prueba pero el comportamiento del doble no es relevante para la prueba en cuestión.

Vamos a utilizar el ejemplo de un modal de Login para todos los ejemplos de código. En este caso tenemos una interfaz Authenticator, que define el contrato que debemos cumplir para poder autenticarnos. Sin embargo nosotros queremos testear que se cierre el modal cuando le damos a cancelar, por lo que un dummy nos viene como anillo al dedo. Creamos un dummy que implemente la interfaz, para que podamos inyectarlo en nuestro Login, que deuvelva cualquier cosa, nos da igual.

No es relevante que el usuario esté autenticado para testear el comportamiento del modal.


### Stubs
El siguiente paso es el stub. Un stub es un tipo de doble de prueba que, a diferencia de un dummy, proporciona respuestas predefinidas a las llamadas que se le hacen durante la prueba, tienen estado o memoria.

Esto nos permite especificar el resultado deseado sin necesidad de interactuar con el sistema real de autenticación.
Por ejemplo si quiesierámos probar que una autenticación falle, teniendo en cuenta la `interfaz Authenticator`, podríamos hacer lo siguiente:
Nos creamos un stub al que le pasemos por constructor el resultado que nosotros queramos que tenga nuestro autenticador, y lo devolvemos en el método authenticate.
De está forma en los test podemos crear un Stub que falle la autenticación de manera programática, y sin depender de la implementación real de nuestro artefacto.

### Spies
Continuemos con el siguiente tipo de doble, el Spy. Un Spy se utiliza para verificar si ciertas acciones han sido realizadas en el objeto, como la invocación de métodos con parámetros específicos, sin interrumpir el flujo natural de la prueba. Además de proporcionar respuestas predefinidas como un Stub, también registra información sobre cómo se utiliza durante las pruebas, por lo que podemos hacerle preguntas (Datos con los que ha sido llamado, cuántas veces ha sido llamado, etc). Este tipo de artefactos vienen muy bien cuando tenemos métodos que no devuelven nada, pero a los que queremos hacer seguimiento.

Volvamos de nuevo al código, aquí vemos un ejemplo de implementación de un Spy. Como vemos no difiere mucho del Stub que teníamos previamente, la diferencia es que ahora guardamos los parámetros con los que se llama al método authenticate, y registramos el número de veces que se llama.

De esa forma en está prueba, nos aseguramos de que `LoginDialog` invoca correctamente a `Authenticator`, comprobando de que sólo se llame una vez a `authenticate` y con los argumentos que se han pasado en el `submit`.

Un spy puede ser tan simple cómo un único booleano que se establece cuando se llama a un método en particular, o algo más complejo.

Son útiles para garantizar que el algoritmo está probandose de manera correcta, sobretodo en código en el que no tenemos tanto control como el código legacy.
Por lo que podemos añadir cierta seguridad sobre ese código en el que a priori no tenemos control.

### Mocks
Llegamos a el último tipo de doble de la parte izquierda del diagrama que os enseñaba al inicio, los Mocks estrictos. Este es el doble que suele dar nombre a todos los demás, ya que normalmente cuando hablamos de dobles hablamos de mocks. Un Mock estricto es aquel que no solo simula el comportamiento de un objeto, sino que también verifica que se realicen llamadas esperadas a sus métodos con parámetros específicos, y falla la prueba si se realiza alguna llamada inesperada o si las llamadas esperadas no ocurren en el orden definido. Dicho de otro modo, las aserciones de la prueba se realizan en el mock.

Como podemos ver en está implementación, tenemos varias varias validaciones para verificar que todo vaya como queremos, por lo que si que tenemos cierta lógica de control en este doble.

Por último tenemos el método verify, que será el que valide si nuestro test ha ido bien, lanzando una excepción en caso de no se haya podido hacer la llamada de autenticación.

Así podemos garantizar que el inicio de sesión ha tenido éxito, y que las expectativas del mock se han cumplido.

Así es como se vería el test, como vemos es el Mock el que verifica si el test pasa o no.
El mock estricto nos puede ayudar a diseñar si partimos de un código nuevo, ya que nos permite definir cierta lógica de negocio y nos guia en el diseño de nuestro código de producción. Sin embargo debemos tener en cuenta de que nuestros tests pueden llegar a ser frágiles en el caso de que alguna regla de negocio cambie, por ejemplo imaginemos que la autenticación ya no sea por usuario y contraseña, sino que sea através de la cuenta de Google, nuestros tests se romperían. Por lo que debemos usarlos con cuidado.

#### Fakes
Por último nos pasamos a la rama derecha del diagrama, el siguiente tipo de doble es el Fake Object. Es un objeto que simula el comportamiento real del artefacto, a diferencia de los stubs o mocks, que generalmente solo simulan respuestas a llamadas específicas, un fake implementa algunas reglas de negocio de manera rudimentaria o simplificada. Nos permiten mantener estados internos, permitiendo que se use en pruebas más complejas o integradas.

En este ejemplo vemos que hemos implementado una cierta lógica, y es que el usuario tiene que ser igual a "user" y la contraseña igual a "good password". Como vemos esto podría simular perfectamente el comportamiento del artefacto Authenticator real, pero una lógica mucho más simple.

Los fakes son especialmente útiles en entornos de prueba donde interactuar con el verdadero sistema o componente sería impracticable, costoso o lento, proporcionando una simulación lo suficientemente buena para permitir una variedad de pruebas.

El problema con los Fake es que, a medida que la aplicación crezca, siempre habrá más condiciones que comprobar. Como consecuencia los Fake tienen a crecer por cada nueva condición, si añadimos un campo email, o un campo teléfono, ya tendríamos que validar más cosas. Pudiendo ser tan grandes y complejos que necesiten sus propias pruebas.

### Dobles de test con liberías de terceros
Todo esto está muy bien, hemos visto los diferentes tipos de dobles de test (dummy, stub, spy, mock y fak object), pero generalmente en nuestro día a día no vamos a picarnos nuestro propio doble, normalmente haremos uso de librerías de tests que nos faciliten el desarrollo. Eso sí, recomiendo utilizarlas cuando tengamos lo más claro posible los diferentes tipos de doble, ya que como dije al principio, normalmente en las librerías los conceptos están un poco mezclados.

El ejemplo que les quiero enseñar es con Mockito, una librería de Java. Como vemos definir un doble es tán simple como usar el método `mock` y pasarle la clase que queremos que simule. Esto nos va a permitir definir la respuesta que queramos que tenga el mock ante ciertos parámetros de entrada, através del método `when`.
También podemos verificar cuantas veces y con que parámetros se ha llamado al método authenticate, através del método `verify`, como podríamos hacer con un Spy.
Como vemos en Mockito está todo un poco unido, pero es una herramienta super potente que nos va a permitir hacer dobles de una manera sencilla.