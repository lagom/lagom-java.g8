/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package $package$.impl;

import javax.annotation.Nullable;
import lombok.Value;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.serialization.CompressedJsonable;

/**
 * The state for the {@link $name;format="Camel"$Entity} entity.
 */
@SuppressWarnings("serial")
@Value
@JsonDeserialize
public final class $name;format="Camel"$State implements CompressedJsonable {

  public final String message;
  public final String timestamp;

  @JsonCreator
  public $name;format="Camel"$State(String message, String timestamp) {
    this.message = Preconditions.checkNotNull(message, "message");
    this.timestamp = Preconditions.checkNotNull(timestamp, "timestamp");
  }
}
