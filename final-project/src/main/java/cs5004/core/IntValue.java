package cs5004.core;

import java.util.Objects;

/**
 * Represents an integer value.
 */
public class IntValue extends Value {
  private final int value;

  public IntValue(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.format("IntValue(%d)", value);
  }

  @Override
  public String format() {
    return String.format("%d", value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IntValue intValue = (IntValue) o;
    return value == intValue.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public int asInteger() { return value; }
}
