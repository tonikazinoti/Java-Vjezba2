package org.javalab;

import org.javalab.enums.QualityOfService;

public class Main {
    public static void main(String[] args) {
        var app = new MqttApplication();
        app.run();
    }
}