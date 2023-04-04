package proj4;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * Tests the stack class
 *
 * @param <T> a generic object
 * @author Caleb L'Italien
 * @version 10/28/2022
 */

public class StackTest<T> {

	@Rule
    public Timeout timeout = Timeout.millis(100);

    private Stack<String> stack;
    
    @Before
    public void setUp() throws Exception {
        stack = new Stack<>();
    }


    @After
    public void tearDown() throws Exception {
        stack = null;
    }


    @Test
    public void testStackConstructor() {
        assertEquals ("{>}", stack.toString());
        assertEquals (0, stack.size());
        assertEquals(true, stack.isEmpty());
    }


    @Test
    public void testPush() {
        stack.push("A");
        assertEquals ("{>A}", stack.toString().replaceAll("[ ]+", ""));

        stack.push("BB");
        assertEquals ("{>BB,A}", stack.toString().replaceAll("[ ]+", ""));

        stack.push("!");
        assertEquals ("{>!,BB,A}", stack.toString().replaceAll("[ ]+", ""));

        stack.push("-1");
        assertEquals ("{>-1,!,BB,A}", stack.toString().replaceAll("[ ]+", ""));
    }


    @Test
    public void testPop(){
        stack.push("A");
        stack.push("BB");
        stack.push("!");
        stack.push("-1");

        assertEquals ("-1", stack.pop());

        assertEquals ("!", stack.pop());

        assertEquals ("BB", stack.pop());

        assertEquals ("A", stack.pop());

        assertEquals (null, stack.pop());
    }


    @Test
    public void testPeek(){
        stack.push("A");
        assertEquals ("A", stack.peek());

        stack.pop();
        assertEquals (null, stack.peek());

        stack.push("BB");
        assertEquals ("BB", stack.peek());

        stack.push("!");
        assertEquals ("!", stack.peek());

        stack.push("-1");
        assertEquals ("-1", stack.peek());
    }


    @Test
    public void testSize(){
        stack.push("A");
        assertEquals (1, stack.size());

        stack.push("BB");
        assertEquals(2, stack.size());

        stack.push("!");
        assertEquals(3, stack.size());

        stack.push("-1");
        assertEquals(4, stack.size());

        stack.pop();
        assertEquals(3, stack.size());

        stack.pop();
        assertEquals(2, stack.size());

        stack.pop();
        assertEquals(1, stack.size());

        stack.pop();
        assertEquals(0, stack.size());

        stack.pop();
        assertEquals(0, stack.size());
    }


    @Test
    public void testIsEmpty(){
        stack.push("A");
        assertEquals(false, stack.isEmpty());

        stack.push("BB");
        assertEquals(false, stack.isEmpty());

        stack.pop();
        stack.pop();
        assertEquals(true, stack.isEmpty());
    }


    @Test
    public void testToString(){
        stack.push("A");
        assertEquals ("{>A}", stack.toString().replaceAll("[ ]+", ""));

        stack.push("BB");
        assertEquals ("{>BB,A}", stack.toString().replaceAll("[ ]+", ""));

        stack.push("!");
        assertEquals ("{>!,BB,A}", stack.toString().replaceAll("[ ]+", ""));

        stack.push("-1");
        assertEquals ("{>-1,!,BB,A}", stack.toString().replaceAll("[ ]+", ""));

        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        assertEquals("{>}", stack.toString().replaceAll("[ ]+", ""));
    }
}