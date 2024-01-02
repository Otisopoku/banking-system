/** Checking class that extends BankAccount class */

public class Checking extends BankAccount {

    /**
     * three argument constructor
     * 
     * @param number
     * @param owner
     * @param balance
     */
    public Checking(long number, String owner, double balance) {
        super(number, owner, balance);
    }

    @Override
    public String toString() {
        return "Checking\t" + super.toString();
    }
}
