import java.util.Comparator;

/**
 * The `ComparatorByBalance` class is a custom comparator for comparing
 * `BankAccount` objects based on their balance.
 * It implements the `Comparator` interface to provide a comparison method.
 */
public class ComparatorByBalance implements Comparator<BankAccount> {

    /**
     * Compares two `BankAccount` objects based on their balance.
     *
     * @param account1 The first `BankAccount` to compare.
     * @param account2 The second `BankAccount` to compare.
     * @return 0 if the balances are equal, a positive value if the balance of
     *         `account1` is greater than `account2`,
     *         or a negative value if the balance of `account1` is less than
     *         `account2`.
     */
    public int compare(BankAccount account1, BankAccount account2) {
        return (account1.getBalance() > account2.getBalance()) ? 1
                : (account1.getBalance() == account2.getBalance()) ? 0 : -1;
    }
}
