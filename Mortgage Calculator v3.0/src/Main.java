import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        float principal = readNumber("Principal ($1K – $1M)", 1_000, 1_000_000);
        float annualRate = readNumber("Annual Interest Rate", 1, 30);
        int periodInYears = (int) readNumber("Period in years", 1, 30);

        String mortgage = calculateMortgage(principal, annualRate, periodInYears);

        System.out.println("Mortgage: " + mortgage);
    }

    public static float readNumber(String prompt, float min, float max) {
        String formattedMin = NumberFormat.getIntegerInstance().format(min);
        String formattedMax = NumberFormat.getIntegerInstance().format(max);
        float value;
        while (true) {
            try {
                System.out.print(prompt + ": ");
                value = new Scanner(System.in).nextFloat();
                if (value >= min && value <= max) break;
                System.out.println("Enter a value between " + formattedMin + " and " + formattedMax + "!");
            }
            catch (Exception e) {
                System.out.println("Enter a value between " + formattedMin + " and " + formattedMax + "!");
            }
        }
        return value;
    }

    public static String calculateMortgage(float principal, float annualRate, int periodInYears) {
        final int MONTHS_IN_YEAR = 12;
        final int PERCENT = 100;

        int periodInMonths = periodInYears * MONTHS_IN_YEAR;
        float monthlyRate = annualRate / PERCENT / MONTHS_IN_YEAR;

        float mortgage = (float) ((principal * monthlyRate * Math.pow(1 + monthlyRate, periodInMonths)) /
                (Math.pow(1 + monthlyRate, periodInMonths) - 1));

        String formattedMortgage = NumberFormat.getCurrencyInstance().format(mortgage);
        return formattedMortgage;
    }
}
