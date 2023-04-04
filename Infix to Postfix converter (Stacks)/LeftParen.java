package proj4;

/**
 * Models the symbol left parenthesis for the creation of postfix expressions
 * 
 * @author Caleb L'Italien
 * @version 10/29/2022
 */

public class LeftParen implements Token {
    public final int NO_PRECEDENCE = -1;

    /**
     * Handles addition of a new operand to the stack and creates a string to be added to the postfix expression
     *
     * @param s the Stack the token uses, if necessary, when processing itself.
     * @return a string to be added to the postfix expression
     */
    public String handle(Stack<Token> s) {
        s.push(this);
        return "";
    }


    /**
     * @return a string version of a left parenthesis
     */
    public String toString() {
        return "(";
    }


    /**
     * @return an integer precedence
     */
    public int getPrecedence() {
        return NO_PRECEDENCE;
    }
}