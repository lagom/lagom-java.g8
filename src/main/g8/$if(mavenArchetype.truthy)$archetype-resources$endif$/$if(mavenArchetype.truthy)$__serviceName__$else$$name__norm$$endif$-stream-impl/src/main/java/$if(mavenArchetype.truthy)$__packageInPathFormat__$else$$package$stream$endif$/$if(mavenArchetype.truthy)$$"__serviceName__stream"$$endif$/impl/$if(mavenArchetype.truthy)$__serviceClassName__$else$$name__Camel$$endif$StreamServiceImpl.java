package $if(mavenArchetype.truthy)$\${package}.\${serviceName}$else$$package$$endif$stream.impl;

import akka.NotUsed;
import akka.stream.javadsl.Source;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import $if(mavenArchetype.truthy)$\${package}.\${serviceName}$else$$package$$endif$.api.$if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Service;
import $if(mavenArchetype.truthy)$\${package}.\${serviceName}$else$$package$$endif$stream.api.$if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$StreamService;

import javax.inject.Inject;

import static java.util.concurrent.CompletableFuture.completedFuture;

/**
 * Implementation of the $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$StreamService.
 */
public class $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$StreamServiceImpl implements $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$StreamService {
    private final $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Service $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Service;
    private final $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$StreamRepository repository;

    @Inject
    public $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$StreamServiceImpl($if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Service $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Service, $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$StreamRepository repository) {
        this.$if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Service = $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Service;
        this.repository = repository;
    }

    @Override
    public ServiceCall<Source<String, NotUsed>, Source<String, NotUsed>> directStream() {
        return hellos -> completedFuture(
                hellos.mapAsync(8, name -> $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Service.hello(name).invoke()));
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
