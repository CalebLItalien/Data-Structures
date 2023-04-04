package proj5;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.*;

/**
 * Tests the Binary Search Tree class
 *
 * @author Caleb L'Italien
 * @version 11/13/2022
 */

public class CalebLitalienBSTTest<T extends Comparable<T>> {
    @Rule
    public Timeout timeout = Timeout.millis(100);

    private BinarySearchTree tree;
    private final PrintStream output = System.out;
    private final ByteArrayOutputStream outputByte = new ByteArrayOutputStream();

    private BinarySearchTree<T> makeTree(){
        tree = new BinarySearchTree<>();
        tree.insert("G");
        tree.insert("H");
        tree.insert("F");
        tree.insert("D");
        tree.insert("Gg");
        tree.insert("g");
        tree.insert("G");
        return tree;
    }


    @Before
    public void constructEmptyTree() {
        tree = new BinarySearchTree<>();
    }


    @Before
    public void constructBytePrintStream() {
        System.setOut(new PrintStream(outputByte));
    }


    @After
    public void tearDown() {
        tree = null;
    }


    @Test
    public void testBinarySearchTreeConstructor() {
        assertEquals("", tree.toString());
        assertTrue(tree.isEmpty());
    }


    @Test
    public void testInsert() {
        tree.insert("G");
        assertEquals("(  G  )", tree.toString());

        tree.insert("H");
        assertEquals("(  G  (  H  ))", tree.toString());

        tree.insert("F");
        assertEquals("((  F  )  G  (  H  ))", tree.toString());

        tree.insert("D");
        assertEquals("(((  D  )  F  )  G  (  H  ))", tree.toString());

        tree.insert("Gg");
        assertEquals("(((  D  )  F  )  G  ((  Gg  )  H  ))", tree.toString());

        tree.insert("g");
        assertEquals("(((  D  )  F  )  G  ((  Gg  )  H  (  g  )))", tree.toString());

        tree.insert("G");
        assertEquals("(((  D  )  F  (  G  ))  G  ((  Gg  )  H  (  g  )))", tree.toString());

        tree.insert("");
        assertEquals("((((    )  D  )  F  (  G  ))  G  ((  Gg  )  H  (  g  )))", tree.toString());
    }


    @Test
    public void testDelete() {
        tree.delete("A");
        tree.delete("a");
        tree.delete("BBB");
        tree.delete("bb");

        makeTree();
        tree.delete("A");
        tree.delete("a");
        tree.delete("BBB");
        tree.delete("bb");

        tree.delete("G");
        assertEquals("(((  D  )  F  )  G  ((  Gg  )  H  (  g  )))", tree.toString());

        tree.delete("H");
        assertEquals("(((  D  )  F  )  G  (  Gg  (  g  )))", tree.toString());

        tree.delete("D");
        assertEquals("((  F  )  G  (  Gg  (  g  )))", tree.toString());

        tree.delete("g");
        assertEquals("((  F  )  G  (  Gg  ))", tree.toString());

        tree.delete("F");
        assertEquals("(  G  (  Gg  ))", tree.toString());

        tree.delete("Gg");
        assertEquals("(  G  )", tree.toString());

        tree.delete("G");
        assertEquals("",tree.toString());

        tree.delete("G");
        assertEquals("",tree.toString());
    }


    @Test
    public void testMaxValue() {
        assertNull(tree.maxValue());

        makeTree();
        assertEquals("g", tree.maxValue());
        tree.delete("g");

        assertEquals("H", tree.maxValue());
        tree.delete("H");

        assertEquals("Gg", tree.maxValue());
        tree.delete("Gg");

        assertEquals("G", tree.maxValue());
        tree.delete("G");
        tree.delete("G");

        assertEquals("F", tree.maxValue());
        tree.delete("F");

        assertEquals("D", tree.maxValue());
    }


    @Test
    public void testSearch() {
        assertFalse(tree.search("C"));
        assertFalse(tree.search("GG"));
        assertFalse(tree.search("Gga"));
        assertFalse(tree.search("gg"));
        assertFalse(tree.search("AAAAAAAAAAAAAAAA"));
        assertFalse(tree.search(""));

        makeTree();
        assertTrue(tree.search("G"));
        assertTrue(tree.search("F"));
        assertTrue(tree.search("H"));

        assertTrue(tree.search("D"));
        assertTrue(tree.search("Gg"));
        assertTrue(tree.search("g"));

        assertFalse(tree.search("C"));
        assertFalse(tree.search("GG"));
        assertFalse(tree.search("Gga"));
        assertFalse(tree.search("gg"));
        assertFalse(tree.search("AAAAAAAAAAAAAAAA"));
        assertFalse(tree.search(""));
    }


    @Test
    public void testGetData() {
        assertNull(tree.getData("G"));
        assertNull(tree.getData("data"));
        assertNull(tree.getData("AAAAAAAAAAAAAAAA"));

        makeTree();
        assertEquals("G", tree.getData("G"));
        assertEquals("F", tree.getData("F"));
        assertEquals("H", tree.getData("H"));

        assertEquals("D", tree.getData("D"));
        assertEquals("Gg", tree.getData("Gg"));
        assertEquals("g", tree.getData("g"));
    }


    @Test
    public void testIsEmpty() {
        assertTrue(tree.isEmpty());

        makeTree();
        assertFalse(tree.isEmpty());
    }


    @Test
    public void testPrintInOrder() {
        tree.printInOrder();
        assertEquals("", outputByte.toString().trim());

        makeTree();
        tree.printInOrder();
        assertEquals("D" + "\n" + "F" + "\n" + "G" + "\n" + "G" + "\n" + "Gg" + "\n" + "H" + "\n" + "g",
                outputByte.toString().trim());
    }


    @Test
    public void testToString() {
        assertEquals("", tree.toString());

        makeTree();
        assertEquals("(((  D  )  F  (  G  ))  G  ((  Gg  )  H  (  g  )))", tree.toString());
    }
}