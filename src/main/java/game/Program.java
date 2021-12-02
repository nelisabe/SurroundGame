package game;

public class Program {
	public static void	main(String[] args) {
		Game			game;

		try {
			game = new Game(args);
		} catch (IllegalParametersException ex) {
			throw ex;
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			return;
		}
		game.start();
	}
}
