package $if(mavenArchetype.truthy)$\${package}.\${serviceName}$else$$package$$endif$.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.javadsl.persistence.AggregateEvent;
import com.lightbend.lagom.javadsl.persistence.AggregateEventShards;
import com.lightbend.lagom.javadsl.persistence.AggregateEventTag;
import com.lightbend.lagom.javadsl.persistence.AggregateEventTagger;
import com.lightbend.lagom.serialization.Jsonable;
import lombok.Value;

/**
 * This interface defines all the events that the $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Entity supports.
 * <p>
 * By convention, the events should be inner classes of the interface, which
 * makes it simple to get a complete picture of what events an entity has.
 */
public interface $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Event extends Jsonable, AggregateEvent<$if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Event> {
    /**
     * Tags are used for getting and publishing streams of events. Each event
     * will have this tag, and in this case, we are partitioning the tags into
     * 4 shards, which means we can have 4 concurrent processors/publishers of
     * events.
     */
    AggregateEventShards<$if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Event> TAG = AggregateEventTag.sharded($if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Event.class, 4);

    /**
     * An event that represents a change in greeting message.
     */
    @SuppressWarnings("serial")
    @Value
    @JsonDeserialize
    final class GreetingMessageChanged implements $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Event {
        public final String name;
        public final String message;

        @JsonCreator
        GreetingMessageChanged(String name, String message) {
            this.name = Preconditions.checkNotNull(name, "name");
            this.message = Preconditions.checkNotNull(message, "message");
        }
    }

    @Override
    default AggregateEventTagger<$if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Event> aggregateTag() {
        return TAG;
    }
}
