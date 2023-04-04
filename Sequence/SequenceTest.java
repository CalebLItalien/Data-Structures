package proj2;

/**
 * JUnit test class.
 */

import org.junit.*;
import org.junit.rules.Timeout;
import static org.junit.Assert.*;

public class SequenceTest {


    @Rule // a test will fail if it takes longer than 1/10 of a second to run
    public Timeout timeout = Timeout.millis(100);

    private Sequence makeSequence(int initialCapacity) {
        Sequence sequence = new Sequence(initialCapacity);
        String[] inputs = new String[]{"A","B","C","D","EE","FFF","1","2","3","4","!","@","#",""};
        for (String input : inputs) {
            sequence.addAfter(input);
        }
        return sequence;
    }

    private Sequence makeDefaultSequence() {
        Sequence sequence = new Sequence();
        String[] inputs = new String[]{"A","B","C","D","EE","FFF","1","2","3","4","!","@","#",""};
        for (String input : inputs) {
            sequence.addAfter(input);
        }
        return sequence;
    }


    @Test
    public void testDefaultConstruct() {
        Sequence emptySequence = new Sequence();

        assertEquals(false, emptySequence.isCurrent());
        assertEquals(null, emptySequence.getCurrent());
        assertEquals(true, emptySequence.isEmpty());
        assertEquals(10, emptySequence.getCapacity());
        assertEquals(0, emptySequence.size());
        assertEquals("{} (capacity = 10)", emptySequence.toString());
    }


    @Test
    public void testConstruct(){
        Sequence sequence = new Sequence(0);
        assertEquals(false, sequence.isCurrent());
        assertEquals(null, sequence.getCurrent());
        assertEquals(0, sequence.getCapacity());
        assertEquals(0, sequence.size());
        assertEquals("{} (capacity = 0)", sequence.toString());

        Sequence sequenceTwo = new Sequence(10);
        assertEquals(false, sequenceTwo.isCurrent());
        assertEquals(null, sequenceTwo.getCurrent());
        assertEquals(10, sequenceTwo.getCapacity());
        assertEquals(0, sequenceTwo.size());
        assertEquals("{} (capacity = 10)", sequenceTwo.toString());

        Sequence sequenceThree = new Sequence(3000);
        assertEquals(false, sequenceThree.isCurrent());
        assertEquals(null, sequenceThree.getCurrent());
        assertEquals(3000, sequenceThree.getCapacity());
        assertEquals(0, sequenceThree.size());
        assertEquals("{} (capacity = 3000)", sequenceThree.toString());

        Sequence sequenceFour = new Sequence(-1);
        assertEquals(false, sequenceFour.isCurrent());
        assertEquals(null, sequenceFour.getCurrent());
        assertEquals(0, sequenceFour.size());

        Sequence sequenceFive = makeDefaultSequence();
        assertEquals(true, sequenceFive.isCurrent());
        assertEquals("", sequenceFive.getCurrent());
        assertEquals(21, sequenceFive.getCapacity());
        assertEquals(14, sequenceFive.size());
        assertEquals("{A, B, C, D, EE, FFF, 1, 2, 3, 4, !, @, #, >} (capacity = 21)", sequenceFive.toString());
    }


