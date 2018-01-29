package $package$stream.impl;

import akka.Done;
import akka.stream.javadsl.Flow;

import $package$.api.$name;format="Camel"$Event;
import $package$.api.$name;format="Camel"$Service;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

/**
 * This subscribes to the $name;format="Camel"$Service event stream.
 */
public class $name;format="Camel"$StreamSubscriber {
    @Inject
    public $name;format="Camel"$StreamSubscriber($name;format="Camel"$Service $name;format="camel"$Service, $name;format="Camel"$StreamRepository repository) {
        // Create a subscriber
        $name;format="camel"$Service.helloEvents().subscribe()
                // And subscribe to it with at least once processing semantics.
                .atLeastOnce(
                        // Create a flow that emits a Done for each message it processes
                        Flow.<$name;format="Camel"$Event>create().mapAsync(1, event -> {
                            if (event instanceof $name;format="Camel"$Event.GreetingMessageChanged) {
                                $name;format="Camel"$Event.GreetingMessageChanged messageChanged = ($name;format="Camel"$Event.GreetingMessageChanged) event;
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
