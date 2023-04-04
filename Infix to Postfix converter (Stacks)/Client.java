package proj4;

/**
 * Converts an input file of mathematical expressions
 * 
 * @author Caleb L'Italien
 * @version 10/29/2022
 *
 * I affirm that I have carried out the attached academic endeavors with full academic honesty, in accordance with the
 * Union College Honor Code and the course syllabus.
 */

public class Client {

    /**
     * Converts an input file of mathematical expressions
     */
    public static void main(String[] args) {
        Converter converter = new Converter("proj4_input.txt");
        converter.convert();
    }
}