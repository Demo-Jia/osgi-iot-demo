package org.coderthoughts.iotdemo.impl1;

import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;

import org.coderthoughts.iotdemo.BlinkingDevice;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

@Component(configurationPid="org.coderthoughts.iotdemo.DeviceConfig",
    configurationPolicy=ConfigurationPolicy.REQUIRE)
public class BlinkingDeviceImplBroken implements BlinkingDevice {
    private Config config;

    @Activate
    public void Activate(Config cfg) {
        config = cfg;
    }

    @Override
    public Map<String, String> blinkGreenLight() {
        System.out.println("Blink Yellow: " + config.ip_address());
        callDevice("http://" + config.ip_address() + "/gpio/3/hi");
        try { Thread.sleep(config.on_duration()); } catch (Exception ex) {}
        callDevice("http://" + config.ip_address() + "/gpio/3/lo");
        return Collections.singletonMap("IP", config.ip_address());
    }

    private void callDevice(String address) {
        try (Scanner s = new Scanner(new URL(address).openStream())) {
            s.useDelimiter("\\A");
            if (s.hasNext())
                s.next();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
