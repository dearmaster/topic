package com.master.design.create.abstractfactory;

public class FactoryProducer {

    public static AbstractFactory getFactory(String factory) {
        if("shape".equalsIgnoreCase(factory))
            return new ShapeFactory();
        else if ("color".equalsIgnoreCase(factory))
            return new ColorFactory();
        else
            return null;
    }

}