    @Test
    public void testAddBefore(){
        Sequence sequence = makeSequence(19);
        sequence.addBefore("5");
        assertEquals("{A, B, C, D, EE, FFF, 1, 2, 3, 4, !, @, #, >5, } (capacity = 19)", sequence.toString());

        sequence.addBefore("AAA");
        assertEquals("{A, B, C, D, EE, FFF, 1, 2, 3, 4, !, @, #, >AAA, 5, } (capacity = 19)", sequence.toString());

        sequence.addBefore(" ");
        assertEquals("{A, B, C, D, EE, FFF, 1, 2, 3, 4, !, @, #, > , AAA, 5, } (capacity = 19)", sequence.toString());

        sequence.addBefore(",");
        assertEquals("{A, B, C, D, EE, FFF, 1, 2, 3, 4, !, @, #, >,,  , AAA, 5, } (capacity = 19)", sequence.toString());

        sequence.addBefore("-1");
        assertEquals("{A, B, C, D, EE, FFF, 1, 2, 3, 4, !, @, #, >-1, ,,  , AAA, 5, } (capacity = 19)", sequence.toString());

        sequence.addBefore("phrase");
        assertEquals("{A, B, C, D, EE, FFF, 1, 2, 3, 4, !, @, #, >phrase, -1, ,,  , AAA, 5, } (capacity = 39)",
                sequence.toString());

        Sequence emptySequence = new Sequence(0);
        emptySequence.addBefore("5");
        assertEquals("{>5} (capacity = 1)", emptySequence.toString());

        Sequence emptySequenceTwo = new Sequence(0);
        emptySequenceTwo.addBefore("AAA");
        assertEquals("{>AAA} (capacity = 1)", emptySequenceTwo.toString());

        Sequence emptySequenceThree = new Sequence(0);
        emptySequenceThree.addBefore(" ");
        assertEquals("{> } (capacity = 1)", emptySequenceThree.toString());

        Sequence emptySequenceFour = new Sequence(0);
        emptySequenceFour.addBefore("-1");
        assertEquals("{>-1} (capacity = 1)", emptySequenceFour.toString());

        Sequence emptySequenceFive = new Sequence(0);
        emptySequenceFive.addBefore("phrase");
        assertEquals("{>phrase} (capacity = 1)", emptySequenceFive.toString());
    }


    @Test
    public void testAddAfter(){
        Sequence sequence = makeSequence(19);
        sequence.addAfter("5");
        assertEquals("{A, B, C, D, EE, FFF, 1, 2, 3, 4, !, @, #, , >5} (capacity = 19)", sequence.toString());

        sequence.addAfter("AAA");
        assertEquals("{A, B, C, D, EE, FFF, 1, 2, 3, 4, !, @, #, , 5, >AAA} (capacity = 19)", sequence.toString());

        sequence.addAfter(" ");
        assertEquals("{A, B, C, D, EE, FFF, 1, 2, 3, 4, !, @, #, , 5, AAA, > } (capacity = 19)", sequence.toString());

        sequence.addAfter(",");
        assertEquals("{A, B, C, D, EE, FFF, 1, 2, 3, 4, !, @, #, , 5, AAA,  , >,} (capacity = 19)", sequence.toString());

        sequence.addAfter("-1");
        assertEquals("{A, B, C, D, EE, FFF, 1, 2, 3, 4, !, @, #, , 5, AAA,  , ,, >-1} (capacity = 19)", sequence.toString());

        sequence.addAfter("phrase");
        assertEquals("{A, B, C, D, EE, FFF, 1, 2, 3, 4, !, @, #, , 5, AAA,  , ,, -1, >phrase} (capacity = 39)", sequence.toString());

        Sequence emptySequence = new Sequence(0);
        emptySequence.addAfter("5");
        assertEquals("{>5} (capacity = 1)", emptySequence.toString());

        Sequence emptySequenceTwo = new Sequence(0);
        emptySequenceTwo.addAfter("AAA");
        assertEquals("{>AAA} (capacity = 1)", emptySequenceTwo.toString());

        Sequence emptySequenceThree = new Sequence(0);
        emptySequenceThree.addAfter(" ");
        assertEquals("{> } (capacity = 1)", emptySequenceThree.toString());

        Sequence emptySequenceFour = new Sequence(0);
        emptySequenceFour.addAfter("-1");
        assertEquals("{>-1} (capacity = 1)", emptySequenceFour.toString());

        Sequence emptySequenceFive = new Sequence(0);
        emptySequenceFive.addAfter("phrase");
        assertEquals("{>phrase} (capacity = 1)", emptySequenceFive.toString());
    }


    @Test
    public void testIsCurrent() {
        Sequence sequence = makeSequence(10);
        assertEquals(true, sequence.isCurrent());

        Sequence sequenceTwo = makeDefaultSequence();
        assertEquals(true, sequenceTwo.isCurrent());

        Sequence emptySequence = new Sequence(3000);
        assertEquals(false, emptySequence.isCurrent());

        Sequence emptySequenceTwo = new Sequence(10);
        assertEquals(false, emptySequenceTwo.isCurrent());

        Sequence emptySequenceThree = new Sequence();
        assertEquals(false, emptySequenceThree.isCurrent());

        Sequence emptySequenceFour = new Sequence(-1);
        assertEquals(false, emptySequenceFour.isCurrent());
    }


