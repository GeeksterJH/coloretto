package domain;

public enum Color {
	Orange,
	Blue,
	Brown,
	Yellow,
	Grey,
	Green,
	Pink;

	public static final int AMOUNT = values().length;

	public static Color get(int index) {
		return values()[index];
	}
}
