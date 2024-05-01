package cs5004.parser.sexp;

import cs5004.parser.Posn;
import java.util.Objects;

public final class IntLit extends Sexp {
  private final int value;

  public IntLit(Posn posn, int value) {
    super(posn);
    this.value = value;
  }

  public int getValue() { return value; }

  @Override
  public String toString() {
    return String.format("IntLit(%s, %s)", super.fieldsToString(), value);
  }

  @Override
  public String unparse() { return String.format("%d", value); }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    IntLit intLit = (IntLit) o;
    return value == intLit.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), value);
  }
}
