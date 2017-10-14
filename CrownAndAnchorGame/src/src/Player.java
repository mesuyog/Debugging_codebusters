
public class Player {
	private String name;
	private int balance;
	private int limit;
	
	public Player(String name, int balance) {
		if (name == null || name .isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty");
		if (balance < 0) throw new IllegalArgumentException("Balance cannot be negative");
		this.name = name;
		this.balance = balance;
		this.limit = 0;
	}
		
	public String getName() { return name; }
	public int getBalance() { return balance; }
	public int getLimit() { return limit; }
	
	public void setLimit(int limit) {
		if (limit < 0) throw new IllegalArgumentException("Limit cannot be negative.");
		if (limit > balance)  throw new IllegalArgumentException("Limit cannot be greater than balance.");
		this.limit = limit;
	}

	public boolean balanceExceedsLimit() {
		return (balance > limit);
	}
	
	public boolean balanceExceedsLimitBy(int amount) {
		return (balance - amount > limit);
	}
	
	public void takeBet(int bet) {
		if (bet < 0) throw new IllegalArgumentException("Bet cannot be negative.");
		if (!balanceExceedsLimitBy(bet)) throw new IllegalArgumentException("Placing bet would go below limit.");
		balance = balance - bet;
	}
	public void returnBet(int bet) {
 		if (bet < 0) throw new IllegalArgumentException("Bet cannot be negative.");
 		if (!balanceExceedsLimitBy(bet)) throw new IllegalArgumentException("Placing bet would go below limit.");
 		balance = balance + bet;
 	}
	
	public void receiveWinnings(int winnings) {
		if (winnings < 0) throw new IllegalArgumentException("Winnings cannot be negative.");
		balance = balance + winnings;	
		//  - adding test code to check incorrect balance increase
 		balance = balance + winnings;
 		//System.out.println("DEBUGGING: Adding winnings of " + winnings + " to balance. Now balance: " + balance);		
	}
	
	public String toString() {
		return String.format("Player: %s, Balance: %d, Limit: %d", name, balance, limit);
	}
	/*
 +	 * Author: Suyog Rajbhandari
 +	 
 +	 * This method created aims to return the bet value to the player in case he wins a game
 +	 * */
 +	public void receiveBetReturned(int bet) {
 +		balance = balance + bet;
 +		System.out.println("----Player.java: bet of " + bet + " has been returned to " + this.getName() + ". Now he has: " + balance);
 +		
 +	}
  }
}
