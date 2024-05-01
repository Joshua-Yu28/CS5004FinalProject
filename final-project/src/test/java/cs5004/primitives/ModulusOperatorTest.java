package cs5004.primitives;

import cs5004.core.BooleanValue;
import cs5004.core.IntValue;
import cs5004.core.TypeError;
import cs5004.core.Value;
import cs5004.evalExceptions.ArityMismatchException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collections;

public class ModulusOperatorTest {

  private final ModulusOperator modOp = new ModulusOperator();

  @Test
  public void testApplySuccessful() {
    IntValue arg1 = new IntValue(10);
    IntValue arg2 = new IntValue(4);
    Value result = modOp.apply(Arrays.asList(arg1, arg2));

    assertNotNull(result, "Result should not be null");
    assertTrue(result instanceof IntValue, "Result should be an instance of IntValue");
    assertEquals(2, ((IntValue) result).getValue(), "10 % 4 should equal 2");
  }

  @Test
  public void testApplyWithZeroDenominator() {
    IntValue arg1 = new IntValue(10);
    IntValue arg2 = new IntValue(0);

    Exception exception = assertThrows(ArithmeticException.class,
        () -> modOp.apply(Arrays.asList(arg1, arg2)),
        "Division by zero should throw ArithmeticException");
    assertEquals("Division by zero error in modulus operation", exception.getMessage());
  }

  @Test
  public void testApplyWithNonIntValue() {
    IntValue arg1 = new IntValue(10);
    Value arg2 = new BooleanValue(true);

    Exception exception = assertThrows(TypeError.class,
        () -> modOp.apply(Arrays.asList(arg1, arg2)),
        "Using non-IntValue should throw TypeError");
    assertEquals("Both arguments for the modulus operator must be integers", exception.getMessage());
  }

  @Test
  public void testApplyIncorrectNumberOfArgs() {
    IntValue arg1 = new IntValue(10);

    Exception exception = assertThrows(ArityMismatchException.class,
        () -> modOp.apply(Collections.singletonList(arg1)),
        "Incorrect number of arguments should throw ArityMismatchException");
    assertEquals("Modulus", ((ArityMismatchException) exception).getFunctionName());
    assertEquals(2, ((ArityMismatchException) exception).getExpectedArity());
    assertEquals(1, ((ArityMismatchException) exception).getActualArity());
  }
}
