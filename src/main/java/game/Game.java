package game;

import logic.ChaseLogic;
import logic.GameObject;

public class Game {
	public enum Mode {
		DEVELOPMENT,
		PRODUCTION
	}

	private enum Status {
		LOSE,
		WIN,
		EXIT,
		BLOCK,
		OK,
		SKIP
	}

	private enum Commands {
		A(4),
		W(8),
		S(2),
		D(6),
		DEV(8),
		EXIT(9);

		Commands(int val) {
			value = val;
		}
		private final int	value;
	}

	public Game(String[] arguments) throws Exception {
		_parameters = GameInitializer.initGameParameters(arguments);
		_map = new GameObject[_parameters.getMapSize()][_parameters.getMapSize()];

		do {
			MapFiller.fillMap(_map, _parameters);
			findPlayer();
		} while (!MapChecker.checkMap(_map, _player));
		findEnemies();
		_drawer = new ConsoleDrawer(_parameters);
		_reader = new ConsoleReader();
	}

	private void	findPlayer() {
		for (int y = 0; y < _parameters.getMapSize(); y++) {
			for (int x = 0; x < _parameters.getMapSize(); x++) {
				if (_map[x][y].getType() == GameObject.Type.PLAYER) {
					_map[x][y].setX(x);
					_map[x][y].setY(y);
					_player = _map[x][y];
				}
			}
		}
	}

	private void	findEnemies() {
		int		index = 0;

		_enemies = new GameObject[_parameters.getEnemiesCount()];
		for (int y = 0; y < _parameters.getMapSize(); y++) {
			for (int x = 0; x < _parameters.getMapSize(); x++) {
				if (_map[x][y].getType() == GameObject.Type.ENEMY) {
					_enemies[index] = _map[x][y];
					_map[x][y].setX(x);
					_map[x][y].setY(y);
					++index;
				}
			}
		}
	}

	private Status	movePlayer(int x, int y) {
		GameObject.Type	type;

		try {
			type = _map[x][y].getType();
			if (type == GameObject.Type.WALL) {
				return Status.BLOCK;
			}
		} catch (Exception ignored) {
			return Status.BLOCK;
		}
		_map[_player.getX()][_player.getY()] = _parameters.getEmpty();
		_map[x][y] = _player;
		_player.setX(x);
		_player.setY(y);
		if (type == GameObject.Type.ENEMY) {
			return Status.LOSE;
		} else if (type == GameObject.Type.GOAL) {
			return Status.WIN;
		}
		return Status.OK;
	}

	private Status	moveEnemy(Integer x, Integer y, GameObject enemy) {
		GameObject.Type	type;

		type = _map[x][y].getType();
		_map[enemy.getX()][enemy.getY()] = _parameters.getEmpty();
		_map[x][y] = enemy;
		enemy.setX(x);
		enemy.setY(y);
		if (type == GameObject.Type.PLAYER)
			return Status.LOSE;
		return Status.OK;
	}

	private Status	enemiesMove() {
		Integer[]	moveCoordinates;
		int			input;

		for (GameObject enemy : _enemies) {
			if ((moveCoordinates = ChaseLogic.findEnemyPath(_map, enemy)) == null)
				continue;
			if (moveEnemy(moveCoordinates[0], moveCoordinates[1], enemy) == Status.LOSE) {
				return Status.LOSE;
			}
			if (_parameters.getMode() == Mode.DEVELOPMENT) {
				_drawer.drawMap(_map);
				System.out.println("Enemy move:");
				do {
					input = _reader.nextInt();
				} while (input != Commands.DEV.value && input != Commands.EXIT.value);
				if (input == Commands.EXIT.value) {
					return Status.EXIT;
				}
			}
		}
		return Status.OK;
	}

	private Status treatInput(int input) {
		int		x;
		int		y;

		if (input < 1 || input > 9)
			return Status.SKIP;
		if (input == Commands.EXIT.value) {
			return Status.EXIT;
		} else if (input == Commands.W.value) {
			x = _player.getX();
			y = _player.getY() - 1;
		} else if (input == Commands.A.value) {
			x = _player.getX() - 1;
			y = _player.getY();
		} else if (input == Commands.S.value) {
			x = _player.getX();
			y = _player.getY() + 1;
		} else if (input == Commands.D.value) {
			x = _player.getX() + 1;
			y = _player.getY();
		} else
			return Status.SKIP;
		return movePlayer(x, y);
	}

	public void 	start() {
		Status			status;

		while (true) {
			_drawer.drawMap(_map);

			status = treatInput(_reader.nextInt());
			if (status == Status.SKIP)
				continue;
			if (status == Status.OK) {
				status = enemiesMove();
			}
			if (status != Status.OK) {
				if (status == Status.LOSE) {
					_drawer.printLoseMessage();
				} else if (status == Status.WIN) {
					_drawer.printWinMessage();
				}
				if (status == Status.EXIT) {
					_drawer.printLoseMessage();
				}
				if (status != Status.BLOCK)
					break;
			}
		}
	}

	private final GameObject[][]	_map;
	private GameObject _player;
	private GameObject[]			_enemies;
	private final GameParameters _parameters;
	private final ConsoleReader		_reader;
	private final ConsoleDrawer		_drawer;
}
