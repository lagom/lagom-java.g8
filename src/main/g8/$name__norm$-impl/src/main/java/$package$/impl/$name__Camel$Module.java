package $package$.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;

import $package$.api.$name;format="Camel"$Service;

/**
 * The module that binds the $name;format="Camel"$Service so that it can be served.
 */
public class $name;format="Camel"$Module extends AbstractModule implements ServiceGuiceSupport {
    @Override
    protected void configure() {
        bindService($name;format="Camel"$Service.class, $name;format="Camel"$ServiceImpl.class);
    }
}
