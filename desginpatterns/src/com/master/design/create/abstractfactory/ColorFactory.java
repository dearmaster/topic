package com.master.design.create.abstractfactory;

public class ColorFactory extends AbstractFactory {
    @Override
    Color getColor(String color) {
        Color c = null;
        if("red".equalsIgnoreCase(color)) {
            c = new Red();
        } else if ("green".equalsIgnoreCase(color)) {
            c = new Green();
        } else if ("blue".equalsIgnoreCase(color)) {
            c = new Blue();
        }
        return c;
    }

    @Override
    Shape getShape(String shape) {
        return null;
    }
}
