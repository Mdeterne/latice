package latice.console;

import java.util.Scanner;

public class LaticeConsole {

	public static void message(String message) {
		System.out.println(message);
	}
	
	public static String entrée(String message) {
		Scanner scanner = new Scanner(System.in);
		System.out.println(message);
		return scanner.nextLine();
	}
}
