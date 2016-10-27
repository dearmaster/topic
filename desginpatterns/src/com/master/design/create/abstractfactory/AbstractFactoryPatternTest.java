package com.master.design.create.abstractfactory;

public class AbstractFactoryPatternTest {

    public static void main(String[] args) {
        AbstractFactory shapeFactory = FactoryProducer.getFactory("shape");

        Shape circle = shapeFactory.getShape("circle");
        Shape square = shapeFactory.getShape("square");
        Shape rectangle = shapeFactory.getShape("rectangle");

        circle.draw();
        square.draw();
        rectangle.draw();


        AbstractFactory colorFactory = FactoryProducer.getFactory("color");

        Color red = colorFactory.getColor("red");
        Color green = colorFactory.getColor("green");
        Color blue = colorFactory.getColor("blue");

        red.fill();
        green.fill();
        blue.fill();
    }

}
