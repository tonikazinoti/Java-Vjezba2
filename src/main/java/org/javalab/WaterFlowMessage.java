package org.javalab;

import com.google.common.base.Splitter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.javalab.enums.WaterFlowMessageType;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WaterFlowMessage {
    private String messageName;
    private String dataType;
    private int factor;
    private int value;
    private String unit;

    public WaterFlowMessage(String serializedMessage) {
        deserializeMessage(serializedMessage);
    }

    private void deserializeMessage(String serializedMessage) {
        Map<String, String> message = Splitter.on("\n")
                .withKeyValueSeparator(": ")
                .split(serializedMessage);

        messageName = message.get("messageName");
        dataType = message.get("dataType");
        factor = Integer.parseInt(message.get("factor"));
        value = Integer.parseInt(message.get("value"));
        unit = message.get("unit");
    }

    @Override
    public String toString() {
        return String.format("""
                        messageName: %s
                        dataType: %s
                        factor: %d
                        value: %d
                        unit: %s""",
                        messageName, dataType, factor, value, unit);
    }
}
