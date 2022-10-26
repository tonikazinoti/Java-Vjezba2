package org.javalab;

public interface IWaterFlowMessage {
    String getMessageName();

    void setMessageName(String messageName);

    String getDataType();

    void setDataType(String dataType);

    int getFactor();

    void setFactor(int factor);

    int getValue();

    void setValue(int value);

    String getUnit();

    void setUnit(String unit);

    @Override
    String toString();
}
