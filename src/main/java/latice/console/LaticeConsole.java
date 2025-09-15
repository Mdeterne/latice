package latice.console;

import java.util.Scanner;

public class LaticeConsole {

    private static final Scanner scanner = new Scanner(System.in);

    public static void message(String message) {
        System.out.println(message);
    }
    
    public static void messageSimple(String message) {
    	System.out.print(message);
    }

    public static String entrée(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }
}
