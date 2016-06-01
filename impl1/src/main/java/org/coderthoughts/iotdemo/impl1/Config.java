package org.coderthoughts.iotdemo.impl1;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(pid="org.coderthoughts.iotdemo.DeviceConfig",
    name="Device Configuration")
public @interface Config {
    @AttributeDefinition(description = "The IP Address to access the device on")
    String ip_address();

    @AttributeDefinition(type = AttributeType.INTEGER, description = "On duration")
    int on_duration() default 500;
}
