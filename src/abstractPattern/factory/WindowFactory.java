package abstractPattern.factory;

import abstractPattern.Button;
import abstractPattern.GUIFactory;
import abstractPattern.button.WindowsButton;

public class WindowFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}
