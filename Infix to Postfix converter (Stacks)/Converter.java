package proj4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Converts an input text file of mathematical expressions into corresponding postfix expressions
 * 
 * @author Caleb L'Italien
 * @version 10/29/2022
 */

public class Converter {
	private Scanner myReader;

	/**
	 * Non-default constructor of a scanner
	 * @param infile path to an input file
	 */
    public Converter(String infile) {
		try {
            myReader = new Scanner(new File(infile)); 
    	}
		catch (FileNotFoundException error) {
			System.out.println("File not found.");
		}
	}


	/**
	 * Converts mathematical expressions into corresponding postfix expressions
	 */
	public void convert() {
		while (myReader.hasNext()) {
			String nextExpression = myReader.next();
			String postfix = "";
			Stack operators = new Stack<>();

			for (char character : nextExpression.toCharArray()) {
				String characterStr = String.valueOf(character);
				String toAdd = "";
				switch (characterStr) {
					case "+":
						Plus plus = new Plus();
						toAdd = plus.handle(operators);
						break;
					case "-":
						Minus minus = new Minus();
						toAdd = minus.handle(operators);
						break;
					case "*":
						Multiply multiply = new Multiply();
						toAdd = multiply.handle(operators);
						break;
					case "/":
						Divide divide = new Divide();
						toAdd = divide.handle(operators);
						break;
					case "^":
						Exponent exponent = new Exponent();
						toAdd = exponent.handle(operators);
						break;
					case "(":
						LeftParen leftParen = new LeftParen();
						toAdd = leftParen.handle(operators);
						break;
					case ")":
						RightParen rightParen = new RightParen();
						toAdd = rightParen.handle(operators);
						break;
					case ";":
						Semicolon semicolon = new Semicolon();
						toAdd = semicolon.handle(operators);
						break;
					default:
						postfix = postfix + characterStr;
					}
				postfix = postfix + toAdd;
			}
			nextExpression = nextExpression.substring(0, nextExpression.length() - 1);

			String conversion = nextExpression + " --> ";
			conversion = conversion + postfix;
			System.out.println(conversion);
		}
	}
}