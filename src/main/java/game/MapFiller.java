package game;

import logic.GameObject;

import java.util.concurrent.ThreadLocalRandom;

public abstract class MapFiller {
	public static void fillMap(GameObject[][] map, GameParameters parameters) {

		for (int x = 0; x < parameters.getMapSize(); ++x) {
			for (int y = 0; y < parameters.getMapSize(); ++y) {
				map[x][y] = parameters.getEmpty();
			}
		}
		fillWalls(map, parameters);
		fillEnemies(map, parameters);
		fillPlayer(map, parameters);
		fillGoal(map, parameters);
	}

	private static void fillWalls(GameObject[][] map, GameParameters parameters) {
		int walls = parameters.getWallsCount();

		for (int i = 0; i < walls; ++i) {
			int x = ThreadLocalRandom.current().nextInt(0, parameters.getMapSize());
			int y = ThreadLocalRandom.current().nextInt(0, parameters.getMapSize());

			if (map[x][y].getType() == GameObject.Type.EMPTY)
				map[x][y] = parameters.getWall();
			else
				--i;
		}
	}

	private static void fillEnemies(GameObject[][] map, GameParameters parameters) {
		int enemies = parameters.getEnemiesCount();

		for (int i = 0; i < enemies; ++i) {
			int x = ThreadLocalRandom.current().nextInt(0, parameters.getMapSize());
			int y = ThreadLocalRandom.current().nextInt(0, parameters.getMapSize());

			if (map[x][y].getType() == GameObject.Type.EMPTY)
				map[x][y] = parameters.getEnemy();
			else
				--i;
		}
	}

	private static void fillPlayer(GameObject[][] map, GameParameters parameters) {
		boolean isSetPlayer = false;

		while (!isSetPlayer) {
			int x = ThreadLocalRandom.current().nextInt(0, parameters.getMapSize());
			int y = ThreadLocalRandom.current().nextInt(0, parameters.getMapSize());

			if (map[x][y].getType() == GameObject.Type.EMPTY) {
				map[x][y] = parameters.getPlayer();
				isSetPlayer = true;
			}
		}
	}

	private static void fillGoal(GameObject[][] map, GameParameters parameters) {
		boolean isSetGoal = false;

		while (!isSetGoal) {
			int x = ThreadLocalRandom.current().nextInt(0, parameters.getMapSize());
			int y = ThreadLocalRandom.current().nextInt(0, parameters.getMapSize());

			if (map[x][y].getType() == GameObject.Type.EMPTY) {
				map[x][y] = parameters.getGoal();
				isSetGoal = true;
			}
		}
	}
}
