package cs5004.parser.sexp;

import cs5004.parser.Posn;
import java.util.Objects;

/**
 * Root of the s-expression class hierarchy.
 */
public abstract class Sexp {
  private final Posn posn;

  public Sexp(Posn p) {
    posn = Objects.requireNonNull(p);
  }

  public Posn getPosn() { return posn; }

  protected String fieldsToString() {
    return posn.toString();
  }

  /// Returns a string containing equivalent concrete syntax to this
  /// s-expression.
  public abstract String unparse();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Sexp sexp = (Sexp) o;
    return Objects.equals(posn, sexp.posn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(posn);
  }
}
