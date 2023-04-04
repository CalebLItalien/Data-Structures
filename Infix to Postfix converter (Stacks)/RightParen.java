package proj4;

/**
 * Models the symbol right parenthesis for the creation of postfix expressions
 * 
 * @author Caleb L'Italien
 * @version 10/29/2022
 */

public class RightParen implements Token {
    public final int NO_PRECEDENCE = -1;

    /**
     * Handles addition of a new operand to the stack and creates a string to be added to the postfix expression
     *
     * @param s the Stack the token uses, if necessary, when processing itself.
     * @return a string to be added to the postfix expression
     */
    public String handle (Stack<Token> s) {
        String toAdd = "";
        Token topOfStack = s.peek();
        while (!topOfStack.toString().equals("(")){
            Token data = s.pop();
            toAdd = toAdd + data.toString();
            topOfStack = s.peek();
        }
        s.pop();
        return toAdd;
    }


    /**
     * @return a string version of a right parenthesis
     */
    public String toString() {
        return ")";
    }


    /**
     * @return an integer precedence
     */
    public int getPrecedence() {
        return NO_PRECEDENCE;
    }
}