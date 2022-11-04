package org.javalab;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class DeviceConfig {
    private final String serverUri;
    private final String clientId;
    private final String username;
    private final String password;
    private final List<Sensor> sensors = new ArrayList<>();
}
