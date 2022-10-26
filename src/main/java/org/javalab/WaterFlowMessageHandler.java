package org.javalab;

import org.eclipse.paho.client.mqttv3.*;
import org.javalab.enums.QualityOfService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WaterFlowMessageHandler {
    private final String topic = "Water Flow";
    private final int qualityOfService = QualityOfService.EXACTLY_ONCE.getValue();
    private final IMqttAsyncClient client;
    private final WaterFlowMessageGenerator waterFlowMessageGenerator;

    public WaterFlowMessageHandler(IMqttAsyncClient client, WaterFlowMessageGenerator waterFlowMessageGenerator) {
        this.client = client;
        this.waterFlowMessageGenerator = waterFlowMessageGenerator;
    }

    public void start() {
        try {
            client.subscribe("Water Flow", qualityOfService);
        } catch (MqttException e) {
            e.printStackTrace();
        }

        startPublishing();
    }

    private void startPublishing() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            try {
                publishMessage(waterFlowMessageGenerator.generateWaterTemperatureMessage());
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }, 0, 3000, TimeUnit.MILLISECONDS);
    }

    private void publishMessage(IWaterFlowMessage waterFlowMessage) throws MqttException {
        System.out.println("Publishing message: " + waterFlowMessage.getMessageName());
        MqttMessage mqttMessage = new MqttMessage(waterFlowMessage.toString().getBytes());
        mqttMessage.setQos(qualityOfService);
        client.publish(topic, mqttMessage);
        System.out.println("Message published");
    }
}
