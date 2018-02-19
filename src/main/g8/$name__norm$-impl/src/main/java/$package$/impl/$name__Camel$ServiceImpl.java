package $package$.impl;

import akka.Done;
import akka.NotUsed;
import akka.japi.Pair;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.broker.Topic;
import com.lightbend.lagom.javadsl.broker.TopicProducer;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRef;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRegistry;

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
            return ref.ask(new Hello(id));
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

    @Override
    public Topic<$package$.api.$name;format="Camel"$Event> helloEvents() {
        // We want to publish all the shards of the hello event
        return TopicProducer.taggedStreamWithOffset($name;format="Camel"$Event.TAG.allTags(), (tag, offset) ->
                // Load the event stream for the passed in shard tag
                persistentEntityRegistry.eventStream(tag, offset).map(eventAndOffset -> {
                    // Now we want to convert from the persisted event to the published event.
                    // Although these two events are currently identical, in future they may
                    // change and need to evolve separately, by separating them now we save
                    // a lot of potential trouble in future.
                    $package$.api.$name;format="Camel"$Event eventToPublish;

                    if (eventAndOffset.first() instanceof $name;format="Camel"$Event.GreetingMessageChanged) {
                        $name;format="Camel"$Event.GreetingMessageChanged messageChanged = ($name;format="Camel"$Event.GreetingMessageChanged) eventAndOffset.first();
                        eventToPublish = new $package$.api.$name;format="Camel"$Event.GreetingMessageChanged(
                                messageChanged.getName(), messageChanged.getMessage()
                        );
                    } else {
                        throw new IllegalArgumentException("Unknown event: " + eventAndOffset.first());
                    }

                    // We return a pair of the translated event, and its offset, so that
                    // Lagom can track which offsets have been published.
                    return Pair.create(eventToPublish, eventAndOffset.second());
                })
        );
    }
}
