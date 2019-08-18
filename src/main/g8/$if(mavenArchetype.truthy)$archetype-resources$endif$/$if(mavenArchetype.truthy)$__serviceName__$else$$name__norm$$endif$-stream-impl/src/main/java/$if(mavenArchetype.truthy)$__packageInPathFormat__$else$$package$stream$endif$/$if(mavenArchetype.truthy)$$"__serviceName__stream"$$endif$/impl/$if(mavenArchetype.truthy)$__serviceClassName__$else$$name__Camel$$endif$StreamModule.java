package $if(mavenArchetype.truthy)$\${package}.\${serviceName}$else$$package$$endif$stream.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;

import $if(mavenArchetype.truthy)$\${package}.\${serviceName}$else$$package$$endif$.api.$if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Service;
import $if(mavenArchetype.truthy)$\${package}.\${serviceName}$else$$package$$endif$stream.api.$if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$StreamService;

/**
 * The module that binds the $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$StreamService so that it can be served.
 */
public class $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$StreamModule extends AbstractModule implements ServiceGuiceSupport {
    @Override
    protected void configure() {
        // Bind the $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$StreamService service
        bindService($if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$StreamService.class, $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$StreamServiceImpl.class);
        // Bind the $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Service client
        bindClient($if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Service.class);
        // Bind the subscriber eagerly to ensure it starts up
        bind($if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$StreamSubscriber.class).asEagerSingleton();
    }
}
