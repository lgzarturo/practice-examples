import abstractPattern.*;
import abstractPattern.factory.LinuxFactory;
import abstractPattern.factory.MacOSFactory;
import abstractPattern.factory.WindowFactory;

public class Example01AbstractPattern {
    public static void main(String[] args) {
        String[] operatingSystems = {"Windows", "Mac", "Linux"};
        String osName = operatingSystems[(int) (Math.random() * operatingSystems.length)];
        GUIFactory factory = switch (osName) {
            case "Windows" -> new WindowFactory();
            case "Mac" -> new MacOSFactory();
            default -> new LinuxFactory();
        };

        Window window = new Window(factory);
        window.render();
    }
}
