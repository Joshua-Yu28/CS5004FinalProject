package cs5004.ast;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BooleanLiteralTest {

  @Test
  public void testConstructorAndValue() {
    BooleanLiteral trueLiteral = new BooleanLiteral(true);
    BooleanLiteral falseLiteral = new BooleanLiteral(false);

    assertNotNull(trueLiteral, "BooleanLiteral object (true) should not be null");
    assertNotNull(falseLiteral, "BooleanLiteral object (false) should not be null");
    assertTrue(trueLiteral.isValue(), "BooleanLiteral true should return true");
    assertFalse(falseLiteral.isValue(), "BooleanLiteral false should return false");
  }

  @Test
  public void testEquals() {
    BooleanLiteral trueLiteral1 = new BooleanLiteral(true);
    BooleanLiteral trueLiteral2 = new BooleanLiteral(true);
    BooleanLiteral falseLiteral1 = new BooleanLiteral(false);

    assertEquals(trueLiteral1, trueLiteral2, "Two BooleanLiterals with the same value (true) should be equal");
    assertNotEquals(trueLiteral1, falseLiteral1, "Two BooleanLiterals with different values should not be equal");
    assertNotEquals(trueLiteral1, null, "BooleanLiteral should not be equal to null");
    assertNotEquals(trueLiteral1, new Object(), "BooleanLiteral should not be equal to a different type of object");
  }

  @Test
  public void testFormat() {
    BooleanLiteral trueLiteral = new BooleanLiteral(true);
    BooleanLiteral falseLiteral = new BooleanLiteral(false);

    assertEquals("true", trueLiteral.format(), "format() should return 'true' for BooleanLiteral true");
    assertEquals("false", falseLiteral.format(), "format() should return 'false' for BooleanLiteral false");
  }

  @Test
  public void testAccept() {
    BooleanLiteral trueLiteral = new BooleanLiteral(true);
    ExpressionVisitor<String> visitor = new MockExpressionVisitor();

    assertEquals("Visited BooleanLiteral: true", trueLiteral.accept(visitor),
        "accept() should pass the BooleanLiteral (true) to the visitor's visit method correctly");
  }

  // Mock ExpressionVisitor for testing purposes
  static class MockExpressionVisitor implements ExpressionVisitor<String> {
    @Override
    public String visit(IntLiteral expr) {
      return "Visited IntLiteral: " + expr.getValue();
    }

    @Override
    public String visit(BooleanLiteral expr) {
      return "Visited BooleanLiteral: " + expr.isValue();
    }

    @Override
    public String visit(VariableReference expr) {
      return "Visited VariableReference: " + expr.getVariableName();
    }

    @Override
    public String visit(FunctionCall expr) {
      return "Visited FunctionCall: " + expr.getFunctionName();
    }

    @Override
    public String visit(AndExpression expr) {
      return "Visited AndExpression";
    }

    @Override
    public String visit(OrExpression expr) {
      return "Visited OrExpression";
    }

    @Override
    public String visit(IfExpression expr) {
      return "Visited IfExpression";
    }

    @Override
    public String visit(LetExpression expr) {
      return "Visited LetExpression";
    }
  }
}
