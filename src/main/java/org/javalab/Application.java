package org.javalab;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Application {
    private static DeviceConfig CONFIG;

    public static void run() {
        try {
            CONFIG = loadConfigurationFromFile();
            var client = connect();
            new WaterFlowDevice(client, CONFIG.getSensors()).start();
        } catch (MqttException | IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private static MqttAsyncClient connect() throws MqttException, IOException {
        var client = new MqttAsyncClient(
                CONFIG.getServerUri(), CONFIG.getClientId(), new MemoryPersistence());
        var clientCallback = new ClientCallback();
        client.setCallback(clientCallback);

        var mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(true);
        mqttConnectOptions.setUserName(CONFIG.getUsername());
        mqttConnectOptions.setPassword(CONFIG.getPassword().toCharArray());

        System.out.println("Connecting to broker: " + client.getServerURI());
        var token = client.connect(mqttConnectOptions);
        token.waitForCompletion();
        System.out.println("Connected");

        return client;
    }

    private static DeviceConfig loadConfigurationFromFile() throws IOException {
        var configLines = Files.readAllLines(Paths.get("config.json"));
        var configJson = String.join("", configLines);
        Gson gson = new Gson();
        return gson.fromJson(configJson, DeviceConfig.class);
    }
}
