package cs5004.evaluator;

import cs5004.ast.AndExpression;
import cs5004.ast.BooleanLiteral;
import cs5004.ast.Definition;
import cs5004.ast.Expression;
import cs5004.ast.ExpressionVisitor;
import cs5004.ast.FunctionCall;
import cs5004.ast.IfExpression;
import cs5004.ast.IntLiteral;
import cs5004.ast.LetExpression;
import cs5004.ast.OrExpression;
import cs5004.ast.VariableReference;
import cs5004.core.BooleanValue;
import cs5004.core.Environment;
import cs5004.core.IntValue;
import cs5004.core.Value;
import cs5004.core.TypeError;
import cs5004.evalExceptions.ArityMismatchException;
import cs5004.evalExceptions.UndefinedFunctionException;
import cs5004.evalExceptions.UndefinedVariableException;
import cs5004.primitives.Primitive;
import cs5004.primitives.PrimitiveTable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * Represents an expression evaluator that interprets various types of expressions
 * in the context of given environments for definitions and variables.
 * <p>
 * This evaluator is capable of handling literal values, variable references,
 * as well as complex expressions such as function calls, logical operations, and conditional expressions.
 */
public class Evaluator implements ExpressionVisitor<Value> {
  private final Environment<Definition> definitions;
  private final Environment<Value> variables;
  private PrimitiveTable primitives;
  /**
   * Constructs an evaluator with specified environments for definitions and variables.
   *
   * @param definitions the environment containing function definitions
   * @param variables the environment containing variable bindings
   */
  public Evaluator(
    Environment<Definition> definitions,
    Environment<Value> variables
  ) {
    this.definitions = Objects.requireNonNull(definitions);
    this.variables = Objects.requireNonNull(variables);
    this.primitives = new PrimitiveTable();
  }
  /**
   * Creates a new evaluator instance with an additional variable binding.
   *
   * @param variableName the name of the variable to bind
   * @param variableValue the value to bind to the variable
   * @return a new {@code Evaluator} instance with the updated environment
   */
  public Evaluator withVariableBinding(
    String variableName,
    Value variableValue
  ) {
    return new Evaluator(
      definitions,
      variables.extend(variableName, variableValue)
    );
  }
  /**
   * Evaluates an integer literal.
   *
   * @param e the integer literal to evaluate
   * @return an {@code IntValue} representing the integer literal
   */
  @Override
  public Value visit(IntLiteral e) {
    return new IntValue(e.getValue());
  }
  /**
   * Evaluates a boolean literal.
   *
   * @param e the boolean literal to evaluate
   * @return a {@code BooleanValue} representing the boolean literal
   */
  @Override
  public Value visit(BooleanLiteral e) {
    return new BooleanValue(e.isValue());
  }
  /**
   * Evaluates a function call expression.
   *
   * @param e the function call expression to evaluate
   * @return the result of the function call
   * @throws UndefinedFunctionException if the function is not defined
   * @throws ArityMismatchException if the number of provided arguments does not match the function's requirements
   */
  @Override
  public Value visit(FunctionCall e) {
    List<Expression> args = e.getArguments();
    List<Value> evaluatedArgs = new ArrayList<>();
    for (Expression arg : args) {
      evaluatedArgs.add(arg.accept(this));
    }

    String functionName = e.getFunctionName();
    Definition def = definitions.lookup(functionName);
    if (def != null) {
      // Start with the current variable environment, so outer variables are still accessible
      Evaluator scopedEvaluator = new Evaluator(definitions, variables);
      List<String> paramNames = def.getArguments();
      if (paramNames.size() != evaluatedArgs.size()) {
        throw new ArityMismatchException("Function", evaluatedArgs.size(), paramNames.size());
      }
      for (int i = 0; i < paramNames.size(); i++) {
        //x,y
        scopedEvaluator = scopedEvaluator.withVariableBinding(paramNames.get(i), evaluatedArgs.get(i));
      }
      return def.getBody().accept(scopedEvaluator);
    } else {
      Primitive prim = primitives.lookup(functionName);
      if (prim != null) {
        return prim.apply(evaluatedArgs);
      } else {
        throw new UndefinedFunctionException("Function " + functionName + " is not defined.");
      }
    }
  }


  /**
   * Evaluates an AND expression with short-circuit logic.
   *
   * @param e the AND expression to evaluate
   * @return the result of the AND operation as a {@code BooleanValue}
   */
  @Override
  public Value visit(AndExpression e) {
    BooleanValue leftVal = (BooleanValue) e.getLeftOperand().accept(this);
    if (!leftVal.getValue()) {
      return new BooleanValue(false);
    }
    BooleanValue rightVal = (BooleanValue) e.getRightOperand().accept(this);
    return rightVal;
  }
  /**
   * Evaluates an OR expression with short-circuit logic.
   *
   * @param e the OR expression to evaluate
   * @return the result of the OR operation as a {@code BooleanValue}
   */
  @Override
  public Value visit(OrExpression e) {
    BooleanValue leftVal = (BooleanValue) e.getLeft().accept(this);
    if (leftVal.getValue()) {
      return new BooleanValue(true);
    }
    BooleanValue rightVal = (BooleanValue) e.getRight().accept(this);
    return rightVal;
  }
  /**
   * Evaluates an if expression, selecting between two branches based on a condition.
   *
   * @param e the if expression to evaluate
   * @return the result of either the consequent or alternative branch, based on the condition's value
   * @throws TypeError if the condition does not evaluate to a boolean
   */
  @Override
  public Value visit(IfExpression e) {
    Value condValue = e.getCondition().accept(this);
    if (!(condValue instanceof BooleanValue)) {
      throw new TypeError("Condition expression must be a boolean");
    }
    BooleanValue condition = (BooleanValue) e.getCondition().accept(this);
    if (condition.getValue()) {
      return e.getConsequent().accept(this);
    } else {
      return e.getAlternative().accept(this);
    }
  }
  /**
   * Evaluates a let expression, introducing a new variable binding for the duration of the expression.
   *
   * @param e the let expression to evaluate
   * @return the result of the body of the let expression
   */
  @Override
  public Value visit(LetExpression e) {
    Value rhs = e.getRhs().accept(this);
    Environment<Value> newVarEnv = variables.extend(e.getVarName(), rhs);
    return e.getBody().accept(new Evaluator(definitions, newVarEnv));
  }
  /**
   * Evaluates a variable reference, looking up its value in the current environment.
   *
   * @param e the variable reference to evaluate
   * @return the value of the variable
   * @throws UndefinedVariableException if the variable is not defined
   */
  @Override
  public Value visit(VariableReference e) {
    Value value = variables.lookup(e.getVariableName());
    if (value == null) {
      throw new UndefinedVariableException(e.getVariableName());
    }
    return value;
  }
}