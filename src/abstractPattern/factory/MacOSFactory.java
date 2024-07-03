package abstractPattern.factory;

import abstractPattern.Button;
import abstractPattern.GUIFactory;
import abstractPattern.button.MacOSButton;

public class MacOSFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacOSButton();
    }
}
