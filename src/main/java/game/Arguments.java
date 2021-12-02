package game;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class Arguments {
	@Parameter(names = "--enemiesCount", required = true, arity = 1)
	private int		enemiesCount;

	@Parameter(names = "--wallsCount", required = true, arity = 1)
	private int		wallsCount;

	@Parameter(names = "--size", required = true, arity = 1)
	private int		size;

	@Parameter(names = "--profile", required = true, arity = 1)
	private String	profile;

	public void		parse(String[] args) {
		JCommander.newBuilder().addObject(this).build().parse(args);
	}

	public int		getEnemiesCount() {
		return enemiesCount;
	}
	public int 		getWallsCount() {
		return wallsCount;
	}
	public int 		getSize() {
		return size;
	}
	public String	getProfile() {
		return profile;
	}
}
