package $if(mavenArchetype.truthy)$\${package}.\${serviceName}$else$$package$$endif$.impl;

import akka.Done;
import akka.NotUsed;
import akka.japi.Pair;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.broker.Topic;
import com.lightbend.lagom.javadsl.broker.TopicProducer;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRef;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRegistry;

import javax.inject.Inject;

import $if(mavenArchetype.truthy)$\${package}.\${serviceName}$else$$package$$endif$.api.GreetingMessage;
import $if(mavenArchetype.truthy)$\${package}.\${serviceName}$else$$package$$endif$.api.$if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Service;
import $if(mavenArchetype.truthy)$\${package}.\${serviceName}$else$$package$$endif$.impl.$if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Command.*;

/**
 * Implementation of the $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Service.
 */
public class $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$ServiceImpl implements $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Service {
    private final PersistentEntityRegistry persistentEntityRegistry;

    @Inject
    public $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$ServiceImpl(PersistentEntityRegistry persistentEntityRegistry) {
        this.persistentEntityRegistry = persistentEntityRegistry;
        persistentEntityRegistry.register($if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Entity.class);
    }

    @Override
    public ServiceCall<NotUsed, String> hello(String id) {
        return request -> {
            // Look up the hello world entity for the given ID.
            PersistentEntityRef<$if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Command> ref = persistentEntityRegistry.refFor($if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Entity.class, id);
            // Ask the entity the Hello command.
            return ref.ask(new Hello(id));
        };
    }

    @Override
    public ServiceCall<GreetingMessage, Done> useGreeting(String id) {
        return request -> {
            // Look up the hello world entity for the given ID.
            PersistentEntityRef<$if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Command> ref = persistentEntityRegistry.refFor($if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Entity.class, id);
            // Tell the entity to use the greeting message specified.
            return ref.ask(new UseGreetingMessage(request.message));
        };
    }

    @Override
    public Topic<$if(mavenArchetype.truthy)$\${package}.\${serviceName}$else$$package$$endif$.api.$if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Event> helloEvents() {
        // We want to publish all the shards of the hello event
        return TopicProducer.taggedStreamWithOffset($if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Event.TAG.allTags(), (tag, offset) ->
                // Load the event stream for the passed in shard tag
                persistentEntityRegistry.eventStream(tag, offset).map(eventAndOffset -> {
                    // Now we want to convert from the persisted event to the published event.
                    // Although these two events are currently identical, in future they may
                    // change and need to evolve separately, by separating them now we save
                    // a lot of potential trouble in future.
                    $if(mavenArchetype.truthy)$\${package}.\${serviceName}$else$$package$$endif$.api.$if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Event eventToPublish;

                    if (eventAndOffset.first() instanceof $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Event.GreetingMessageChanged) {
                        $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Event.GreetingMessageChanged messageChanged = ($if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Event.GreetingMessageChanged) eventAndOffset.first();
                        eventToPublish = new $if(mavenArchetype.truthy)$\${package}.\${serviceName}$else$$package$$endif$.api.$if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Event.GreetingMessageChanged(
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
