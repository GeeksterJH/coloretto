package domain;

public class ColorCard extends Card {
	private Color color;

	public ColorCard(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	@Override
	public String toString() {
		return color.toString();
	}
}
