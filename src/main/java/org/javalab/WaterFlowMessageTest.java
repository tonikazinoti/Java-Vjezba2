package org.javalab;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;

class WaterFlowMessageTest {
    @Test
    void givenProperSerializedMessage_WhenConstruct_ThenDeserializeCorrectly() {
        var serializedMessage = """
                messageName: Potrošnja u zadnjih godinu dana
                dataType: uint16
                factor: 10
                value: 30482
                unit: m3""";
        var waterFlowMessage = new WaterFlowMessage(serializedMessage);

        assertThat(waterFlowMessage.getMessageName()).isEqualTo("Potrošnja u zadnjih godinu dana");
        assertThat(waterFlowMessage.getDataType()).isEqualTo("uint16");
        assertThat(waterFlowMessage.getFactor()).isEqualTo(10);
        assertThat(waterFlowMessage.getValue()).isEqualTo(30482);
        assertThat(waterFlowMessage.getUnit()).isEqualTo("m3");
    }

    @Test
    void givenInitialValues_WhenToString_ThenCorrectString() {
        WaterFlowMessage waterFlowMessage = new WaterFlowMessage(
                "Potrošnja u zadnjih godinu dana",
                "uint16",
                10,
                30482,
                "m3"
                );

        String expected = """
                messageName: Potrošnja u zadnjih godinu dana
                dataType: uint16
                factor: 10
                value: 30482
                unit: m3""";

        String actual = waterFlowMessage.toString();

        assertThat(actual).isEqualTo(expected);
    }
}