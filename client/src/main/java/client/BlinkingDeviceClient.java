package client;

import java.util.Map;

import org.coderthoughts.iotdemo.BlinkingDevice;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate=true,
    service=BlinkingDeviceClient.class,
    property={"osgi.command.function=green",
        "osgi.command.scope=blink"})
public class BlinkingDeviceClient {
    @Reference
    private BlinkingDevice blinkDevice;

    public void green() {
        Map<String, String> res = blinkDevice.blinkGreenLight();
        System.out.println("Invoked device: " + res);
    }
}
