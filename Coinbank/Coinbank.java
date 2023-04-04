package proj1;  // Don't change the package name.  Gradescope expects this.

/**
 * Represents a coinbank, which can categorize, store, and remove coins.
 * @author Caleb L'Italien
 * CSC-151-01
 *
 * I affirm that I have carried out the attached academic endeavors with full academic honesty,
 * in accordance with the Union College Honor Code and the course syllabus.
 *
 * @version 09/19/22
 */
public class Coinbank {
	
	// Denominations
	public static final int PENNY_VALUE = 1;
	public static final int NICKEL_VALUE = 5;
	public static final int DIME_VALUE = 10;
	public static final int QUARTER_VALUE = 25;
	
	// give meaningful names to holder array indices
	private final int PENNY = 0;
	private final int NICKEL = 1;
	private final int DIME = 2;
	private final int QUARTER = 3;
	
	// how many types of coins does the bank hold?
	private final int COINTYPES = 4;
	private final int NO_COINS = 0;
	
	private int[] holder;

	/**
	 * Default constructor
	 */
	public Coinbank() {
		holder = new int[COINTYPES]; }
	
	/**
	 * getter
	 * @param coinType denomination of coin to get. Valid denominations are 1,5,10,25.
	 * @return number of coins that bank is holding of that type, or -1 if denomination not valid
	 */
	public int get(int coinType){
		if (isBankable(coinType)){
			int coinPosition = coinIndex(coinType);
			return holder[coinPosition]; }

		else{
			return NO_COINS; } }

	/**
	 * getter
	 * @param coinType denomination of coin to get the index of. Valid denominations are 1,5,10,25.
	 * @return The index of the coin type in the holder.
	 */
	private int coinIndex(int coinType){
		int coinIndex;
		if (coinType == QUARTER_VALUE){
			coinIndex = QUARTER; }

		else if (coinType == DIME_VALUE){
			coinIndex = DIME; }

		else if (coinType == NICKEL_VALUE){
			coinIndex = NICKEL; }

		else{
			coinIndex = PENNY; }
		return coinIndex; }

	/**
	 * setter
	 * @param coinType denomination of coin to set
	 * @param numCoins number of coins
	 */
	private void set(int coinType, int numCoins) {
		int coinIndex = coinIndex(coinType);
		holder[coinIndex] = numCoins; }
	
	/**
	 * Return true if given coin can be held by this bank.  Else false.
	 * @param coin penny, nickel, dime, or quarter is bankable.  All others are not.
	 * @return true if bank can hold this coin, else false
	 */
	private boolean isBankable(int coin){
		switch (coin) {
		case PENNY_VALUE: case NICKEL_VALUE: 
		case DIME_VALUE: case QUARTER_VALUE:
			return true;
		default: 
			return false; } }
	
	/** 
	 * insert valid coin into bank.  Returns true if deposit
	 * successful (i.e. coin was penny, nickel, dime, or quarter).
	 * Returns false if coin not recognized
	 * 
	 * @param coinType either 1, 5, 10, or 25 to be valid
	 * @return true if deposit successful, else false
	 */
	public boolean insert(int coinType){
		if (!isBankable(coinType)) {
			return false; }

		else {
			set(coinType, get(coinType)+1);
			return true; } }
	
	/**
	 * returns the requested number of the requested coin type, if possible.
	 * Does nothing if the coin type is invalid.  If bank holds
	 * fewer coins than is requested, then all of the coins of that
	 * type will be returned.
	 * @param coinType either 1, 5, 10, or 25 to be valid
	 * @param requestedCoins number of coins to be removed
	 * @return number of coins that are actually removed
	 */
	public int remove(int coinType, int requestedCoins) {
		if (isBankable(coinType) && (requestedCoins > NO_COINS)){
			int numHave = get(coinType);
			int coinsLeft = numLeft(requestedCoins, numHave);
			set(coinType, coinsLeft);

			return coinsRemoved(numHave, requestedCoins); }
		return NO_COINS; }

	/**
	 * returns actual number of coins to be removed based on availability of coins in the bank
	 * @param numHave the number of coins in the bank
	 * @param requestedCoins the number of coins requested from the bank
	 * @return the number of coins to be removed
	 */
	private int coinsRemoved(int numHave, int requestedCoins){
		int coinsRemoved;
		if (numHave < requestedCoins){
			coinsRemoved = numHave; }

		else{
			coinsRemoved = requestedCoins; }
		return coinsRemoved; }

	/**
	 * returns number of coins remaining after removing the
	 * requested amount.  Returns zero if requested amount > what we have
	 * @param numWant number of coins to be removed
	 * @param numHave number of coins you have
	 * @return number of coins left after removal
	 */
	private int numLeft(int numWant, int numHave){
		return Math.max(0, numHave-numWant); }
	
	/**
	 * Returns bank as a printable string
	 */
	public String toString() {
		double total = (get(PENNY_VALUE) * PENNY_VALUE +
				get(NICKEL_VALUE) * NICKEL_VALUE + 
				get(DIME_VALUE) * DIME_VALUE +
				get(QUARTER_VALUE) * QUARTER_VALUE) / 100.0;
				
		String toReturn = "The bank currently holds $" + total + " consisting of \n";
		toReturn+=get(PENNY_VALUE) + " pennies\n";
		toReturn+=get(NICKEL_VALUE) + " nickels\n";
		toReturn+=get(DIME_VALUE) + " dimes\n";
		toReturn+=get(QUARTER_VALUE) + " quarters\n";
		return toReturn; } }