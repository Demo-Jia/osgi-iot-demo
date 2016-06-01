package client;

import java.util.Map;

import org.coderthoughts.iotdemo.BlinkingDevice;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate=true,
    service=BlinkingDeviceClient.class,
    property={"osgi.command.function=blink",
        "osgi.command.scope=device"})
public class BlinkingDeviceClient {
    @Reference
    private BlinkingDevice blinkDevice;

    public void blink() {
        Map<String, String> res = blinkDevice.blinkGreenLight();
        System.out.println("Invoked device: " + res);
    }
}
