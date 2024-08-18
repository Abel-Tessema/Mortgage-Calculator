import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        float principal = 0;
        float annualInterestRate = 0;
        float monthlyInterestRate = 0;
        byte periodInYears = 0;
        short periodInMonths = 0;
        float mortgage = 0;
        String formattedMortgage;

        System.out.print("Principal: ");
        principal = new Scanner(System.in).nextFloat();

        System.out.print("Annual Interest Rate (Percent): ");
        annualInterestRate = new Scanner(System.in).nextFloat() / 100;
        monthlyInterestRate = annualInterestRate / 12;

        System.out.print("Period (Years): ");
        periodInYears = new Scanner(System.in).nextByte();
        periodInMonths = (short) (periodInYears * 12);

        mortgage = (float) ((principal * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, periodInMonths)) /
                        (Math.pow(1 + monthlyInterestRate, periodInMonths) - 1));

        formattedMortgage = NumberFormat.getCurrencyInstance().format(mortgage);

        System.out.println("Mortgage: " + formattedMortgage);
    }
}