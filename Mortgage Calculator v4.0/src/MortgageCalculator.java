public class MortgageCalculator {
    private static final byte MONTHS_IN_YEAR = 12;
    private static final byte PERCENT = 100;

    private final float principal;
    private final float annualRate;
    private final byte periodInYears;

    public MortgageCalculator(float principal, float annualRate, byte periodInYears) {
        this.principal = principal;
        this.annualRate = annualRate;
        this.periodInYears = periodInYears;
    }

    public float calculateBalance(int month) {
        int periodInMonths = getPeriodInMonths();
        float monthlyRate = getMonthlyRate();

        float remainingMortgage = (float)
                ((principal * (Math.pow(1 + monthlyRate, periodInMonths) - Math.pow(1 + monthlyRate, month))) /
                        (Math.pow(1 + monthlyRate, periodInMonths) - 1));
        return remainingMortgage;
    }

    public float calculateMortgage() {
        int periodInMonths = getPeriodInMonths();
        float monthlyRate = getMonthlyRate();

        float mortgage = (float) ((principal * monthlyRate * Math.pow(1 + monthlyRate, periodInMonths)) /
                (Math.pow(1 + monthlyRate, periodInMonths) - 1));

        return mortgage;
    }

    public float[] getRemainingBalances() {
        var balances = new float[getPeriodInMonths()];
        for (int month = 1; month <= getPeriodInMonths(); month++)
            balances[month - 1] = calculateBalance(month);

        return balances;
    }

    private float getMonthlyRate() {
        return annualRate / PERCENT / MONTHS_IN_YEAR;
    }

    private int getPeriodInMonths() {
        return periodInYears * MONTHS_IN_YEAR;
    }
}
