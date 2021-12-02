package logic;

public class GameObject {
	public enum	Type {
		EMPTY,
		ENEMY,
		PLAYER,
		WALL,
		GOAL
	}

	public GameObject(char objectChar, String objectColor, Type objectType) {
		_objectChar = objectChar;
		_objectColor = objectColor;
		x = 0;
		y = 0;
		_type = objectType;
	}

	public char		getChar() {
		return _objectChar;
	}

	public String	getColor() {
		return _objectColor;
	}

	public int		getX() {
		return x;
	}
	public void 	setX(int newX) {
		x = newX;
	}

	public int		getY() {
		return y;
	}
	public void		setY(int newY) {
		y = newY;
	}

	public Type		getType() {
		return _type;
	}

	private final char		_objectChar;
	private final String	_objectColor;
	private int				x;
	private int				y;
	private final Type		_type;
}
