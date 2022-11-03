package org.javalab.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QualityOfService {
    AT_MOST_ONCE (0),
    AT_LEAST_ONE (1),
    EXACTLY_ONCE (2);

    private final int value;
}
