package org.javalab.enums;

public enum QualityOfService {
    AT_MOST_ONCE (0),
    AT_LEAST_ONE (1),
    EXACTLY_ONCE (2);

    private final int value;

    QualityOfService(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
