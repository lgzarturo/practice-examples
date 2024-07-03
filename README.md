# Patrones de diseño

A continuación se presentan los patrones de diseño y su implementación en Java.

## Patrones de creación

### Abstract Factory

El patrón Abstract Factory proporciona una interfaz para crear familias de objetos relacionados o dependientes sin especificar sus clases concretas.

[Ejemplo](src/Example01AbstractPattern.java): En una aplicación puede tener una ventana y dependiendo del sistema operativo, se puede tener una ventana diferente. En este caso, se puede tener una ventana de Windows y una ventana de MacOS. En la ventana se muestra un botón que tiene un texto diferente dependiendo del sistema operativo.