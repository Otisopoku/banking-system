/**
 * Savings class that extends BankAccount class. Has private field
 * yearlyInterestRate
 */

public class Savings extends BankAccount {

    /** Data fields */
    private double yearlyInterestRate;

    /**
     * Four argument constructor
     * 
     * @param number
     * @param owner
     * @param balance
     * @param yearlyInterestRate
     */
    public Savings(long number, String owner, double balance, double yearlyInterestRate) {
        super(number, owner, balance);
        this.yearlyInterestRate = yearlyInterestRate;
    }

    /**
     * Getter for the yearly Interest
     * 
     * @return double
     */
    public double getYearlyInterest() {
        return yearlyInterestRate;
    }

    /**
     * Getter for the MonthlyInterest
     * 
     * @return double
     */
    public double getMonthlyInterest() {
        double monthlyInterest = ((yearlyInterestRate / 12) / 100) * balance;
        this.balance += monthlyInterest;
        return monthlyInterest;
    }

    /**
     * Setter for the yearly interest
     * 
     * @param yearlyInterestRate
     */
    public void setYearlyInterest(double yearlyInterestRate) {
        this.yearlyInterestRate = yearlyInterestRate;
    }

    @Override
    public String toString() {
        return "Savings\t\t" + super.toString() + String.format("\t%-10.2f", this.yearlyInterestRate);
    }

}