    @Test
    public void testGetCapacity(){
        Sequence sequence = makeSequence(10);
        assertEquals(21, sequence.getCapacity());

        Sequence sequenceTwo = makeDefaultSequence();
        assertEquals(21, sequenceTwo.getCapacity());

        Sequence emptySequence = new Sequence(3000);
        assertEquals(3000, emptySequence.getCapacity());

        Sequence emptySequenceTwo = new Sequence(10);
        assertEquals(10, emptySequenceTwo.getCapacity());

        Sequence emptySequenceThree = new Sequence();
        assertEquals(10, emptySequenceThree.getCapacity());
    }


    @Test
    public void testGetCurrent(){
        Sequence sequence = makeSequence(10);
        assertEquals("", sequence.getCurrent());

        Sequence sequenceTwo = makeDefaultSequence();
        assertEquals("", sequenceTwo.getCurrent());

        Sequence emptySequence = new Sequence(3000);
        assertEquals(null, emptySequence.getCurrent());

        Sequence emptySequenceTwo = new Sequence(10);
        assertEquals(null, emptySequenceTwo.getCurrent());

        Sequence emptySequenceThree = new Sequence();
        assertEquals(null, emptySequenceThree.getCurrent());
    }


    @Test
    public void testEnsureCapacity(){
        Sequence sequence = makeSequence(20);
        sequence.ensureCapacity(41);
        assertEquals(41,sequence.getCapacity());

        Sequence sequenceTwo = makeDefaultSequence();
        sequenceTwo.ensureCapacity(3000);
        assertEquals(3000,sequenceTwo.getCapacity());

        Sequence sequenceThree = makeSequence(20);
        sequenceThree.ensureCapacity(5);
        assertEquals(20, sequenceThree.getCapacity());

        Sequence emptySequence = new Sequence();
        emptySequence.ensureCapacity(-1);
        assertEquals(10, emptySequence.getCapacity());
    }


    @Test
    public void testAddAll(){
        Sequence sequence = new Sequence(0);
        Sequence sequenceTwo = new Sequence(10);
        Sequence sequenceThree = new Sequence(3000);
        Sequence sequenceFour = makeDefaultSequence();
        Sequence sequenceFive = makeSequence(10);
        Sequence sequenceSix = makeSequence(3000);

        sequence.addAll(sequenceTwo);
        assertEquals("{} (capacity = 0)", sequence.toString());
        sequence.addAll(sequenceThree);
        assertEquals("{} (capacity = 0)", sequence.toString());
        sequence.addAll(sequenceFour);
        assertEquals("{A, B, C, D, EE, FFF, 1, 2, 3, 4, !, @, #, } (capacity = 14)", sequence.toString());

        sequenceThree.addAll(sequenceFour);
        assertEquals("{A, B, C, D, EE, FFF, 1, 2, 3, 4, !, @, #, } (capacity = 3000)", sequenceThree.toString());

        sequenceFive.addAll(sequence);
        assertEquals("{A, B, C, D, EE, FFF, 1, 2, 3, 4, !, @, #, >, A, B, C, D, EE, FFF, 1, 2, 3, 4, !, @, #, } " +
                "(capacity = 28)", sequenceFive.toString());
        sequenceFive.addAll(sequenceSix);
        assertEquals("{A, B, C, D, EE, FFF, 1, 2, 3, 4, !, @, #, >, A, B, C, D, EE, FFF, 1, 2, 3, 4, !, @, #, , " +
                "A, B, C, D, EE, FFF, 1, 2, 3, 4, !, @, #, } (capacity = 42)", sequenceFive.toString());
    }


    @Test
    public void testAdvance(){
        Sequence emptySequence = new Sequence(3000);
        emptySequence.advance();
        assertEquals(null, emptySequence.getCurrent());

        Sequence emptySequenceTwo = new Sequence(10);
        emptySequenceTwo.advance();
        assertEquals(null, emptySequenceTwo.getCurrent());

        Sequence emptySequenceThree = new Sequence();
        emptySequenceThree.advance();
        assertEquals(null, emptySequenceThree.getCurrent());

        Sequence sequence = makeDefaultSequence();
        sequence.addBefore("phrase");
        sequence.advance();
        assertEquals("", sequence.getCurrent());

        Sequence sequenceTwo = makeSequence(10);
        sequenceTwo.addBefore("3");
        sequenceTwo.advance();
        assertEquals("", sequenceTwo.getCurrent());

        Sequence sequenceThree = makeSequence(3000);
        sequenceThree.addBefore(",");
        sequenceThree.advance();
        assertEquals("", sequenceThree.getCurrent());
    }


