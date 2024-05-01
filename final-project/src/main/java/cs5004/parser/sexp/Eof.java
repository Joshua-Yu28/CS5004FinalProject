package cs5004.parser.sexp;

import cs5004.parser.Posn;

public class Eof extends Sexp {
  public Eof(Posn p) {
    super(p);
  }

  @Override
  public String unparse() { return "#<eof>"; }

  @Override
  public String toString() {
    return String.format("Eof(%s)", super.fieldsToString());
  }
}
