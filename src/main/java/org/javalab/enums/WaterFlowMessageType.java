package org.javalab.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum WaterFlowMessageType {
    CURRENT_WATER_TEMPERATURE("Trenutna temperatura vode", "int16", 10, -32668, 32668, "°C"),
    CURRENT_WATER_PRESSURE("Trenutni tlak vode", "uint16", 1000, 0, 65336, "Bar"),
    WATER_USAGE_LITER_ONE_MINUTE("Potrošnja u zadnjoj minuti", "uint16", 0, 0, 65336, "litra"),
    WATER_USAGE_LITER_TEN_MINUTES("Potrošnja u zadnjih 10 minuta", "uint16", 0, 0, 65336, "litra"),
    WATER_USAGE_LITER_ONE_HOUR("Potrošnja u zadnjih sat vremena", "uint16", 0, 0, 65336, "litra"),
    WATER_USAGE_LITER_ONE_DAY("Potrošnja u zadnjem danu", "uint16", 0, 0, 65336, "litra"),
    WATER_USAGE_CUBIC_METER_ONE_WEEK("Potrošnja u zadnjih tjedan dana", "uint16", 10, 0, 65336, "m3"),
    WATER_USAGE_CUBIC_METER_ONE_MONTH("Potrošnja u zadnjih mjesec dana", "uint16", 10, 0, 65336, "m3"),
    WATER_USAGE_CUBIC_METER_ONE_YEAR("Potrošnja u zadnjih godinu dana", "uint16", 10, 0, 65336, "m3"),
    ;

    private final String messageName;
    private final String dataType;
    private final int factor;
    private final int minValue;
    private final int maxValue;
    private final String unit;
}
