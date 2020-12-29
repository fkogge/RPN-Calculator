package RPN_Calculator;

import java.util.Scanner;

/**
 * The RPNCalculator program retrieves an RPN expression from the user then
 * exercises the RPN class to retrieve a the result of the RPN calculation.
 * The program will also check to see if the RPN expression is valid before
 * evaluating it.
 *
 * @author  Francis Kogge
 * @version 1.0
 */
public class RPNCalculator {

    /**
     * After printing a short introduction, retrieves an RPN expression from
     * the user to be evaluated. The user may have the program attempt to
     * calculate as many expressions as they wish.
     *
     * @param args String array containing the command line arguments
     */
    public static void main(String[] args) {
        introduction();
        getExpression();
        goodbye();
    }

    /**
     * Prints a short introduction and brief instructions for the program.
     */
    public static void introduction() {
        System.out.println("\nWelcome to the RPN Calculator!\n\n" +
                           "Enter operands followed by the operator(s) " +
                           "(blank line to quit).\n");
    }

    /**
     * Retrieves the RPN expression from the user and passes it as an
     * argument to the calculateExpression method if it is not a blank line.
     */
    public static void getExpression() {
        String expression; // Holds the RPN expression
        boolean validExpression; // Indicates a valid expression
        // Scanner object used to retrieve user input
        Scanner console = new Scanner(System.in);

        do {
            // Prompts user for expression
            System.out.print("calc> ");
            expression = console.nextLine();
            validExpression = !expression.equals("");
            if (validExpression) {
                calculateExpression(expression);
            }
        // Continues while the expression entered is valid
        } while (validExpression);
        console.close();
    }

    /**
     * Calculates the RPN expression by utilizing the RPN class, and prints
     * out the result to the console.
     *
     * @param expression RPN expression to be evaluated
     */
    public static void calculateExpression(String expression) {
        // Creates the RPN object and passes the expression to be
        // evaluated
        RPN calculate = new RPN(expression);
        System.out.println(calculate.getResult());
    }

    /**
     * Prints a goodbye message to the console.
     */
    public static void goodbye() {
        System.out.println("\nGoodbye, and thanks for using the RPN " +
                           "calculator!");
    }

}
