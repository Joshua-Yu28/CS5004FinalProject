package cs5004.parser.sexp;

import cs5004.parser.Posn;
import java.util.Objects;

public final class BoolLit extends Sexp {
  private final boolean value;

  public BoolLit(Posn posn, boolean value) {
    super(posn);
    this.value = value;
  }

  public boolean getValue() { return value; }

  @Override
  public String unparse() {
    return value ? "#t" : "#f";
  }

  @Override
  public String toString() {
    return String.format("BoolLit(%s, %s)", super.fieldsToString(), value);
  }

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
    BoolLit boolLit = (BoolLit) o;
    return value == boolLit.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), value);
  }
}
