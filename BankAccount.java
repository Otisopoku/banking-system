/**
 * Abstract class from which other classes (Checking.java, Savings.java,
 * Investment.java)
 */

public abstract class BankAccount {

    /** Data fields */
    private long number;
    private String owner;
    protected double balance;

    /**
     * Three argument constructor
     * 
     * @param number,  the bank number individual
     * @param owner,   owner of the account
     * @param balance, balance in account of owner
     */
    public BankAccount(long number, String owner, double balance) {
        this.number = number;
        this.owner = owner;
        this.balance = balance;
    }

    /**
     * Getter for field number
     * 
     * @return number
     */
    public long getNumber() {
        return number;
    }

    /**
     * Getter for field owner
     * 
     * @return owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Getter for field balance
     * 
     * @return balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Setter for number
     * 
     * @param number
     */
    public void setNumber(long number) {
        this.number = number;
    }

    /**
     * Enables owner to add to his balance
     * 
     * @param amount
     */
    public void deposit(double amount) {
        this.balance = balance + amount;
    }

    /**
     * returns true if amount was withdrawn successfully, false otherwise
     * 
     * @param amount
     * @return boolean
     */
    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false;
        }
        this.balance = this.balance - amount;
        return true;
    }

    /**
     * returns the fields of the class
     * 
     * @return String
     */
    @Override
    public String toString() {
        return String.format("%-10d\t%-30s\t$%-10.2f",
                number, owner, balance);
    }

}