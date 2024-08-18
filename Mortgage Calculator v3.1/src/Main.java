import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        float principal = readNumber("Principal ($1K – $1M)", 1_000, 1_000_000);
        float annualRate = readNumber("Annual Interest Rate", 1, 30);
        int periodInYears = (int) readNumber("Period in years", 1, 30);

        float mortgage = calculateMortgage(principal, annualRate, periodInYears);
        printMortgage(mortgage);
        printPaymentSchedule(principal, annualRate, periodInYears);
    }

    static final int MONTHS_IN_YEAR = 12;
    static final int PERCENT = 100;

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

    public static float calculateMortgage(float principal, float annualRate, int periodInYears) {
        int periodInMonths = periodInYears * MONTHS_IN_YEAR;
        float monthlyRate = annualRate / PERCENT / MONTHS_IN_YEAR;

        float mortgage = (float) ((principal * monthlyRate * Math.pow(1 + monthlyRate, periodInMonths)) /
                (Math.pow(1 + monthlyRate, periodInMonths) - 1));

        return mortgage;
    }

    private static float calculateRemainingMortgage(float principal, float monthlyRate, int periodInMonths, int month) {
        float remainingMortgage = (float)
                ((principal * (Math.pow(1 + monthlyRate, periodInMonths) - Math.pow(1 + monthlyRate, month))) /
                (Math.pow(1 + monthlyRate, periodInMonths) - 1));
        return remainingMortgage;
    }

    public static void printMortgage(float mortgage) {
        String formattedMortgage = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.println("Monthly Payments: " + formattedMortgage);
    }
    public static void printPaymentSchedule(float principal, float annualRate, int periodInYears) {
        int periodInMonths = periodInYears * MONTHS_IN_YEAR;
        float monthlyRate = annualRate / PERCENT / MONTHS_IN_YEAR;

        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        for (int month = 1; month <= periodInMonths; month++) {
            float remainingMortgage = calculateRemainingMortgage(principal, monthlyRate, periodInMonths, month);
            String formattedRemainingMortgage = NumberFormat.getCurrencyInstance().format(remainingMortgage);
            System.out.println(formattedRemainingMortgage);
        }
    }

}
