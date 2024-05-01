package cs5004.ast;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

public class DefinitionTest {

  private Definition definition;
  private Expression expr;

  @BeforeEach
  public void setUp() {
    // Setup a dummy expression
    expr = new IntLiteral(5);
    List<String> args = Arrays.asList("x", "y");
    definition = new Definition("myFunction", args, expr);
  }

  @Test
  public void testConstructorAndGetters() {
    assertNotNull(definition, "Definition object should not be null");
    assertEquals("myFunction", definition.getName(), "Function name should match");
    assertEquals(Arrays.asList("x", "y"), definition.getArguments(), "Arguments should match");
    assertSame(expr, definition.getBody(), "Expression should match the one passed to constructor");
  }

  @Test
  public void testEquals() {
    Expression anotherExpr = new IntLiteral(5);
    List<String> anotherArgs = Arrays.asList("x", "y");
    Definition sameDefinition = new Definition("myFunction", anotherArgs, anotherExpr);
    Definition differentDefinition1 = new Definition("otherFunction", anotherArgs, anotherExpr);
    Definition differentDefinition2 = new Definition("myFunction", Arrays.asList("z"), anotherExpr);
    Definition differentDefinition3 = new Definition("myFunction", anotherArgs, new IntLiteral(10));

    assertEquals(definition, sameDefinition, "Definitions should be equal");
    assertNotEquals(definition, differentDefinition1,
        "Definitions should not be equal if names differ");
    assertNotEquals(definition, differentDefinition2,
        "Definitions should not be equal if arguments differ");
    assertNotEquals(definition, differentDefinition3,
        "Definitions should not be equal if bodies differ");
    assertNotEquals(definition, null, "Definitions should not be equal to null");
    assertNotEquals(definition, "string", "Definitions should not be equal to a different type");
  }

  @Test
  public void testHashCode() {
    Definition sameDefinition = new Definition("myFunction", Arrays.asList("x", "y"), expr);
    assertEquals(definition.hashCode(), sameDefinition.hashCode(),
        "Hash codes should be the same for equal objects");

    Definition differentDefinition = new Definition("otherFunction", Arrays.asList("x", "y"), expr);
    assertNotEquals(definition.hashCode(), differentDefinition.hashCode(),
        "Hash codes should be different for non-equal objects");
  }

}