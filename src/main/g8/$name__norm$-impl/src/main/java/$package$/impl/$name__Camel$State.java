package $package$.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.serialization.CompressedJsonable;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * The state for the {@link $name;format="Camel"$Aggregate} entity.
 */
@SuppressWarnings("serial")
@Value
@JsonDeserialize
public final class $name;format="Camel"$State implements CompressedJsonable {
    public static final $name;format="Camel"$State INITIAL = new $name;format="Camel"$State("Hello", LocalDateTime.now().toString());
    public final String message;
    public final String timestamp;

    @JsonCreator
    $name;format="Camel"$State(String message, String timestamp) {
        this.message = Preconditions.checkNotNull(message, "message");
        this.timestamp = Preconditions.checkNotNull(timestamp, "timestamp");
    }

    public $name;format="Camel"$State withMessage(String message) {
        return new $name;format="Camel"$State(message, LocalDateTime.now().toString());
    }


}
