package org.javalab;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class ClientCallback implements MqttCallback {
    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("Connection has been lost");
        System.exit(0);
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) {
        System.out.println("\nMessage arrived from topic: " + topic + "\n");
        System.out.println(mqttMessage.toString() + "\n\n");
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println("Delivered");
    }
}
