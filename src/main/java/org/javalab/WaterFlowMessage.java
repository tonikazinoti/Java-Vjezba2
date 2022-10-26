package org.javalab;

public class WaterFlowMessage implements IWaterFlowMessage {
    private String messageName;
    private String dataType;
    private int factor;
    private int value;
    private String unit;

    public WaterFlowMessage(String serializedMessage) {
        var keysValues = serializedMessage.split(": ");
    }

    public WaterFlowMessage(String messageName, String dataType, int factor, int value, String unit) {
        this.messageName = messageName;
        this.dataType = dataType;
        this.factor = factor;
        this.value = value;
        this.unit = unit;
    }

    @Override
    public String getMessageName() {
        return messageName;
    }

    @Override
    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    @Override
    public String getDataType() {
        return dataType;
    }

    @Override
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    @Override
    public int getFactor() {
        return factor;
    }

    @Override
    public void setFactor(int factor) {
        this.factor = factor;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String getUnit() {
        return unit;
    }

    @Override
    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return String.format("""
                        %s:
                        Tip podatka: %s
                        Faktor: %d
                        Vrijednost: %d
                        Jedinica: %s
                        """,
                        messageName, dataType, factor, value, unit);
    }
}
