package proj5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Driver for the index maker project
 * 
 * @author Caleb L'Italien
 * @version 11/12/2022
 *
 *  I affirm that I have carried out the attached academic endeavors with full academic honesty, in accordance with the
 *  Union College Honor Code and the course syllabus.
 */

public class Client {
    private static Scanner myReader;

    private static final int MIN_SIZE = 2;
    private static final String PAGE_BREAK = "#";
    private static final int STARTING_PAGE = 1;

    public static void main(String[] args) {
        makeIndex("uscons.txt");
    }


    /**
     * Makes an index out of fileName. Gradescope needs this function.
     *
     * @param fileName path to text file that you want to index
     */
    public static void makeIndex(String fileName) {
        try {
            myReader = new Scanner(new File(fileName));
            myReader.useDelimiter("[^a-zA-Z#]+");
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        BinarySearchTree dictionary = new BinarySearchTree<>();
        BinarySearchTree index = new BinarySearchTree<>();
        int pageCount = STARTING_PAGE;

        while (myReader.hasNext()) {
            String nextExpression = myReader.next();
            if (nextExpression.equals(PAGE_BREAK)){
                pageCount ++;
            }
            if (nextExpression.length() > MIN_SIZE && !dictionary.search(nextExpression)) {
                PageList temp = new PageList();
                temp.setWord(nextExpression);

                if (index.search(temp)) {
                    PageList pagelist = (PageList) index.getData(temp);
                    if (!pagelist.contains(pageCount)) {
                        if (!pagelist.full()) {
                            pagelist.addToList(pageCount);
                        }
                        else {
                            System.out.println("Deleting '" + pagelist + "' from index.");
                            index.delete(pagelist);
                            dictionary.insert(nextExpression);
                        }
                    }
                }
                else {
                    PageList pagelist = new PageList();
                    pagelist.setWord(nextExpression);
                    pagelist.addToList(pageCount);
                    index.insert(pagelist);
                }
            }
        }
        index.printInOrder();
        dictionary.printInOrder();
    }
}