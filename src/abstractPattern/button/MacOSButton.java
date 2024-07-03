package abstractPattern.button;

import abstractPattern.Button;

public class MacOSButton implements Button {
    @Override
    public void render() {
        System.out.println("Soy un botón de Mac");
    }

    @Override
    public void onClick() {
        System.out.println("Haz clic en un botón de Mac");
    }
}
