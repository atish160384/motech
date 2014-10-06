package org.motechproject.mds.service;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.springframework.context.ApplicationContext;

/**
 * The <code>ServiceUtil</code> class provides utility methods to work with services
 */
public final class ServiceUtil {
    private ServiceUtil() {}

    public static <S> MotechDataService<S> getServiceForInterfaceName(BundleContext bundleContext, String interfaceName) {
        ServiceReference ref = bundleContext.getServiceReference(interfaceName);

        return ref == null ? null : (MotechDataService<S>) bundleContext.getService(ref);
    }

    public static <S> MotechDataService<S> getServiceFromAppContext(ApplicationContext applicationContext,
                                                                    Class<S> entityClass) {
        return getServiceFromAppContext(applicationContext, entityClass.getName());
    }

    public static <S> MotechDataService<S> getServiceFromAppContext(ApplicationContext applicationContext,
                                                                    String entityClassName) {
        String beanName = entityClassName + "DataService";
        return applicationContext.containsBean(beanName) ?
                (MotechDataService<S>) applicationContext.getBean(beanName) : null;
    }
}
