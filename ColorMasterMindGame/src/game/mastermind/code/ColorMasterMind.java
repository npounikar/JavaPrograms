package game.mastermind.code;

import java.util.Scanner;

public class ColorMasterMind {

	Player p1, p2;
	Scanner sc;

	int totalChances;

	public ColorMasterMind() {
		initGame();
	}

	private void initGame() {

		p1 = new Player();
		p2 = new Player();

		totalChances = 10;

		//Set a sequence of colors for P1
		p1.getColorSequence()[0] = Color.BLUE; 
		p1.getColorSequence()[1] = Color.RED; 
		p1.getColorSequence()[2] = Color.GREEN; 
		p1.getColorSequence()[3] = Color.BLUE; 
	}


	public void play() {

		for(int i = 0; i < totalChances; i++) {
			if(p2.isHasWon()) {
				System.out.println("YOU WON");
				break;
			} else 
				playChance(i+1);
		}

		if(!p2.isHasWon())
			System.out.println("SORRY YOU EXHAUSTED ALL CHANCES!! YOU LOST");
	}


	public void playChance(int chanceNo) {

		sc = new Scanner(System.in);
		System.out.println("Chance : "+chanceNo);
		System.out.println("Among BLUE(B), GREEN(G), YELLOW(Y), RED(R), CYAN(C), SILVER(S), PINK(P), NAVY(N) : \nEnter any 4 colors  :: \n");

		int count = 0; 
		Color[] inputColor = new Color[4];
		do {

			String input = sc.next();
			if(input.equalsIgnoreCase("RED") || input.equalsIgnoreCase("R")) {
				inputColor[count] = Color.RED;
				count++;
			}
			if(input.equalsIgnoreCase("GREEN") || input.equalsIgnoreCase("G")) {
				inputColor[count] = Color.GREEN;
				count++;
			}
			if(input.equalsIgnoreCase("YELLOW") || input.equalsIgnoreCase("Y")) {
				inputColor[count] = Color.YELLOW;
				count++;
			}
			if(input.equalsIgnoreCase("BLUE") || input.equalsIgnoreCase("B")) {
				inputColor[count] = Color.BLUE;
				count++;
			}
			if(input.equalsIgnoreCase("CYAN") || input.equalsIgnoreCase("C")) {
				inputColor[count] = Color.CYAN;
				count++;
			}
			if(input.equalsIgnoreCase("SILVER") || input.equalsIgnoreCase("S")) {
				inputColor[count] = Color.SILVER;
				count++;
			}
			if(input.equalsIgnoreCase("PINK") || input.equalsIgnoreCase("P")) {
				inputColor[count] = Color.PINK;
				count++;
			}
			if(input.equalsIgnoreCase("NAVY") || input.equalsIgnoreCase("N")) {
				inputColor[count] = Color.NAVY;
				count++;
			}

		}while(count<4);

		p2.setColorSequence(inputColor);		
		checkforWin();
	}


	private void checkforWin() {

		int white = 0, black = 0;
		boolean whiteMatchedIndex[] = new boolean[4];
		boolean blackMatchedIndex[] = new boolean[4];
		for(int i = 0; i < 4; i++) {
			if(p1.getColorSequence()[i] == p2.getColorSequence()[i]) {
				whiteMatchedIndex[i] = true;
				blackMatchedIndex[i] = false;
				white++;
			}
		}

		if(white == 4)
			p2.setHasWon(true);
		else {
			for(int i = 0; i < 4; i++) {
				if(whiteMatchedIndex[i])
					continue;
				for(int j = 0; j < 4; j++) {
					if(whiteMatchedIndex[j])
						continue;
					if(p2.getColorSequence()[i] == p1.getColorSequence()[j] && !blackMatchedIndex[j]) {
						blackMatchedIndex[j] = true;
						black++;
						break;
					}
				}
			}
		}
		
		System.out.println("HINT : Positions Matched = "+white+", Colors Matched = "+black);
		System.out.println("---------------------------------------------------------");
	}
}


