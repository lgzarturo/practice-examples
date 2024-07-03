package abstractPattern.button;

import abstractPattern.Button;

public class LinuxButton implements Button {
    @Override
    public void render() {
        System.out.println("Soy un botón de Linux");
    }

    @Override
    public void onClick() {
        System.out.println("Haz clic en un botón de Linux");
    }
}
