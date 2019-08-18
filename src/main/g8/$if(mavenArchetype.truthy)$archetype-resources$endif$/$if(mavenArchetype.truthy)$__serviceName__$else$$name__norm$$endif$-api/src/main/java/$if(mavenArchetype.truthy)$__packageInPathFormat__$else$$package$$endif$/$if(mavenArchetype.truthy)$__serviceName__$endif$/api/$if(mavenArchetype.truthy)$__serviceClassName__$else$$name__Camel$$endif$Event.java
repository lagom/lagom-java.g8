package $if(mavenArchetype.truthy)$\${package}.\${serviceName}$else$$package$$endif$.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.common.base.Preconditions;
import lombok.Value;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Event.GreetingMessageChanged.class, name = "greeting-message-changed")
})
public interface $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Event {
    String getName();

    @Value
    final class GreetingMessageChanged implements $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Event {
        public final String name;
        public final String message;

        @JsonCreator
        public GreetingMessageChanged(String name, String message) {
            this.name = Preconditions.checkNotNull(name, "name");
            this.message = Preconditions.checkNotNull(message, "message");
        }
    }
}
