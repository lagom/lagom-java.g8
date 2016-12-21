/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package $organization$.$name;format="camel"$stream.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import $organization$.$name;format="camel"$.api.$name;format="Camel"$Service;
import $organization$.$name;format="camel"$stream.api.$name;format="Camel"$StreamService;

/**
 * The module that binds the $name;format="Camel"$StreamService so that it can be served.
 */
public class $name;format="Camel"$StreamModule extends AbstractModule implements ServiceGuiceSupport {
  @Override
  protected void configure() {
    // Bind the $name;format="Camel"$StreamService service
    bindServices(serviceBinding($name;format="Camel"$StreamService.class, $name;format="Camel"$StreamServiceImpl.class));
    // Bind the $name;format="Camel"$Service client
    bindClient($name;format="Camel"$Service.class);
  }
}
