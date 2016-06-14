package org.coderthoughts.iotdemo;

import java.util.Map;

import org.coderthoughts.iotdemo.impl.Foo;
import org.coderthoughts.iotdemo.impl.FooException;

public interface BlinkingDevice {
    Map<String, String> blinkGreenLight();

    default Object foo() throws FooException {
        Foo foo = new Foo();
        System.out.println("Fooing: " + foo);
        return foo;
    }
}
