package domain;

public enum Color {
	Orange,
	Blue,
	Brown,
	Yellow,
	Grey,
	Green,
	Pink;

	public static final int AMOUNT = 7;

	public static Color get(int index) {
		switch (index) {
			case 1: return Orange;
			case 2: return Blue;
			case 3: return Brown;
			case 4: return Yellow;
			case 5: return Grey;
			case 6: return Green;
			default: return Pink;
		}
	}

	public String toString() {
		switch (this) {
			case Orange: return "Orange";
			case Blue: return "Blue";
			case Brown: return "Brown";
			case Yellow: return "Yellow";
			case Grey: return "Grey";
			case Green: return "Green";
			case Pink: return "Pink";
		}

		return "";
	}
}
