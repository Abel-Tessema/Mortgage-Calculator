public class Main {

    public static void main(String[] args) {
        float principal = Console.readNumber("Principal ($1K – $1M)", 1_000, 1_000_000);
        float annualRate = Console.readNumber("Annual Interest Rate", 1, 30);
        byte periodInYears = (byte) Console.readNumber("Period in years", 1, 30);

        var calculator = new MortgageCalculator(principal, annualRate, periodInYears);

        var report = new MortgageReport(calculator);
        report.printMortgage();
        report.printPaymentSchedule();
    }

}
