package cs5004.ast;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;

public class ProgramTest {
  private Program program;
  private List<Definition> definitions;
  private Expression expression;

  @BeforeEach
  public void setUp() {
    // Set up with one definition and an expression
    expression = new IntLiteral(10);
    definitions = new ArrayList<>();
    definitions.add(new Definition("func", List.of("x"), expression));
    program = new Program(definitions, expression);
  }

  @Test
  public void testConstructorAndGetters() {
    assertNotNull(program, "Program object should not be null");
    assertEquals(definitions, program.getDefinitions(), "Definitions should match");
    assertSame(expression, program.getExpression(), "Expressions should match");
  }



  @Test
  public void testConstructorWithNullElementsInDefinitions() {
    List<Definition> invalidDefinitions = new ArrayList<>();
    invalidDefinitions.add(null);
    assertThrows(NullPointerException.class,
        () -> new Program(invalidDefinitions, expression),
        "Constructor should throw NullPointerException for null elements in definitions");
  }

  @Test
  public void testDataEncapsulation() {
    List<Definition> originalDefinitions = new ArrayList<>(definitions);
    definitions.add(new Definition("newFunc", List.of("y"), new IntLiteral(20)));
    assertEquals(originalDefinitions, program.getDefinitions(), "Program definitions should not change when external list is modified");
  }

  @Test
  public void testEmptyDefinitions() {
    Program emptyProgram = new Program(new ArrayList<>(), expression);
    assertTrue(emptyProgram.getDefinitions().isEmpty(), "Definitions list should be empty");
    assertSame(expression, emptyProgram.getExpression(), "Expression should match even if definitions are empty");
  }

  @Test
  public void testMultipleDefinitions() {
    definitions.add(new Definition("func2", List.of("z"), new BooleanLiteral(true)));
    Program multiDefProgram = new Program(definitions, expression);
    assertEquals(2, multiDefProgram.getDefinitions().size(), "Should contain two definitions");
    assertEquals("func2", multiDefProgram.getDefinitions().get(1).getName(), "Second definition should match 'func2'");
  }
}
