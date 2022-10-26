package org.javalab;

public interface WaterFlowMessageGenerator {
    IWaterFlowMessage generateWaterTemperatureMessage();

    default int generateRandomNumber(int min, int max) {
        return (int) (Math.floor(Math.random() * (max - min)) + min);
    }
}
