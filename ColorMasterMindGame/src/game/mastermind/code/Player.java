package game.mastermind.code;

public class Player {
	
	private boolean hasWon;
	private Color[] colorSequence;
	
	Player() {
		hasWon = false;
		colorSequence = new Color[4];
	}
	
	public boolean isHasWon() {
		return hasWon;
	}

	public void setHasWon(boolean hasWon) {
		this.hasWon = hasWon;
	}

	public Color[] getColorSequence() {
		return colorSequence;
	}

	public void setColorSequence(Color[] colorSequence) {
		this.colorSequence = colorSequence;
	}



}
