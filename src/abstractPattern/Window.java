package abstractPattern;

public class Window {
    private final Button button;

    public Window(GUIFactory factory) {
        button = factory.createButton();
    }

    public void render() {
        button.render();
    }

    public void onClick() {
        button.onClick();
    }
}
