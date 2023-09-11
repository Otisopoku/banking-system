/** Investment class that extends the BankAccount class. Has data field type */

public class Investment extends BankAccount {

    /** Data field */
    private String type;

    /**
     * Four argument constructor
     * 
     * @param number
     * @param owner
     * @param balance
     * @param type
     */
    public Investment(long number, String owner, double balance, String type) {
        super(number, owner, balance);
        this.type = type;
    }

    /**
     * Getter for field type
     * 
     * @return String
     */
    public String getType() {
        return this.type;
    }

    /**
     * Setter for field type
     * 
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Calculates for either the profit of loss depending on the value of a random
     * number. Returns the signed value of either profit or loss
     * 
     * @return double
     */
    public double getProfitOrLoss() {
        double profitOrLoss = 0.05 * this.balance; // profit
        double probability = Math.random();

        if (probability < 0.5) {
            this.balance = this.balance - profitOrLoss;
            return profitOrLoss * -1;
        } else {
            this.balance = this.balance + profitOrLoss;
            return profitOrLoss;
        }
    }

    @Override
    public String toString() {
        return "Investment\t" + super.toString() + String.format("\t%-10s", type);
    }
}
