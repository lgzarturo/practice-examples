import java.util.Scanner;

public class Example01 {
    public static void main(String[] args) {
        System.out.println("What's your name?");
        Scanner scanner = new Scanner(System.in);
        var name = scanner.nextLine();
        if (name == null || name.isBlank()) {
            System.err.println("No name provided");
            System.exit(1);
        }
        System.out.println(Greetings.greet(name));
    }
}
