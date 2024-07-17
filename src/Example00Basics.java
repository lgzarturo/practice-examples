import java.math.BigDecimal;
import java.math.BigInteger;

public class Example00Basics {
    public static void main(String[] args) {
        // Declarar multiples variables en una sola línea
        int a = 1, b = 2, c = 3; // Esto se puede, pero no es recomendable, debido a que puede ser confuso

        primitiveTypes();
        wrapperClasses();
        bigNumbers();
        correctNotation();
        exampleOfIndentation();
        stringUsages();
    }

    /**
     * Tipos de datos primitivos
     */
    private static void primitiveTypes() {
        // Tipos de datos primitivos
        // Se utilizan para almacenar valores simples, sin métodos ni propiedades
        // Se almacenan en la memoria de forma eficiente y se pueden usar para realizar operaciones matemáticas
        // Estos tipos de datos no son objetos, por lo que no se pueden llamar métodos en ellos
        byte byteValue = 127;                   // El rango de valores de un byte es de -128 a 127 [1 byte]
        short shortValue = 32767;               // El rango de valores de un short es de -32,768 a 32,767 [2 bytes] (-32K a 32K)
        int integer = 42;                       // El rango de valores de un int es de -2,147,483,648 a 2,147,483,647 [4 bytes] (-2B a 2B)
        long longValue = 1234567890;            // El rango de valores de un long es de -9,223,372,036,854,775,808 a 9,223,372,036,854,775,807 [8 bytes] (-9Q a 9Q)
        long viewsCount = 3_123_000_000L;       // Se puede usar guiones bajos para hacer que los números sean más legibles
        // Se debe agregar una 'F' al final para indicar que es un número flotante
        float floatValue = 3.14F;               // El rango de valores de un float es de 3.4e−038 a 3.4e+038 [4 bytes]
        double floatingPoint = 3.14;            // El rango de valores de un double es de 1.7e−308 a 1.7e+308 [8 bytes]
        boolean bool = true;                    // Un booleano puede ser verdadero o falso [1 bit]
        char character = 'A';                   // Un char almacena un solo carácter Unicode [2 bytes]
        String string = "Hello, World!";        // Un String es una secuencia de caracteres, el tamaño no es fijo y se puede modificar en tiempo de ejecución

        System.out.println("byte: " + byteValue);
        System.out.println("short: " + shortValue);
        System.out.println("int: " + integer);
        System.out.println("long: " + longValue);
        System.out.println("long[viewsCount]: " + viewsCount);
        System.out.println("float: " + floatValue);
        System.out.println("double: " + floatingPoint);
        System.out.println("boolean: " + bool);
        System.out.println("char: " + character);
        System.out.println("String: " + string);
    }

    /**
     * Clases envolventes, objetos que envuelven tipos de datos primitivos
     */
    private static void wrapperClasses() {
        // Clases envolventes, se utilizan para convertir tipos de datos primitivos en objetos
        // Se pueden llamar métodos en estos objetos
        // Se basan en las clases primitivas, pero tienen métodos y propiedades adicionales
        // Son eficaces para trabajar con colecciones de datos y en situaciones en las que se requiere un objeto
        Byte byteObject = 127;
        Short shortObject = 32767;
        Integer integerObject = 42;
        Long longObject = 1234567890L;
        Float floatObject = 3.14F;
        Double floatingPointObject = 3.14;
        Boolean boolObject = true;
        Character characterObject = 'A';

        System.out.println("Byte: " + byteObject);
        System.out.println("Short: " + shortObject);
        System.out.println("Integer: " + integerObject);
        System.out.println("Long: " + longObject);
        System.out.println("Float: " + floatObject);
        System.out.println("Double: " + floatingPointObject);
        System.out.println("Boolean: " + boolObject);
        System.out.println("Character: " + characterObject);
    }

    /**
     * Clases de números grandes
     */
    private static void bigNumbers() {
        // Clases de números grandes, se utilizan para almacenar números grandes
        // Se pueden usar para realizar operaciones matemáticas con números grandes
        // Para representa montos de dinero es mejor usar BigDecimal debido a la precisión
        // BigInteger se usa para números enteros grandes por ejemplo para cálculos matemáticos que requieren precisión
        BigInteger bigInteger = new BigInteger("123456789012345678901234567890");
        BigDecimal bigDecimal = new BigDecimal("1234567890.12345678901234567890");

        System.out.println("BigInteger: " + bigInteger);
        System.out.println("BigDecimal: " + bigDecimal);
    }

