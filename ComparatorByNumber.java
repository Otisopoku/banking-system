import java.util.Comparator;

/**
 * The `ComparatorByNumber` class is a custom comparator for comparing
 * `BankAccount` objects based on their account numbers.
 * It implements the `Comparator` interface to provide a comparison method.
 */
public class ComparatorByNumber implements Comparator<BankAccount> {

    /**
     * Compares two `BankAccount` objects based on their account numbers.
     *
     * @param account1 The first `BankAccount` to compare.
     * @param account2 The second `BankAccount` to compare.
     * @return 0 if the account numbers are equal, a positive value if the account
     *         number of `account1` is greater than `account2`,
     *         or a negative value if the account number of `account1` is less than
     *         `account2`.
     */
    @Override
    public int compare(BankAccount account1, BankAccount account2) {
        return (account1.getNumber() > account2.getNumber()) ? 1
                : (account1.getNumber() == account2.getNumber()) ? 0 : -1;
    }
}
