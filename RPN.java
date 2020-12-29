package RPN_Calculator;

import java.util.Scanner;
import java.util.EnumMap;

/**
 * The RPN class will perform an RPN (Reverse Polish Notation) calculation
 * on an expression provided by the client program, utilizing a stack. The
 * expression will only be evaluated if it is a properly formed RPN expression
 * (must have one less operator than the number of operands). Otherwise, an
 * exception will be thrown indicating what the error was.
 *
 * @author  Francis Kogge
 * @version 1.0
 */
public class RPN {

    // Valid operators
    private enum Operator {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }
    private double result; // Holds the result of the RPN calculation
    private Stack<Double> operandStack; // Stack object that will hold operands
    private Scanner scanExp; // Scanner object used to parse the expression
    private EnumMap<Operator, Character> operatorMap; // Maps Operator enum to
                                                      // operator character

    /**
     * The constructor initializes the stack which will hold the operands
     * from the RPN expression, and Scanner object used to read through
     * the expression.
     *
     * @param expression Expression to be evaluated based on Reverse Polish
     *                   Notation
     */
    public RPN(String expression) {
        createEnumMap();
        operandStack = new Stack<>();
        scanExp = new Scanner(expression);
        // Captures the result of the expression
        result = evaluate();
    }

    private void createEnumMap() {
        operatorMap = new EnumMap<>(Operator.class);
        operatorMap.put(Operator.ADD, '+');
        operatorMap.put(Operator.SUBTRACT, '-');
        operatorMap.put(Operator.MULTIPLY, '*');
        operatorMap.put(Operator.DIVIDE, '/');
    }

    /**
     * Returns the result of the expression after being evaluated.
     *
     * @return Answer to the evaluated expression
     */
    public double getResult() {
        return result;
    }

    /**
     * Evaluates the expression supplied by the client program and returns
     * the answer to the equation after being fully evaluated. Throws proper
     * exception if too many or not enough operators are in the expression
     * for the RPN calculation to work properly.
     *
     * @return Answer to the evaluated expression
     * @throws IllegalArgumentException If there's too many operators
     *                                  If there's not enough operators
     */
    private double evaluate() {
        // Continues while there are still tokens to be read in
        // from the expression
        while (scanExp.hasNext()) {
            populateOperandStack();
            // If there is only 1 number in the operand stack and still
            // operators left in the expression
            if (operandStack.size() == 1 && scanExp.hasNext()) {
                throw new IllegalArgumentException("Too many operators!");
            // If the stack has at least 2 operands to be evaluated
            } else if (operandStack.size() >= 2) {
                // Perform the RPN calculation. First pop of the stack captures
                // the operand on the right side of the operator, and second
                // pop captures the operand on the left side of the operator.
                performCalculation(operandStack.pop(), operandStack.pop(),
                                   scanExp.next().charAt(0));
            }
        }
        scanExp.close();
        if (operandStack.size() > 1) {
            // Operand stack should only have 1 value at this point
            // (the answer to the expression)
            throw new IllegalArgumentException("Not enough operators!");
        }
        // Remaining value in the stack is the answer to the expression
        return operandStack.pop();
    }

    /**
     * Populates the stack with operands, which will all be of type double.
     */
    private void populateOperandStack() {
        while (scanExp.hasNextDouble()) {
            operandStack.push(scanExp.nextDouble());
        }
    }

    /**
     * Performs the next calculation in the RPN expression by evaluating the
     * two operands popped from the operand stack, based on the operator, all
     * passed in as arguments. An exception will be thrown if the operator
     * is not one of the four valid arithmetic operators.
     *
     * @param right    Operand on the right side of the operator
     * @param left     Operand on the left side of the operator
     * @param operator Arithmetic operator
     * @throws IllegalArgumentException If the operator is not a legal
     *                                  arithmetic operator
     */
    private void performCalculation(double right, double left,
                                    char operator) {
        if (operator == operatorMap.get(Operator.ADD)) {
            operandStack.push(left + right);
        } else if (operator == operatorMap.get(Operator.SUBTRACT)) {
            operandStack.push(left - right);
        } else if (operator == operatorMap.get(Operator.MULTIPLY)) {
            operandStack.push(left * right);
        } else if (operator == operatorMap.get(Operator.DIVIDE)) {
            operandStack.push(left / right);
        } else {
            // All valid operators have been exhausted at this point
            throw new IllegalArgumentException("Unknown operator: " +
                                               operator);
        }
    }

}
