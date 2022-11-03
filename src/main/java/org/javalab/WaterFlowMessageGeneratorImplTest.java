package org.javalab;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class WaterFlowMessageGeneratorImplTest {
    @Test
    void whenGenerateWaterFlowMessages_ThenGenerateCorrectly() {
        WaterFlowMessageGenerator waterFlowMessageGenerator = new WaterFlowMessageGeneratorImpl();
        List<String> messages = waterFlowMessageGenerator.generateWaterFlowMessages();

        assertThat(messages.size()).isEqualTo(9);
    }
}