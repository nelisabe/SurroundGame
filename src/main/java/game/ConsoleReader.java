package game;

import java.util.Scanner;

public class ConsoleReader {
	public ConsoleReader() {
		scanner = new Scanner(System.in);
	}

	public int			nextInt() {
		String 	input;
		int		result;

		while (true) {
			input = scanner.nextLine();
			if (input.equals("\n"))
				continue;
			try {
				result = Integer.parseInt(input);
				clearInput(input.length());
				break;
			} catch (Exception ignored) { }
			clearInput(input.length());
		}
		return result;
	}

	public static void	clearInput(int count) {
		System.out.print("\033[F");
		for (int i = 0; i < count; ++i) {
			System.out.print(' ');
		}
		System.out.print("\033[" + count + "D");
	}

	private final Scanner	scanner;
}
