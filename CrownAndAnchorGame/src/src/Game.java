import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Game {

	private List<Dice> dice;
	private List<DiceValue> values;
	
	public Game(Dice die1, Dice die2, Dice die3) {
		if (die1 == null || die2 == null || die3 == null) throw new IllegalArgumentException("Dice cannot be null.");
		dice = new ArrayList<Dice>();
		dice.add(die1);
		dice.add(die2);
		dice.add(die3);
		values = new ArrayList<DiceValue>();
	}

	public List<DiceValue> getDiceValues() {
		values.clear();
		for (Dice d : dice) {
			values.add(d.getValue());
		}
		return Collections.unmodifiableList(values);
	}	
	
	public int playRound(Player player, DiceValue pick, int bet ) {		
		if (player == null) throw new IllegalArgumentException("Player cannot be null.");
		if (pick == null) throw new IllegalArgumentException("Pick cannot be negative.");
		if (bet < 0) throw new IllegalArgumentException("Bet cannot be negative.");
		
		// Suyog Rajbhandari - code added to test incorrect balance increase bug
 		System.out.println("----GAME.JAVA: Balance before betting an amount of " + bet + ": " + player.getBalance());
 		//System.out.println("----GAME.JAVA: Balance before betting an amount of " + bet + ": " + player.getBalance());
		
		player.takeBet(bet);
		// Detection of bug1
		System.out.println("----GAME.JAVA: Balance after betting "+ bet + ": " + player.getBalance());     
		//System.out.println("----GAME.JAVA: Balance after betting "+ bet + ": " + player.getBalance());     
		int matches = 0;
		System.out.println("---Bug 3 detector: Rolling new dices");
		for ( Dice d : dice) {
			System.out.println("--- Dice value before roll: " + d.getValue());
			d.roll();
			System.out.println("--- Dice value after roll: " + d.getValue());
			if (d.getValue().equals(pick)) { 
				matches += 1;
			}
		}
		// Detection of Bug1
		System.out.println("----GAME.JAVA: number of match: " + matches);
		//System.out.println("----GAME.JAVA: number of match: " + matches);
		int winnings = matches * bet;

		if (matches > 0) {	
			// Player should have his bet returned here because he wins
 			System.out.println("----GAME.JAVA: Player wins, his bet of " + bet + " should be returned to his balance here!");
 			//System.out.println("----GAME.JAVA: Player wins, his bet of " + bet + " should be returned to his balance here!");
			// The bellow method is called so that the bet amount can be added back to the player's balance on winning
			player.receiveBetReturned(bet);
			//player.receiveBetReturned(bet);
			player.receiveWinnings(winnings);
			//player.receiveWinnings(winnings);
			
			System.out.println("----GAME.JAVA: Player received winnings of " + winnings);
			//System.out.println("----GAME.JAVA: Player received winnings of " + winnings);
 			System.out.println("----GAME.JAVA: Now player has balance of: " + player.getBalance());
 			//System.out.println("----GAME.JAVA: Now player has balance of: " + player.getBalance());
		}
        return winnings;		
	}
	
}
