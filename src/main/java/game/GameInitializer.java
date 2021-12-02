package game;

import logic.GameObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class GameInitializer {
	public static GameParameters initGameParameters(String[] args) throws Exception {
		GameParameters gameParameters;
		Arguments		arguments;
		Properties		properties;
		Game.Mode		mode;

		arguments = new Arguments();
		try {
			arguments.parse(args);
		} catch (Exception ex) {
			throw new IllegalParametersException(ex.getMessage());
		}
		if (arguments.getEnemiesCount() + arguments.getWallsCount() + 2 >=
				arguments.getSize() * arguments.getSize()) {
 			throw new IllegalParametersException("Illegal parameters");
		}

		if (arguments.getProfile().equals("dev")) {
			mode = Game.Mode.DEVELOPMENT;
		} else
			mode = Game.Mode.PRODUCTION;
		String	pathToConf = "/resources/application-" + arguments.getProfile() + ".properties";
		properties = parseConfigFile(pathToConf);

		String 		emptyChar;
		String 		enemyChar;
		String 		playerChar;
		String 		wallChar;
		String 		goalChar;

		if (properties.getProperty("empty.char").isEmpty()) {
			emptyChar = " ";
		} else {
			emptyChar = properties.getProperty("empty.char");
		}
		if (properties.getProperty("enemy.char").isEmpty()) {
			enemyChar = " ";
		} else {
			enemyChar = properties.getProperty("enemy.char");
		}
		if (properties.getProperty("player.char").isEmpty()) {
			playerChar = " ";
		} else {
			playerChar = properties.getProperty("player.char");
		}
		if (properties.getProperty("wall.char").isEmpty()) {
			wallChar = " ";
		} else {
			wallChar = properties.getProperty("wall.char");
		}
		if (properties.getProperty("goal.char").isEmpty()) {
			goalChar = " ";
		} else {
			goalChar = properties.getProperty("goal.char");
		}
		gameParameters = new GameParameters(
				new GameObject(emptyChar.charAt(0), properties.getProperty("empty.color"), GameObject.Type.EMPTY),
				new GameObject(enemyChar.charAt(0),  properties.getProperty("enemy.color"), GameObject.Type.ENEMY),
				new GameObject(playerChar.charAt(0),  properties.getProperty("player.color"), GameObject.Type.PLAYER),
				new GameObject(wallChar.charAt(0),  properties.getProperty("wall.color"), GameObject.Type.WALL),
				new GameObject(goalChar.charAt(0),  properties.getProperty("goal.color"), GameObject.Type.GOAL),
				arguments.getEnemiesCount(), arguments.getWallsCount(), arguments.getSize(),
				mode);
		return gameParameters;
	}

	private static Properties	parseConfigFile(String filePath) throws Exception {
		Properties	properties;


		properties = new Properties();
		InputStream	fileInputStream = Program.class.getResourceAsStream(filePath);
		if (fileInputStream == null) {
			throw new FileNotFoundException("File not found: " + filePath);
		}
		try {
			properties.load(fileInputStream);
		} catch (IOException ex) {
			fileInputStream.close();
			throw ex;
		}
		return properties;
	}
}
