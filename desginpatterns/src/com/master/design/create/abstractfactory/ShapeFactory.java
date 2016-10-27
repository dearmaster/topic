package com.master.design.create.abstractfactory;

public class ShapeFactory extends AbstractFactory {
    @Override
    Color getColor(String color) {
        return null;
    }

    @Override
    Shape getShape(String shape) {
        Shape s = null;
        if("circle".equalsIgnoreCase(shape)) {
            s = new Circle();
        } else if("square".equalsIgnoreCase(shape)) {
            s = new Square();
        } else if("rectangle".equalsIgnoreCase(shape)) {
            s = new Rectangle();
        }
        return s;
    }
}
