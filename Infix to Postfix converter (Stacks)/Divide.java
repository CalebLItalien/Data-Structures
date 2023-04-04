package proj4;

/**
 * Models the operator divide for the creation of postfix expressions
 * 
 * @author Caleb L'Italien
 * @version 10/29/2022
 */

public class Divide implements Token {
    private final int PRECEDENCE = 2;

    /**
     * Handles addition of a new operand to the stack and creates a string to be added to the postfix expression
     *
     * @param s the Stack the token uses, if necessary, when processing itself.
     * @return a string to be added to the postfix expression
     */
    public String handle(Stack<Token> s) {
        String toAdd = "";
        if (!s.isEmpty()) {
            Token topOfStack = s.peek();
            if (topOfStack.toString().equals("(")) {
                s.push(this);
            }
            else {
                while (!s.isEmpty() && topOfStack.getPrecedence() >= this.getPrecedence()
                        && !topOfStack.toString().equals("(")){
                    Token data = s.pop();
                    toAdd = toAdd + data.toString();
                    topOfStack = s.peek();
                }
                s.push(this);
            }
        }
        else{
            s.push(this);
        }
        return toAdd;
    }


    /**
     * @return a string version of the divide operator
     */
    public String toString() {
        return "/";
    }


    /**
     * @return an integer precedence
     */
    public int getPrecedence() {
        return PRECEDENCE;
    }
}