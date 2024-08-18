import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int MONTHS_IN_YEAR = 12;
        final int PERCENT = 100;
        final float MIN_PRINCIPAL = 1_000;
        final float MAX_PRINCIPAL = 1_000_000;
        final float MIN_ANNUAL_RATE = 0;
        final float MAX_ANNUAL_RATE = 30;
        final int MIN_YEARS = 0;
        final int MAX_YEARS = 30;
        float principal;
        float annualRate;
        float monthlyRate;
        int periodInYears;
        int periodInMonths;
        float mortgage;
        String formattedMortgage;

        while (true) {
            System.out.print("Principal ($1K – $1M): ");
            principal = new Scanner(System.in).nextFloat();
            if (principal >= MIN_PRINCIPAL && principal <= MAX_PRINCIPAL) break;
            System.out.println("Enter a principal between 1,000 and 1,000,000!");
        }

        while (true) {
            System.out.print("Annual Interest Rate: ");
            annualRate = new Scanner(System.in).nextFloat();
            if (annualRate > MIN_ANNUAL_RATE && annualRate <= MAX_ANNUAL_RATE) {
                monthlyRate = annualRate / PERCENT / MONTHS_IN_YEAR;
                break;
            }
            System.out.println("Enter an annual interest rate greater than 0 and less than or equal to 30!");
        }

        while (true) {
            try {
                System.out.print("Period in years: ");
                periodInYears = new Scanner(System.in).nextInt();
                if (periodInYears > MIN_YEARS && periodInYears <= MAX_YEARS) {
                    periodInMonths = periodInYears * MONTHS_IN_YEAR;
                    break;
                }
                System.out.println("Enter a number of years greater than 0 and less than or equal to 30!");
            }
            catch (Exception e) {
                System.out.println("Enter a number of years greater than 0 and less than or equal to 30!");
            }
        }

        mortgage = (float) ((principal * monthlyRate * Math.pow(1 + monthlyRate, periodInMonths)) /
                (Math.pow(1 + monthlyRate, periodInMonths) - 1));

        formattedMortgage = NumberFormat.getCurrencyInstance().format(mortgage);

        System.out.println("Mortgage: " + formattedMortgage);
    }
}