    @Test
    public void testClone(){
        Sequence sequence = new Sequence(0);
        Sequence sequenceClone = sequence.clone();
        assertEquals(sequenceClone.toString(), sequence.toString());

        Sequence sequenceTwo = new Sequence(10);
        Sequence sequenceTwoClone = sequenceTwo.clone();
        assertEquals(sequenceTwoClone.toString(), sequenceTwo.toString());

        Sequence sequenceThree = new Sequence(3000);
        Sequence sequenceThreeClone = sequenceThree.clone();
        assertEquals(sequenceThreeClone.toString(), sequenceThree.toString());

        Sequence sequenceFour = makeDefaultSequence();
        Sequence sequenceFourClone = sequenceFour.clone();
        assertEquals(sequenceFourClone.toString(), sequenceFour.toString());

        Sequence sequenceFive = makeSequence(10);
        Sequence sequenceFiveClone = sequenceFive.clone();
        assertEquals(sequenceFiveClone.toString(), sequenceFive.toString());

        Sequence sequenceSix = makeSequence(3000);
        Sequence sequenceSixClone = sequenceSix.clone();
        assertEquals(sequenceSixClone.toString(), sequenceSix.toString());
    }


    @Test
    public void testRemoveCurrent(){
        Sequence emptySequence = new Sequence(3000);
        emptySequence.removeCurrent();
        assertEquals(null, emptySequence.getCurrent());

        Sequence emptySequenceTwo = new Sequence(10);
        emptySequenceTwo.removeCurrent();
        assertEquals(null, emptySequenceTwo.getCurrent());

        Sequence emptySequenceThree = new Sequence();
        emptySequenceThree.removeCurrent();
        assertEquals(null, emptySequenceThree.getCurrent());

        Sequence sequence = makeDefaultSequence();
        sequence.addBefore("phrase");
        sequence.removeCurrent();
        assertEquals("", sequence.getCurrent());

        Sequence sequenceTwo = makeSequence(10);
        sequenceTwo.addBefore("3");
        sequenceTwo.removeCurrent();
        assertEquals("", sequenceTwo.getCurrent());

        Sequence sequenceThree = makeSequence(3000);
        sequenceThree.addBefore(",");
        sequenceThree.removeCurrent();
        assertEquals("", sequenceThree.getCurrent());

        Sequence sequenceFour = makeSequence(10);
        sequenceFour.removeCurrent();
        assertEquals(null, sequenceFour.getCurrent());
    }


    @Test
    public void testSize(){
        Sequence sequence = makeSequence(10);
        assertEquals(14, sequence.size());

        Sequence sequenceTwo = makeDefaultSequence();
        assertEquals(14, sequenceTwo.size());

        Sequence emptySequence = new Sequence(3000);
        assertEquals(0, emptySequence.size());

        Sequence emptySequenceTwo = new Sequence(10);
        assertEquals(0, emptySequenceTwo.size());

        Sequence emptySequenceThree = new Sequence();
        assertEquals(0, emptySequenceThree.size());
    }


    @Test
    public void testStart(){
        Sequence sequence = makeSequence(10);
        sequence.start();
        assertEquals("A", sequence.getCurrent());

        Sequence sequenceTwo = makeDefaultSequence();
        sequenceTwo.start();
        assertEquals("A", sequenceTwo.getCurrent());

        Sequence emptySequence = new Sequence(3000);
        emptySequence.start();
        assertEquals(null, emptySequence.getCurrent());

        Sequence emptySequenceTwo = new Sequence(10);
        emptySequenceTwo.start();
        assertEquals(null, emptySequenceTwo.getCurrent());

        Sequence emptySequenceThree = new Sequence();
        emptySequenceThree.start();
        assertEquals(null, emptySequenceThree.getCurrent());
    }


