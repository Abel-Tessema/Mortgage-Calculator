import java.text.NumberFormat;
import java.util.Scanner;

/**
 * Reads numbers from the console.
 * It has two overloaded methods named 'readNumber'.
 * The first one asks only for a prompt, while the second demands a minimum and maximum value for the input of the prompt.
 */
public class Console {
    private static final Scanner scanner = new Scanner(System.in);
    private static float value;
    private static String input;
    public static float readNumber(String prompt) {
        input = scanner.next();
        value = Float.parseFloat(input);
        return value;
    }
    public static float readNumber(String prompt, float min, float max) {
        String formattedMin = NumberFormat.getIntegerInstance().format(min);
        String formattedMax = NumberFormat.getIntegerInstance().format(max);
        while (true) {
            try {
                System.out.print(prompt + ": ");
                input = scanner.next();
                value = Float.parseFloat(input);
                if (value >= min && value <= max) break;
                System.out.println("Enter a value between " + formattedMin + " and " + formattedMax + "!");
            } catch (Exception e) {
                System.out.println("Enter a value between " + formattedMin + " and " + formattedMax + "!");
            }
        }
        return value;
    }
}
