package org.javalab;

import lombok.AllArgsConstructor;
import org.eclipse.paho.client.mqttv3.*;
import org.javalab.enums.QualityOfService;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
public class WaterFlowDevice {
    private final static String DEVICE_NAME = "Water Flow";
    private final int qualityOfService = QualityOfService.EXACTLY_ONCE.getValue();
    private final IMqttAsyncClient client;
    private final List<Sensor> sensors;

    public void start() {
        try {
            client.subscribe(DEVICE_NAME, qualityOfService);
        } catch (MqttException e) {
            e.printStackTrace();
        }

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this::publishMessages, 0, 3, TimeUnit.SECONDS);
    }

    private void publishMessages() {
        sensors.forEach(sensor -> {
            try {
                publishSingleMessage(sensor);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        });
    }

    private void publishSingleMessage(Sensor sensor) throws MqttException {
        System.out.println("Publishing message...");
        var message = sensor.getMeasurementMessage();
        MqttMessage mqttMessage = new MqttMessage(message.getBytes());
        mqttMessage.setQos(qualityOfService);
        client.publish(DEVICE_NAME, mqttMessage);
        System.out.println("Message published!");
    }
}
