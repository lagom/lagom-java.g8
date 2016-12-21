/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package $organization$.$name;format="camel"$.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import $organization$.$name;format="camel"$.api.$name;format="Camel"$Service;

/**
 * The module that binds the $name;format="Camel"$Service so that it can be served.
 */
public class $name;format="Camel"$Module extends AbstractModule implements ServiceGuiceSupport {
  @Override
  protected void configure() {
    bindServices(serviceBinding($name;format="Camel"$Service.class, $name;format="Camel"$ServiceImpl.class));
  }
}
