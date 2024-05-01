package cs5004.primitives;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PrimitiveTableTest {

  private PrimitiveTable table;

  @BeforeEach
  public void setUp() {
    table = new PrimitiveTable();
  }

  @Test
  public void testLookup() {
    assertNotNull(table.lookup("not"), "Lookup for 'not' should not return null");
    assertNotNull(table.lookup("=="), "Lookup for '==' should not return null");
    assertNotNull(table.lookup("!="), "Lookup for '!=' should not return null");
    assertNotNull(table.lookup("<"), "Lookup for '<' should not return null");
    assertNotNull(table.lookup("<="), "Lookup for '<=' should not return null");
    assertNotNull(table.lookup(">"), "Lookup for '>' should not return null");
    assertNotNull(table.lookup(">="), "Lookup for '>=' should not return null");
    assertNotNull(table.lookup("+"), "Lookup for '+' should not return null");
    assertNotNull(table.lookup("-"), "Lookup for '-' should not return null");
    assertNotNull(table.lookup("*"), "Lookup for '*' should not return null");
    assertNotNull(table.lookup("/"), "Lookup for '/' should not return null");
    assertNotNull(table.lookup("mod"), "Lookup for 'mod' should not return null");
  }

  @Test
  public void testLookupNonExistent() {
    assertNull(table.lookup("undefined"), "Lookup for a non-existent operator should return null");
  }

  @Test
  public void testCorrectClassTypes() {
    assertTrue(table.lookup("not") instanceof NotOperator, "'not' should return an instance of NotOperator");
    assertTrue(table.lookup("==") instanceof EqualsOperator, "'==' should return an instance of EqualsOperator");
    assertTrue(table.lookup("!=") instanceof NotEqualsOperator, "'!=' should return an instance of NotEqualsOperator");
    assertTrue(table.lookup("<") instanceof LessThanOperator, "'<' should return an instance of LessThanOperator");
    assertTrue(table.lookup("<=") instanceof LessThanOrEqualOperator, "'<=' should return an instance of LessThanOrEqualOperator");
    assertTrue(table.lookup(">") instanceof GreaterThanOperator, "'>' should return an instance of GreaterThanOperator");
    assertTrue(table.lookup(">=") instanceof GreaterThanOrEqualOperator, "'>=' should return an instance of GreaterThanOrEqualOperator");
    assertTrue(table.lookup("+") instanceof AddOperator, "'+' should return an instance of AddOperator");
    assertTrue(table.lookup("-") instanceof SubtractOperator, "'-' should return an instance of SubtractOperator");
    assertTrue(table.lookup("*") instanceof MultiplyOperator, "'*' should return an instance of MultiplyOperator");
    assertTrue(table.lookup("/") instanceof DivideOperator, "'/' should return an instance of DivideOperator");
    assertTrue(table.lookup("mod") instanceof ModulusOperator, "'mod' should return an instance of ModulusOperator");
  }
}
