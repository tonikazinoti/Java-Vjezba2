package org.javalab;

import org.javalab.enums.WaterFlowMessageType;

import java.util.ArrayList;
import java.util.List;

public class WaterFlowMessageGeneratorImpl implements WaterFlowMessageGenerator {

    @Override
    public List<String> generateWaterFlowMessages() {
        var waterFlowMessages = new ArrayList<String>();
        waterFlowMessages.add(generateWaterFlowMessage(WaterFlowMessageType.CURRENT_WATER_TEMPERATURE));
        waterFlowMessages.add(generateWaterFlowMessage(WaterFlowMessageType.CURRENT_WATER_PRESSURE));
        waterFlowMessages.add(generateWaterFlowMessage(WaterFlowMessageType.WATER_USAGE_LITER_ONE_MINUTE));
        waterFlowMessages.add(generateWaterFlowMessage(WaterFlowMessageType.WATER_USAGE_LITER_TEN_MINUTES));
        waterFlowMessages.add(generateWaterFlowMessage(WaterFlowMessageType.WATER_USAGE_LITER_ONE_HOUR));
        waterFlowMessages.add(generateWaterFlowMessage(WaterFlowMessageType.WATER_USAGE_LITER_ONE_DAY));
        waterFlowMessages.add(generateWaterFlowMessage(WaterFlowMessageType.WATER_USAGE_CUBIC_METER_ONE_WEEK));
        waterFlowMessages.add(generateWaterFlowMessage(WaterFlowMessageType.WATER_USAGE_CUBIC_METER_ONE_MONTH));
        waterFlowMessages.add(generateWaterFlowMessage(WaterFlowMessageType.WATER_USAGE_CUBIC_METER_ONE_YEAR));

        return waterFlowMessages;
    }

    private String generateWaterFlowMessage(WaterFlowMessageType waterFlowMessageType) {
        var generatedValue = generateRandomNumber(
                waterFlowMessageType.getMinValue(), waterFlowMessageType.getMaxValue());

        var waterFlowMessage = new WaterFlowMessage(
                waterFlowMessageType.getMessageName(),
                waterFlowMessageType.getDataType(),
                waterFlowMessageType.getFactor(),
                generatedValue,
                waterFlowMessageType.getUnit());

        return waterFlowMessage.toString();
    }

    private int generateRandomNumber(int min, int max) {
        return (int) (Math.floor(Math.random() * (max - min)) + min);
    }
}
