package $if(mavenArchetype.truthy)$\${package}.\${serviceName}$else$$package$$endif$stream.impl;

import akka.Done;
import akka.stream.javadsl.Flow;

import $if(mavenArchetype.truthy)$\${package}.\${serviceName}$else$$package$$endif$.api.$if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Event;
import $if(mavenArchetype.truthy)$\${package}.\${serviceName}$else$$package$$endif$.api.$if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Service;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

/**
 * This subscribes to the $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Service event stream.
 */
public class $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$StreamSubscriber {
    @Inject
    public $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$StreamSubscriber($if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Service $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Service, $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$StreamRepository repository) {
        // Create a subscriber
        $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Service.helloEvents().subscribe()
                // And subscribe to it with at least once processing semantics.
                .atLeastOnce(
                        // Create a flow that emits a Done for each message it processes
                        Flow.<$if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Event>create().mapAsync(1, event -> {
                            if (event instanceof $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Event.GreetingMessageChanged) {
                                $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Event.GreetingMessageChanged messageChanged = ($if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Event.GreetingMessageChanged) event;
                                // Update the message
                                return repository.updateMessage(messageChanged.getName(), messageChanged.getMessage());
                            } else {
                                // Ignore all other events
                                return CompletableFuture.completedFuture(Done.getInstance());
                            }
                        })
                );
    }
}
