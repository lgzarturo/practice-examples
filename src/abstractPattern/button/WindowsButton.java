package abstractPattern.button;

import abstractPattern.Button;

public class WindowsButton implements Button {
    @Override
    public void render() {
        System.out.println("Soy un botón de Windows");
    }

    @Override
    public void onClick() {
        System.out.println("Haz clic en un botón de Windows");
    }
}
