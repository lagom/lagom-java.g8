/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package $package$.impl;

import akka.Done;
import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRef;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRegistry;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;
import $package$.api.GreetingMessage;
import $package$.api.$name;format="Camel"$Service;
import $package$.impl.$name;format="Camel"$Command.*;

/**
 * Implementation of the $name;format="Camel"$Service.
 */
public class $name;format="Camel"$ServiceImpl implements $name;format="Camel"$Service {

  private final PersistentEntityRegistry persistentEntityRegistry;

  @Inject
  public $name;format="Camel"$ServiceImpl(PersistentEntityRegistry persistentEntityRegistry) {
    this.persistentEntityRegistry = persistentEntityRegistry;
    persistentEntityRegistry.register($name;format="Camel"$Entity.class);
  }

  @Override
  public ServiceCall<NotUsed, String> hello(String id) {
    return request -> {
      // Look up the hello world entity for the given ID.
      PersistentEntityRef<$name;format="Camel"$Command> ref = persistentEntityRegistry.refFor($name;format="Camel"$Entity.class, id);
      // Ask the entity the Hello command.
      return ref.ask(new Hello(id, Optional.empty()));
      //return CompletableFuture.completedFuture("Hello someone");
    };
  }

  @Override
  public ServiceCall<GreetingMessage, Done> useGreeting(String id) {
    return request -> {
      // Look up the hello world entity for the given ID.
      PersistentEntityRef<$name;format="Camel"$Command> ref = persistentEntityRegistry.refFor($name;format="Camel"$Entity.class, id);
      // Tell the entity to use the greeting message specified.
      return ref.ask(new UseGreetingMessage(request.message));
    };

  }

}
