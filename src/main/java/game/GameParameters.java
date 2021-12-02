package game;

import logic.GameObject;

public class GameParameters {
	GameParameters(GameObject empty, GameObject enemy,
				   GameObject player, GameObject wall,
				   GameObject goal, int enemiesCount,
				   int wallsCount, int mapSize,
				   Game.Mode mode) {
		_empty = empty;
		_enemy = enemy;
		_player = player;
		_wall = wall;
		_goal = goal;
		_mapSize = mapSize;
		_enemiesCount = enemiesCount;
		_wallsCount = wallsCount;
		_mode = mode;
	}

	public GameObject	getEmpty() {
		return _empty;
	}
	public GameObject	getEnemy() {
		return new GameObject(_enemy.getChar(), _enemy.getColor(), _enemy.getType());
	}
	public GameObject	getPlayer() {
		return _player;
	}
	public GameObject	getWall() {
		return _wall;
	}
	public GameObject	getGoal() {
		return _goal;
	}
	public int 			getMapSize() {
		return _mapSize;
	}
	public int			getEnemiesCount() {
		return _enemiesCount;
	}
	public int			getWallsCount() {
		return _wallsCount;
	}
	public Game.Mode	getMode() {
		return _mode;
	}

	private final GameObject	_empty;
	private final GameObject	_enemy;
	private final GameObject	_player;
	private final GameObject	_wall;
	private final GameObject	_goal;
	private final int			_mapSize;
	private final int			_enemiesCount;
	private final int			_wallsCount;
	private final Game.Mode		_mode;
}
