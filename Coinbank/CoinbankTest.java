package common;
/**
 * JUnit test class.  Use these tests as models for your own.
 */
import org.junit.*;
import org.junit.rules.Timeout;
import static org.junit.Assert.*;

import proj1.Coinbank;

public class CoinbankTest {
	
	@Rule // a test will fail if it takes longer than 1/10 of a second to run
 	public Timeout timeout = Timeout.millis(100); 

	/**
	 * Sets up a bank with the given coins
	 * @param pennies number of pennies you want
	 * @param nickels number of nickels you want
	 * @param dimes number of dimes you want
	 * @param quarters number of quarters you want
	 * @return the Coinbank filled with the requested coins of each type
	 */
	private Coinbank makeBank(int pennies, int nickels, int dimes, int quarters) {
		Coinbank c = new Coinbank();
		int[] money = new int[]{pennies, nickels, dimes, quarters};
		int[] denom = new int[]{1,5,10,25};
		for (int index=0; index<money.length; index++) {
			int numCoins = money[index];
			for (int coin=0; coin<numCoins; coin++) {
				c.insert(denom[index]);
			}
		}
		return c;
	}

	@Test // bank should be empty upon construction
	public void testConstruct() {
		Coinbank emptyDefault = new Coinbank();
		assertEquals(0, emptyDefault.get(1));
		assertEquals(0, emptyDefault.get(5));
		assertEquals(0, emptyDefault.get(10));
		assertEquals(0, emptyDefault.get(25));
	}
	

	@Test // inserting coin should return true & one coin should be in bank
	public void testInsert_return()
	{
		Coinbank c = new Coinbank();
		assertTrue(c.insert(1));
		assertTrue(c.insert(5));
		assertTrue(c.insert(10));
		assertTrue(c.insert(25));

		assertEquals(1,c.get(1));
		assertEquals(1,c.get(5));
		assertEquals(1,c.get(10));
		assertEquals(1,c.get(25));
	}

	@Test // inserting invalid coin should return false and no coin should be in bank
	public void testInsertInvalid(){
		Coinbank c = new Coinbank();
		assertFalse(c.insert(-110));
		assertFalse(c.insert(-1));
		assertFalse(c.insert(3));
		assertFalse(c.insert(110));

		assertEquals(0,c.get(1));
		assertEquals(0,c.get(5));
		assertEquals(0,c.get(10));
		assertEquals(0,c.get(25));
	}
	
	@Test // getter should return correct values
	public void testGet()
	{
		Coinbank c = makeBank(3,2,15,1);
		assertEquals(3,c.get(1));
		assertEquals(2,c.get(5));
		assertEquals(15,c.get(10));
		assertEquals(1,c.get(25));

		Coinbank d = makeBank(0,0,0,0);
		assertEquals(0,d.get(1));
		assertEquals(0,d.get(5));
		assertEquals(0,d.get(10));
		assertEquals(0,d.get(25));

		assertEquals(0,d.get(-110));
		assertEquals(0,d.get(-1));
		assertEquals(0,d.get(3));
		assertEquals(0,d.get(110));
	}
	
	@Test // getter should not alter the bank
	public void testGet_contents()
	{
		Coinbank c = makeBank(5,2,15,1);
		c.get(1);
		c.get(5);
		c.get(10);
		c.get(25);
		String expected = "The bank currently holds $1.9 consisting of \n5 pennies\n2 nickels\n15 dimes\n1 quarters\n";
		assertEquals(expected,c.toString());

		Coinbank d = makeBank(0,0,0,0);
		d.get(1);
		d.get(5);
		d.get(10);
		d.get(25);
		String expect = "The bank currently holds $0.0 consisting of \n0 pennies\n0 nickels\n0 dimes\n0 quarters\n";
		assertEquals(expect,d.toString());
	}

	@Test // test of remove
	public void testRemove_justEnough()
	{
		Coinbank c = makeBank(4,1,3,5);
		assertEquals(5,c.remove(25,5));
		assertEquals(3,c.remove(1,3));
		assertEquals(1,c.remove(5,1));
		assertEquals(2,c.remove(10,2));
		String expected = "The bank currently holds $0.11 consisting of \n1 pennies\n0 nickels\n1 dimes\n0 quarters\n";
		assertEquals(expected,c.toString());
	}

	@Test // test of remove
	public void testRemove_tooLittle(){
		Coinbank c = makeBank(0,1,2,3);
		assertEquals(0,c.remove(1,3));
		assertEquals(1,c.remove(5,10));
		assertEquals(2,c.remove(10,30));
		assertEquals(3,c.remove(25,25));
	}
	
	@Test // remove should not do anything if invalid coin or invalid number of coins is requested
	public void testRemove_invalidCoin()
	{
		Coinbank c = makeBank(4,1,3,5);
		assertEquals(0,c.remove(3,1));
		assertEquals(0,c.remove(-1,2));
		assertEquals(0,c.remove(110,3));
		assertEquals(0,c.remove(-110,4));

		assertEquals(0,c.remove(1,-2));

		Coinbank d = makeBank(0,0,0,0);
		assertEquals(0,d.remove(3,1));
		assertEquals(0,d.remove(-1,2));
		assertEquals(0,d.remove(110,3));
		assertEquals(0,d.remove(-110,4));

		assertEquals(0,d.remove(25,-10));
	}
}
