package $package$stream.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;

import $package$.api.$name;format="Camel"$Service;
import $package$stream.api.$name;format="Camel"$StreamService;

/**
 * The module that binds the $name;format="Camel"$StreamService so that it can be served.
 */
public class $name;format="Camel"$StreamModule extends AbstractModule implements ServiceGuiceSupport {
    @Override
    protected void configure() {
        // Bind the $name;format="Camel"$StreamService service
        bindService($name;format="Camel"$StreamService.class, $name;format="Camel"$StreamServiceImpl.class);
        // Bind the $name;format="Camel"$Service client
        bindClient($name;format="Camel"$Service.class);
        // Bind the subscriber eagerly to ensure it starts up
        bind($name;format="Camel"$StreamSubscriber.class).asEagerSingleton();
    }
}
