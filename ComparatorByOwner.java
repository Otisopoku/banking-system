import java.util.Comparator;

/**
 * The `ComparatorByOwner` class is a custom comparator for comparing
 * `BankAccount` objects based on their owners' names.
 * It implements the `Comparator` interface to provide a comparison method.
 */
public class ComparatorByOwner implements Comparator<BankAccount> {

    /**
     * Compares two `BankAccount` objects based on the names of their owners.
     *
     * @param account1 The first `BankAccount` to compare.
     * @param account2 The second `BankAccount` to compare.
     * @return A positive value if the owner's name of `account1` is
     *         lexicographically greater than that of `account2`,
     *         a negative value if it is lexicographically less, or zero if they are
     *         equal.
     */
    @Override
    public int compare(BankAccount account1, BankAccount account2) {
        return account1.getOwner().compareTo(account2.getOwner());
    }
}
