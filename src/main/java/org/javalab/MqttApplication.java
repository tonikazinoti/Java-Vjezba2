package org.javalab;

import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MqttApplication {
    public static void run() {
        try {
            var client = connect();
            new MqttMessageHandler(client, new WaterFlowMessageGeneratorImpl()).start();
        } catch (MqttException | IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private static MqttAsyncClient connect() throws MqttException, IOException {
        var config = loadConfigurationFromFile();
        var client = new MqttAsyncClient(
                config.getString("serverUri"), config.getString("clientId"), new MemoryPersistence());
        var clientCallback = new ClientCallback();
        client.setCallback(clientCallback);

        var mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(true);
        mqttConnectOptions.setUserName(config.getString("username"));
        mqttConnectOptions.setPassword(config.getString("password").toCharArray());

        System.out.println("Connecting to broker: " + client.getServerURI());

        var token = client.connect(mqttConnectOptions);
        token.waitForCompletion();

        System.out.println("Connected");

        return client;
    }

    private static JSONObject loadConfigurationFromFile() throws IOException {
        var configLines = Files.readAllLines(Paths.get("config.json"));
        var configJson = String.join("", configLines);
        return new JSONObject(configJson);
    }
}
