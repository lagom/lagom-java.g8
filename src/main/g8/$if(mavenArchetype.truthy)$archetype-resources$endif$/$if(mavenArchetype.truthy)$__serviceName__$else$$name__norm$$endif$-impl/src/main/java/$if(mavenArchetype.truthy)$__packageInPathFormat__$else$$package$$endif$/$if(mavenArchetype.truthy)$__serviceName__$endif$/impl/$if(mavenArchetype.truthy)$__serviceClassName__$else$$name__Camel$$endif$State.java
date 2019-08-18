package $if(mavenArchetype.truthy)$\${package}.\${serviceName}$else$$package$$endif$.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.serialization.CompressedJsonable;
import lombok.Value;

/**
 * The state for the {@link $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Entity} entity.
 */
@SuppressWarnings("serial")
@Value
@JsonDeserialize
public final class $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$State implements CompressedJsonable {
    public final String message;
    public final String timestamp;

    @JsonCreator
    $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$State(String message, String timestamp) {
        this.message = Preconditions.checkNotNull(message, "message");
        this.timestamp = Preconditions.checkNotNull(timestamp, "timestamp");
    }
}
