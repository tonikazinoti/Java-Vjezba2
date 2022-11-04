package org.javalab;

import com.google.common.base.Splitter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sensor {
    private String sensorName;
    private String dataType;
    private int factor;
    private int minValue;
    private int maxValue;
    private String unit;

    public Sensor(String serializedMessage) {
        deserializeMessage(serializedMessage);
    }

    private void deserializeMessage(String serializedMessage) {
        Map<String, String> message = Splitter.on("\n")
                .withKeyValueSeparator(": ")
                .split(serializedMessage);

        sensorName = message.get("messageName");
        dataType = message.get("dataType");
        factor = Integer.parseInt(message.get("factor"));
        minValue = Integer.parseInt(message.get("minValue"));
        maxValue = Integer.parseInt(message.get("maxValue"));
        unit = message.get("unit");
    }

    public String getMeasurementMessage() {
        return String.format("""
                        messageName: %s
                        dataType: %s
                        factor: %d
                        value: %d
                        unit: %s""",
                        sensorName, dataType, factor, generateRandomNumber(), unit);
    }

    private int generateRandomNumber() {
        return (int) (Math.floor(Math.random() * (maxValue - minValue)) + minValue);
    }
}
