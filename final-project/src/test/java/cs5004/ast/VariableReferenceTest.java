package cs5004.ast;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VariableReferenceTest {
  @Test
  public void testConstructorAndGetVariableName() {
    VariableReference varRef = new VariableReference("x");
    assertNotNull(varRef, "VariableReference object should not be null");
    assertEquals("x", varRef.getVariableName(), "Variable name should match the constructor argument");
  }


  @Test
  public void testEquals() {
    VariableReference varRef1 = new VariableReference("x");
    VariableReference varRef2 = new VariableReference("x");
    VariableReference varRef3 = new VariableReference("y");

    assertEquals(varRef1, varRef2, "Two VariableReferences with the same name should be equal");
    assertNotEquals(varRef1, varRef3, "Two VariableReferences with different names should not be equal");
    assertNotEquals(varRef1, null, "VariableReference should not be equal to null");
    assertNotEquals(varRef1, "x", "VariableReference should not be equal to a String");
  }

  @Test
  public void testFormat() {
    VariableReference varRef = new VariableReference("x");
    assertEquals("x", varRef.format(), "format() should return the variable name");
  }

  @Test
  public void testAccept() {
    VariableReference varRef = new VariableReference("x");
    ExpressionVisitor<String> visitor = new MockExpressionVisitor();
    assertEquals("Visited VariableReference: x", varRef.accept(visitor), "accept() should pass this object to the visitor's visit method");
  }

  // Mock ExpressionVisitor for testing purposes
  static class MockExpressionVisitor implements ExpressionVisitor<String> {
    @Override
    public String visit(IntLiteral expr) {
      return "Visited IntLiteral";
    }

    @Override
    public String visit(BooleanLiteral expr) {
      return "Visited BooleanLiteral";
    }

    @Override
    public String visit(VariableReference expr) {
      return "Visited VariableReference: " + expr.getVariableName();
    }

    @Override
    public String visit(FunctionCall expr) {
      return "Visited FunctionCall";
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
