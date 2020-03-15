package domain;

public class ColorCard extends Card {
	public ColorCard(Color color) {
		super(color);
	}
	private Color color;

	public void Card(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
}
