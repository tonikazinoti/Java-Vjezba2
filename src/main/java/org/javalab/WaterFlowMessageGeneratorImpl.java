package org.javalab;

public class WaterFlowMessageGeneratorImpl implements WaterFlowMessageGenerator {
    @Override
    public IWaterFlowMessage generateWaterTemperatureMessage() {
        var messageName = "Trenutna temperatura vode";
        var dataType = "int16";
        var factor = 10;
        var value = generateRandomNumber(-32668, 32668);
        var unit = "°C";

        return new WaterFlowMessage(messageName, dataType, factor, value, unit);
    }
}
