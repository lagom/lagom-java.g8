package $package$stream.impl;

import akka.NotUsed;
import akka.stream.javadsl.Source;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import $package$.api.$name;format="Camel"$Service;
import $package$stream.api.$name;format="Camel"$StreamService;

import javax.inject.Inject;

import static java.util.concurrent.CompletableFuture.completedFuture;

/**
 * Implementation of the $name;format="Camel"$StreamService.
 */
public class $name;format="Camel"$StreamServiceImpl implements $name;format="Camel"$StreamService {
    private final $name;format="Camel"$Service $name;format="camel"$Service;
    private final $name;format="Camel"$StreamRepository repository;

    @Inject
    public $name;format="Camel"$StreamServiceImpl($name;format="Camel"$Service $name;format="camel"$Service, $name;format="Camel"$StreamRepository repository) {
        this.$name;format="camel"$Service = $name;format="camel"$Service;
        this.repository = repository;
    }

    @Override
    public ServiceCall<Source<String, NotUsed>, Source<String, NotUsed>> directStream() {
        return hellos -> completedFuture(
                hellos.mapAsync(8, name -> $name;format="camel"$Service.hello(name).invoke()));
    }

    @Override
    public ServiceCall<Source<String, NotUsed>, Source<String, NotUsed>> autonomousStream() {
        return hellos -> completedFuture(
                hellos.mapAsync(8, name -> repository.getMessage(name).thenApply(message ->
                        String.format("%s, %s!", message.orElse("Hello"), name)
                ))
        );
    }
}
