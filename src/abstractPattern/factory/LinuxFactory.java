package abstractPattern.factory;

import abstractPattern.Button;
import abstractPattern.GUIFactory;
import abstractPattern.button.LinuxButton;

public class LinuxFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new LinuxButton();
    }
}
