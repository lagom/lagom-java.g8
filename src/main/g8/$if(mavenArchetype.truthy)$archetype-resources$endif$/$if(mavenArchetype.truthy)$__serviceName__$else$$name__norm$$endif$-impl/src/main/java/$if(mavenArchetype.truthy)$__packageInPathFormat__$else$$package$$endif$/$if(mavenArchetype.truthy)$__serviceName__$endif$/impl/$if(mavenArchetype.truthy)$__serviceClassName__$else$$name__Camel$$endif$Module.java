package $if(mavenArchetype.truthy)$\${package}.\${serviceName}$else$$package$$endif$.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;

import $if(mavenArchetype.truthy)$\${package}.\${serviceName}$else$$package$$endif$.api.$if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Service;

/**
 * The module that binds the $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Service so that it can be served.
 */
public class $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Module extends AbstractModule implements ServiceGuiceSupport {
    @Override
    protected void configure() {
        bindService($if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Service.class, $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$ServiceImpl.class);
    }
}
