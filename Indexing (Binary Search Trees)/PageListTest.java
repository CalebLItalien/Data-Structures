package proj5;

/**
 * Tests the PageList class
 *
 * @author Caleb L'Italien
 * @version 10/09/2022
 */

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class PageListTest {
    @Rule
    public Timeout timeout = Timeout.millis(100);

    private PageList pagelist;

    private PageList makePageList() {
        pagelist.setWord("hello");
        pagelist.addToList(5);
        pagelist.addToList(999999);
        pagelist.addToList(-1);
        pagelist.addToList(0);
        return pagelist;
    }


    @Before
    public void constructPageList() {
        pagelist = new PageList();
    }


    @After
    public void tearDown() {
        pagelist = null;
    }


    @Test
    public void testGetWord() {
        assertNull(pagelist.getWord());

        makePageList();
        assertEquals("hello", pagelist.getWord());
    }


    @Test
    public void testSetWord() {
        pagelist.setWord("");
        assertEquals("", pagelist.getWord());

        makePageList();
        pagelist.setWord("new phrase");
        assertEquals("new phrase", pagelist.getWord());
    }


    @Test
    public void testContains() {
        assertFalse(pagelist.contains(5));
        assertFalse(pagelist.contains(999999));
        assertFalse(pagelist.contains(-1));
        assertFalse(pagelist.contains(0));

        makePageList();
        assertTrue(pagelist.contains(5));
        assertTrue(pagelist.contains(999999));
        assertTrue(pagelist.contains(-1));
        assertTrue(pagelist.contains(0));

        assertFalse(pagelist.contains(6));
        assertFalse(pagelist.contains(999998));
        assertFalse(pagelist.contains(-2));
    }


    @Test
    public void testAddToList() {
        pagelist.addToList(5);
        assertTrue(pagelist.contains(5));
        assertEquals("{5}", pagelist.toString());


        pagelist.addToList(5);
        assertTrue(pagelist.contains(5));
        assertEquals("{5}", pagelist.toString());

        pagelist.addToList(999999);
        assertTrue(pagelist.contains(999999));
        assertEquals("{5, 999999}", pagelist.toString());

        pagelist.addToList(-1);
        assertTrue(pagelist.contains(-1));
        assertEquals("{5, 999999, -1}", pagelist.toString());

        pagelist.addToList(0);
        assertTrue(pagelist.contains(0));
        assertEquals("{5, 999999, -1, 0}", pagelist.toString());

        pagelist.addToList(1);
        assertFalse(pagelist.contains(1));
        assertEquals("{5, 999999, -1, 0}", pagelist.toString());
    }


    @Test
    public void testCompareTo() {
        PageList emptyPageList = new PageList();
        assertEquals(0, emptyPageList.compareTo(pagelist));
        assertEquals(0, pagelist.compareTo(pagelist));

        emptyPageList.setWord("data");
        assertEquals(1, emptyPageList.compareTo(pagelist));
        assertEquals(-1, pagelist.compareTo(emptyPageList));

        makePageList();
        assertEquals(-4, emptyPageList.compareTo(pagelist));
    }


    @Test
    public void testFull() {
        assertFalse(pagelist.full());

        pagelist.addToList(5);
        assertFalse(pagelist.full());

        makePageList();
        assertTrue(pagelist.full());
    }


    @Test
    public void testToString() {
        assertEquals("{}", pagelist.toString());

        makePageList();
        assertEquals("hello {5, 999999, -1, 0}", pagelist.toString());
    }
}