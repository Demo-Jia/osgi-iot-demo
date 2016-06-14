package refclient;

import java.lang.reflect.Method;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.util.tracker.ServiceTracker;

@Component(immediate=true)
public class ReflectiveClient {
    private static final String SERVICE_API = "org.coderthoughts.iotdemo.BlinkingDevice";

    private ServiceTracker st;

    @Activate
    public void activate(BundleContext context) throws Exception {
        st = new ServiceTracker(context, SERVICE_API, null) {
            @Override
            public Object addingService(ServiceReference reference) {
                Object svc = super.addingService(reference);
                System.out.println("Found service: " + svc);

                invoke(svc);

                return svc;
            }
        };
        st.open(true);
        System.out.println("Looking for service: " + SERVICE_API);
    }

    protected void invoke(Object svc) {
        try {
            Class<? extends Object> cls = svc.getClass();
            Method m = cls.getMethod("foo");
            m.invoke(svc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deactivate
    public void deactivate() throws Exception {
        st.close();
        st = null;
    }
}