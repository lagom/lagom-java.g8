/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package $organization$.$name;format="camel"$stream.impl;

import akka.NotUsed;
import akka.stream.javadsl.Source;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import $organization$.$name;format="camel"$.api.$name;format="Camel"$Service;
import $organization$.$name;format="camel"$stream.api.$name;format="Camel"$StreamService;

import javax.inject.Inject;

import static java.util.concurrent.CompletableFuture.completedFuture;

/**
 * Implementation of the $name;format="Camel"$StreamService.
 */
public class $name;format="Camel"$StreamServiceImpl implements $name;format="Camel"$StreamService {

  private final $name;format="Camel"$Service $name;format="camel"$Service;

  @Inject
  public $name;format="Camel"$StreamServiceImpl($name;format="Camel"$Service $name;format="camel"$Service) {
    this.$name;format="camel"$Service = $name;format="camel"$Service;
  }

  @Override
  public ServiceCall<Source<String, NotUsed>, Source<String, NotUsed>> stream() {
    return hellos -> completedFuture(
        hellos.mapAsync(8, name -> $name;format="camel"$Service.hello(name).invoke()));
  }
}