    /**
     * Notación correcta de variables en Java
     */
    private static void correctNotation() {
        // En java se debe usar la notación camelCase para nombrar variables, los nombres de las variables deben ser descriptivos
        // Es importante mantener un estilo de codificación consistente en el código
        // Se busca que el código sea fácil de leer y entender, esa es la razón por la que se siguen las convenciones de codificación
        BigDecimal totalAmount = new BigDecimal("1234567890.12345678901234567890");
        BigDecimal totalAmountInDollars = totalAmount; // Esto es más descriptivo que totalAmountUSD

        // Se recomienda el uso de 'is' o 'has' como prefijo para variables booleanas
        Boolean isCorrect = true; // Esto es más descriptivo que correct
        Boolean hasErrorInData = false; // Esto es más descriptivo que error
        String fullName = "Alice Smith"; // Esto es más descriptivo que name
        String phoneNumber = "1234567890"; // Esto es más descriptivo que phone
    }

    /**
     * La indentación y el espaciado también son importantes para hacer que el código sea más legible
     */
    private static void exampleOfIndentation() {
        // La indentación y el espaciado también son importantes para hacer que el código sea más legible
        // Se recomienda usar 4 espacios para la indentación
        for (int i = 0; i < 10; i++) {
            // Evitar bucles anidados, ya que pueden hacer que el código sea difícil de leer y el costo computacional sea mayor
            // Otra forma de evitar bucles anidados es usar métodos y funciones
            // La complejidad solo debe aumentar si es necesario, de lo contrario, se debe mantener el código simple
            for (int j = 0; j < 10; j++) {
                System.out.println("i: " + i + ", j: " + j);
                if (i == 5) {
                    System.out.println("i es igual a 5");
                    switch (j) {
                        case 0:
                            System.out.println("j es igual a 0");
                            if (j == 0) {
                                System.out.println("j es igual a 0");
                                while (j < 5) {
                                    // No se recomienda un nivel de indentación mayor a 3, ya que dificulta la lectura
                                    System.out.println("j: " + j);
                                    j++;
                                }
                            }
                            break;
                        case 1:
                            System.out.println("j es igual a 1");
                            break;
                        default:
                            System.out.println("j es diferente de 0 y 1");
                            break;
                    }
                }
            }
        }

        // mal ejemplo de indentación, no es fácil de leer, lo que dificulta la comprensión del código
        for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 10; j++) {
        System.out.println("i: " + i + ", j: " + j);
        if (i == 5) System.out.println("i es igual a 5");
        }
        }

        // otro ejemplo de mal indentación, no es fácil de leer, lo que dificulta la comprensión del código
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                System.out.println("i: " + i + ", j: " + j);
    }

    /**
     * Uso de cadenas de texto, recomendaciones para hacer que el código sea más legible
     */
    private static void stringUsages() {
        // Evitar líneas de código muy largas, se recomienda un máximo de 80 caracteres por línea
        // Si una línea de código es demasiado larga, se puede dividir en varias líneas
        // Esto hace que el código sea más legible y fácil de entender
        String longText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";

        // Se recomienda dividir la línea de código en varias líneas
        String longTextFormatted = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
            "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";

        // Se puede usar texto de varias líneas para hacer que el código sea más legible
        // Esto aplicará saltos de línea y espacios en blanco tal como se escriben en el código
        String longTextFormatted2 = """
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, 
            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
        """;

        // Para limitar el texto a un ancho de 80 caracteres y evitar el uso de saltos de línea,
        // Se puede usar el carácter de escape '\' para dividir la línea de código
        String text80CharsLimit = """
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt \
            ut labore et dolore magna aliqua.Lorem ipsum dolor sit amet, consectetur adipiscing elit, \
            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.""";

        System.out.println(longText);
        System.out.println(longTextFormatted);
        System.out.println(longTextFormatted2);
        System.out.println(text80CharsLimit);

        // Se recomienda usar la interpolación de cadenas en lugar de la concatenación de cadenas
        // La interpolación de cadenas hace que el código sea más legible y fácil de entender
        String name = "Alice";
        String message = "Hello, " + name + "!"; // Concatenación de cadenas [forma tradicional], se recomienda evitarla
        // La interpolación de cadenas se puede hacer de dos formas
        String messageFormatted = String.format("Hello, %s!", name);    // Forma tradicional
        String messageInterpolated = "Hello, %s!".formatted(name);      // Forma moderna

        System.out.println(message);
        System.out.println(messageFormatted);
        System.out.println(messageInterpolated);
    }
}
