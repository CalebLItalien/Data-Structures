package proj3;

/**
 * JUnit test class. Tests the LinkedList class.
 *
 * Author: Caleb L'Italien
 * Last edited: 10/18/2022
 */

import org.junit.*;
import org.junit.rules.Timeout;
import static org.junit.Assert.*;

public class LinkedListTester {
    @Rule // a test will fail if it takes longer than 1/10 of a second to run
    public Timeout timeout = Timeout.millis(100);

    public LinkedList makeLinkedList(){
        LinkedList linkedList = new LinkedList();
        String[] inputs = new String[]{"A","B","C","D","EE","FFF","1","2","3","4","!","@","#",""};
        for (String input: inputs){
            linkedList.insertAtHead(input);
        }
        return linkedList;
    }


    @Test
    public void testLinkedListConstructor() {
        LinkedList linkedList = new LinkedList();
        assertEquals(0, linkedList.getLength());
        assertEquals(null, linkedList.getFirstNode());
        assertEquals("()", linkedList.toString());
    }


    @Test
    public void testGetLength() {
        LinkedList emptyLinkedList = new LinkedList();
        assertEquals(0, emptyLinkedList.getLength());

        LinkedList linkedList = makeLinkedList();
        assertEquals(14, linkedList.getLength());

        linkedList.insertAtHead("data");
        assertEquals(15, linkedList.getLength());

        linkedList.insertAtIndex("!", 3);
        assertEquals(16, linkedList.getLength());

        linkedList.insertAtEnd("j");
        assertEquals(17, linkedList.getLength());

        linkedList.removeAtIndex(5);
        assertEquals(16, linkedList.getLength());

        linkedList.removeHead();
        assertEquals(15, linkedList.getLength());
    }


    @Test
    public void testGetFirstNode() {
        LinkedList emptyLinkedList = new LinkedList();
        assertEquals(null, emptyLinkedList.getFirstNode());

        LinkedList linkedList = makeLinkedList();
        assertEquals("", linkedList.getFirstNode().data);

        linkedList.insertAtHead("data");
        assertEquals("data", linkedList.getFirstNode().data);

        linkedList.insertAtIndex("!", 0);
        assertEquals("!", linkedList.getFirstNode().data);

        linkedList.removeHead();
        assertEquals("data", linkedList.getFirstNode().data);
    }


    @Test
    public void testInsertAtHead() {
        LinkedList emptyLinkedList = new LinkedList();
        emptyLinkedList.insertAtHead("data");
        assertEquals("data", emptyLinkedList.getFirstNode().data);

        emptyLinkedList.insertAtHead("!");
        assertEquals("!", emptyLinkedList.getFirstNode().data);

        LinkedList linkedList = makeLinkedList();
        linkedList.insertAtHead("data");
        assertEquals("data", linkedList.getFirstNode().data);

        linkedList.insertAtHead("!");
        assertEquals("!", linkedList.getFirstNode().data);
    }


    @Test
    public void testRemoveHead() {
        LinkedList emptyLinkedList = new LinkedList();
        emptyLinkedList.removeHead();
        assertEquals(0, emptyLinkedList.getLength());

        LinkedList linkedList = makeLinkedList();
        linkedList.removeHead();
        assertEquals("#", linkedList.getFirstNode().data);
    }


    @Test
    public void testGetDataAtIndex() {
        LinkedList emptyLinkedList = new LinkedList();
        assertEquals(null, emptyLinkedList.getDataAtIndex(5));
        assertEquals(0, emptyLinkedList.getLength());

        LinkedList linkedList = makeLinkedList();
        assertEquals("", linkedList.getDataAtIndex(0));
        assertEquals(null, linkedList.getDataAtIndex(15));
        assertEquals("#", linkedList.getDataAtIndex(1));
        assertEquals("A", linkedList.getDataAtIndex(13));
    }


    @Test
    public void testRemoveAtIndex() {
        LinkedList emptyLinkedList = new LinkedList();
        emptyLinkedList.removeAtIndex(5);
        assertEquals("()", emptyLinkedList.toString());
        assertEquals(0, emptyLinkedList.getLength());

        emptyLinkedList.removeAtIndex(0);
        assertEquals("()", emptyLinkedList.toString());
        assertEquals(0, emptyLinkedList.getLength());

        LinkedList linkedList = makeLinkedList();
        linkedList.removeAtIndex(0);
        assertEquals("(#, @, !, 4, 3, 2, 1, FFF, EE, D, C, B, A)", linkedList.toString());
        assertEquals(13, linkedList.getLength());

        linkedList.removeAtIndex(5);
        assertEquals("(#, @, !, 4, 3, 1, FFF, EE, D, C, B, A)", linkedList.toString());
        assertEquals(12, linkedList.getLength());

        linkedList.removeAtIndex(11);
        assertEquals("(#, @, !, 4, 3, 1, FFF, EE, D, C, B)", linkedList.toString());
        assertEquals(11, linkedList.getLength());

        linkedList.removeAtIndex(11);
        assertEquals("(#, @, !, 4, 3, 1, FFF, EE, D, C, B)", linkedList.toString());
        assertEquals(11, linkedList.getLength());
    }


    @Test
    public void testInsertAtEnd() {
        LinkedList emptyLinkedList = new LinkedList();
        emptyLinkedList.insertAtEnd("j");
        assertEquals("(j)", emptyLinkedList.toString());

        LinkedList linkedList = makeLinkedList();
        linkedList.insertAtEnd("data");
        assertEquals("(, #, @, !, 4, 3, 2, 1, FFF, EE, D, C, B, A, data)", linkedList.toString());
    }


    @Test
    public void testInsertAtIndex() {
        LinkedList emptyLinkedList = new LinkedList();
        emptyLinkedList.insertAtIndex("data",5);
        assertEquals("()",emptyLinkedList.toString());
        assertEquals(0, emptyLinkedList.getLength());

        LinkedList linkedList = makeLinkedList();
        linkedList.insertAtIndex("data",0);
        assertEquals("(data, , #, @, !, 4, 3, 2, 1, FFF, EE, D, C, B, A)", linkedList.toString());
        assertEquals(15, linkedList.getLength());

        linkedList.insertAtIndex("k",4);
        assertEquals("(data, , #, @, k, !, 4, 3, 2, 1, FFF, EE, D, C, B, A)", linkedList.toString());
        assertEquals(16, linkedList.getLength());

        linkedList.insertAtIndex("end",16);
        assertEquals("(data, , #, @, k, !, 4, 3, 2, 1, FFF, EE, D, C, B, A, end)", linkedList.toString());
        assertEquals(17, linkedList.getLength());


        linkedList.insertAtIndex("!" , 18);
        assertEquals("(data, , #, @, k, !, 4, 3, 2, 1, FFF, EE, D, C, B, A, end)", linkedList.toString());
        assertEquals(17, linkedList.getLength());
    }


    @Test
    public void testToString() {
        LinkedList emptyLinkedList = new LinkedList();
        assertEquals("()", emptyLinkedList.toString());

        LinkedList linkedList = makeLinkedList();
        assertEquals("(, #, @, !, 4, 3, 2, 1, FFF, EE, D, C, B, A)", linkedList.toString());
    }
}
