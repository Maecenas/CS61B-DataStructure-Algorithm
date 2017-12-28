/* BadTransactionException.java */

/**
 *  For inappropriate transaction input such as to deposit negative money 
 */

public class BadTransactionException extends Exception {

	public int amount;

	public BadTransactionException(int amount) {
		super("Invalid amount: " + amount);
		this.amount = amount;
	}
}