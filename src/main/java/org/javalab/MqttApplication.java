package org.javalab;

import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.UUID;

public class MqttApplication {
    private final String clientId = UUID.randomUUID().toString();
    private final String serverUri = "tcp://192.168.5.16:1883";
    private final String username = "admin";
    private final char[] password = "12345678".toCharArray();

    public void run() {
        try {
            var client = connect();
            new WaterFlowMessageHandler(client, new WaterFlowMessageGeneratorImpl()).start();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private MqttAsyncClient connect() throws MqttException {
        var client = new MqttAsyncClient(serverUri, clientId, new MemoryPersistence());
        var clientCallback = new ClientCallback();
        client.setCallback(clientCallback);

        var mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(true);
        mqttConnectOptions.setUserName(username);
        mqttConnectOptions.setPassword(password);

        System.out.println("Connecting to broker: " + serverUri);

        var token = client.connect(mqttConnectOptions);
        token.waitForCompletion();

        System.out.println("Connected");

        return client;
    }
}
