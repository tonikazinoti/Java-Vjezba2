package org.javalab;

import lombok.AllArgsConstructor;
import org.eclipse.paho.client.mqttv3.*;
import org.javalab.enums.QualityOfService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
public class MqttMessageHandler {
    private final static String TOPIC_NAME = "Water Flow";
    private final int qualityOfService = QualityOfService.EXACTLY_ONCE.getValue();
    private final IMqttAsyncClient client;
    private final WaterFlowMessageGenerator waterFlowMessageGenerator;

    public void start() {
        try {
            client.subscribe(TOPIC_NAME, qualityOfService);
        } catch (MqttException e) {
            e.printStackTrace();
        }

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this::publishMessages, 0, 3, TimeUnit.SECONDS);
    }

    private void publishMessages() {
        var messagesToPublish = waterFlowMessageGenerator.generateWaterFlowMessages();
        messagesToPublish.forEach(message -> {
            try {
                publishSingleMessage(message);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        });
    }

    private void publishSingleMessage(String waterFlowMessage) throws MqttException {
        System.out.println("Publishing message...");
        MqttMessage mqttMessage = new MqttMessage(waterFlowMessage.getBytes());
        mqttMessage.setQos(qualityOfService);
        client.publish(TOPIC_NAME, mqttMessage);
        System.out.println("Message published");
    }
}
