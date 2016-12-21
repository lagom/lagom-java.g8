/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package $organization$.$name;format="camel"$.impl;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.serialization.CompressedJsonable;

/**
 * The state for the {@link $name;format="Camel"$Entity} entity.
 */
@SuppressWarnings("serial")
@Immutable
@JsonDeserialize
public final class $name;format="Camel"$State implements CompressedJsonable {

  public final String message;
  public final String timestamp;

  @JsonCreator
  public $name;format="Camel"$State(String message, String timestamp) {
    this.message = Preconditions.checkNotNull(message, "message");
    this.timestamp = Preconditions.checkNotNull(timestamp, "timestamp");
  }

  @Override
  public boolean equals(@Nullable Object another) {
    if (this == another)
      return true;
    return another instanceof $name;format="Camel"$State && equalTo(($name;format="Camel"$State) another);
  }

  private boolean equalTo($name;format="Camel"$State another) {
    return message.equals(another.message) && timestamp.equals(another.timestamp);
  }

  @Override
  public int hashCode() {
    int h = 31;
    h = h * 17 + message.hashCode();
    h = h * 17 + timestamp.hashCode();
    return h;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("$name;format="Camel"$State").add("message", message).add("timestamp", timestamp).toString();
  }
}