    @Test
    public void testTrimToSize(){
        Sequence sequence = makeSequence(10);
        sequence.trimToSize();
        assertEquals(14, sequence.getCapacity());

        Sequence sequenceTwo = makeDefaultSequence();
        sequenceTwo.trimToSize();
        assertEquals(14, sequenceTwo.getCapacity());

        Sequence emptySequence = new Sequence(3000);
        emptySequence.trimToSize();
        assertEquals(0, emptySequence.getCapacity());

        Sequence emptySequenceTwo = new Sequence(10);
        emptySequenceTwo.trimToSize();
        assertEquals(0, emptySequenceTwo.getCapacity());

        Sequence emptySequenceThree = new Sequence();
        emptySequenceThree.trimToSize();
        assertEquals(0, emptySequenceThree.getCapacity());
    }


    @Test
    public void testToString(){
        Sequence sequence = new Sequence(0);
        assertEquals("{} (capacity = 0)", sequence.toString());

        Sequence sequenceTwo = new Sequence(10);
        assertEquals("{} (capacity = 10)", sequenceTwo.toString());

        Sequence sequenceThree = new Sequence(3000);
        assertEquals("{} (capacity = 3000)", sequenceThree.toString());

        Sequence sequenceFour = makeDefaultSequence();
        assertEquals("{A, B, C, D, EE, FFF, 1, 2, 3, 4, !, @, #, >} (capacity = 21)", sequenceFour.toString());

        Sequence sequenceFive = makeSequence(10);
        assertEquals("{A, B, C, D, EE, FFF, 1, 2, 3, 4, !, @, #, >} (capacity = 21)", sequenceFive.toString());

        Sequence sequenceSix = makeSequence(3000);
        assertEquals("{A, B, C, D, EE, FFF, 1, 2, 3, 4, !, @, #, >} (capacity = 3000)", sequenceSix.toString());
    }


    @Test
    public void testEquals(){
        Sequence sequence = new Sequence(0);
        Sequence sequenceTwo = new Sequence(10);
        Sequence sequenceThree = new Sequence(3000);
        Sequence sequenceFour = makeDefaultSequence();
        Sequence sequenceFive = makeSequence(10);
        Sequence sequenceSix = makeSequence(3000);

        assertEquals(true, sequence.equals(sequenceTwo));
        assertEquals(true, sequenceFive.equals(sequenceSix));
        assertEquals(false, sequenceThree.equals(sequenceFour));

        sequenceSix.removeCurrent();
        assertEquals(false, sequenceFive.equals(sequenceSix));

        sequenceFour.addBefore("A");
        sequenceFour.start();
        sequenceFive.start();
        sequenceFour.removeCurrent();
        assertEquals(false, sequenceFour.equals(sequenceFive));
    }


    @Test
    public void testIsEmpty(){
        Sequence sequence = makeSequence(10);
        assertEquals(false, sequence.isEmpty());

        Sequence sequenceTwo = makeDefaultSequence();
        assertEquals(false, sequenceTwo.isEmpty());

        Sequence emptySequence = new Sequence(3000);
        assertEquals(true, emptySequence.isEmpty());

        Sequence emptySequenceTwo = new Sequence(10);
        assertEquals(true, emptySequenceTwo.isEmpty());

        Sequence emptySequenceThree = new Sequence();
        assertEquals(true, emptySequenceThree.isEmpty());
    }


    @Test
    public void testClear(){
        Sequence sequence = makeSequence(10);
        sequence.clear();
        assertEquals(true, sequence.isEmpty());
        assertEquals(null, sequence.getCurrent());

        Sequence sequenceTwo = makeDefaultSequence();
        sequenceTwo.clear();
        assertEquals(true, sequenceTwo.isEmpty());
        assertEquals(null, sequenceTwo.getCurrent());


        Sequence emptySequence = new Sequence(3000);
        emptySequence.clear();
        assertEquals(true, emptySequence.isEmpty());
        assertEquals(null, emptySequence.getCurrent());


        Sequence emptySequenceTwo = new Sequence(10);
        emptySequenceTwo.clear();
        assertEquals(true, emptySequenceTwo.isEmpty());
        assertEquals(null, emptySequenceTwo.getCurrent());


        Sequence emptySequenceThree = new Sequence();
        emptySequenceThree.clear();
        assertEquals(true, emptySequenceThree.isEmpty());
        assertEquals(null, emptySequenceThree.getCurrent());
    }

}
