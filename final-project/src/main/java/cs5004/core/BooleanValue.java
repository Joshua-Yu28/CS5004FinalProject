package cs5004.core;

import java.util.Objects;

/**
 * Represents a boolean value, either <code>true</code> or <code>false</code>.
 */
public class BooleanValue extends Value {
  private final boolean value;

  public BooleanValue(boolean value) {
    this.value = value;
  }

  public boolean getValue() {
    return value;
  }

  @Override
  public String format() {
    if (value) {
      return "true";
    } else {
      return "false";
    }
  }
  @Override
  public String toString() {
    return String.format("BooleanValue(%s)", format());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BooleanValue that = (BooleanValue) o;
    return value == that.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public boolean asBoolean() {
    return value;
  }
}
