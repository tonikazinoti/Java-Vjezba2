package org.javalab;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;

class SensorTest {
    @Test
    void givenProperSerializedMessage_WhenConstruct_ThenDeserializeCorrectly() {
        var serializedMessage = """
                messageName: Potrošnja u zadnjih godinu dana
                dataType: uint16
                factor: 10
                minValue: -100
                maxValue: 100
                unit: m3""";

        var waterFlowMessage = new Sensor(serializedMessage);

        assertThat(waterFlowMessage.getSensorName()).isEqualTo("Potrošnja u zadnjih godinu dana");
        assertThat(waterFlowMessage.getDataType()).isEqualTo("uint16");
        assertThat(waterFlowMessage.getFactor()).isEqualTo(10);
        assertThat(waterFlowMessage.getMinValue()).isEqualTo(-100);
        assertThat(waterFlowMessage.getMaxValue()).isEqualTo(100);
        assertThat(waterFlowMessage.getUnit()).isEqualTo("m3");
    }
}