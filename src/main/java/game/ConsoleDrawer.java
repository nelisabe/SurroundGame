package game;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;
import logic.GameObject;

public class ConsoleDrawer {
	public ConsoleDrawer(GameParameters parameters) {
		_parameters = parameters;
		_mode = _parameters.getMode();
		_printer = new ColoredPrinter();
	}

	private void	goBackToStart() {
		for (int i = 0; i < _parameters.getMapSize() + 1; ++i) {
			System.out.print("\033[F");
		}
		System.out.print("\r");
	}

	public void 	drawMap(GameObject[][] map) {
		System.out.print(System.lineSeparator());
		for (int y = 0; y < _parameters.getMapSize(); ++y) {
			for (int x = 0; x < _parameters.getMapSize(); ++x) {
				 _printer.print(map[x][y].getChar(),
						 Ansi.Attribute.BOLD,
						 Ansi.FColor.valueOf("BLACK"),
						 Ansi.BColor.valueOf(map[x][y].getColor()));
			}
			System.out.print(System.lineSeparator());
		}
		if (_mode == Game.Mode.PRODUCTION) {
			goBackToStart();
		}
	}

	public void		printWinMessage() {
		_printer.print("You win!",
				Ansi.Attribute.BLINK,
				Ansi.FColor.valueOf("GREEN"),
				Ansi.BColor.valueOf("BLACK"));
		System.out.print(System.lineSeparator());
		System.out.print(System.lineSeparator());
		System.out.print("\033[F");
	}

	public void		printLoseMessage() {
		_printer.print("You lose!",
				Ansi.Attribute.BLINK,
				Ansi.FColor.valueOf("RED"),
				Ansi.BColor.valueOf("BLACK"));
		System.out.print(System.lineSeparator());
		System.out.print(System.lineSeparator());
		System.out.print("\033[F");
	}

	private final GameParameters _parameters;
	private final Game.Mode			_mode;
	private final ColoredPrinter	_printer;
}
