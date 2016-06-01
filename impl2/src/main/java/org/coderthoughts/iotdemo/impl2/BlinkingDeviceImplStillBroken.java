package org.coderthoughts.iotdemo.impl2;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.coderthoughts.iotdemo.BlinkingDevice;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

@Component(configurationPid="org.coderthoughts.iotdemo.DeviceConfig",
    configurationPolicy=ConfigurationPolicy.REQUIRE)
public class BlinkingDeviceImplStillBroken implements BlinkingDevice {
    private Config config;

    @Activate
    public void Activate(Config cfg) {
        config = cfg;
    }

    @Override
    public Map<String, String> blinkGreenLight() {
        callDevice("http://" + config.ip_address() + "/gpio/1/hi");
        try { Thread.sleep(config.on_duration()); } catch (Exception ex) {}
        callDevice("http://" + config.ip_address() + "/gpio/1/lo");

        Map<String, String> m = new HashMap<>();
        m.put("IP", config.ip_address());
        m.put("IO", "1");
        return m;
    }

    private void callDevice(String address) {
        try (Scanner s = new Scanner(new URL(address).openStream())) {
            s.useDelimiter("\\A");
            if (s.hasNext())
                s.next();
        } catch (Exception ex) {
            // ignore
        }
    }
}
