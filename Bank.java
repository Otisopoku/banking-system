import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

/** Bank class to manage BankAccounts */
public class Bank implements Closeable {

    /** Date fields */
    // private BankAccount[] accounts;

    private ArrayList<BankAccount> accounts;

    private int count;

    /** Default constructor */
    public Bank() {
        // accounts = new BankAccount[50];
        accounts = new ArrayList<>();
        count = 0;
    }

    /**
     * Initializes accounts to size 100
     * reads the accounts from file and stores the number of bank accounts in field
     * member count
     * 
     * @param filename
     */
    public Bank(String filename) {
        // accounts = new BankAccount[100];
        accounts = new ArrayList<>();
        readAccounts(filename);

    }

    // Big O notation: constant time O(1)
    /**
     * 
     * // * @return count
     * //
     */
    // private int getCount() {
    // return count;
    // }

    // Big O notation: constant time O(1)
    /**
     * 
     * @return BankAccount[]
     */
    public ArrayList<BankAccount> getBankAccounts() {
        return accounts;
    }

    // Big O notation: linear time complexity -- O(n)
    /**
     * Reads the Bank Accounts from a file; returns the number of BankAccount read
     * 
     * @param filename
     * @return int
     */
    private void readAccounts(String filename) {
        // int countAccounts = 0;

        File file = new File(filename);
        Scanner fileScanner = null;

        try {
            fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {

                /**
                 * reads and instantiates BankAccounts into the accounts array based on the type
                 */
                String[] lines = fileScanner.nextLine().split("\\|");
                if (lines[0].equals("Checking")) {
                    accounts.add(new Checking(Long.parseLong(lines[1]), lines[2],
                            Double.parseDouble(lines[3])));
                } else if (lines[0].equals("Savings")) {
                    accounts.add(new Savings(Long.parseLong(lines[1]), lines[2],
                            Double.parseDouble(lines[3]), Double.parseDouble(lines[4])));
                } else if (lines[0].equals("Investment")) {
                    accounts.add(new Investment(Long.parseLong(lines[1]), lines[2],
                            Double.parseDouble(lines[3]), lines[4]));
                }

            }

        } catch (FileNotFoundException e) {
            System.out.println("File is not found: " + e.getMessage());
        } finally {
            fileScanner.close();
        }

    }

    // Big O notation: Linear time complexity -- O(n)
    /**
     * Writes the existing BankAccounts into the file provided as the parameter
     * 
     * @param filename
     */
    public void saveAccount(String filename) {

        File file = new File(filename);
        PrintWriter fileWriter = null;
        try {
            fileWriter = new PrintWriter(file);
            for (int i = 0; i < count; i++) {
                if (accounts.get(i) instanceof Investment) {
                    Investment item = (Investment) accounts.get(i);
                    fileWriter.printf("%s|%d|%s|%.2f|%s%n", "Investment", accounts.get(i).getNumber(),
                            accounts.get(i).getOwner(), accounts.get(i).getBalance(), item.getType());
                } else if (accounts.get(i) instanceof Savings) {
                    Savings item = (Savings) accounts.get(i);
                    fileWriter.printf("%s|%d|%s|%.2f|%.2f%n", "Savings", accounts.get(i).getNumber(),
                            accounts.get(i).getOwner(), accounts.get(i).getBalance(), item.getMonthlyInterest());
                } else if (accounts.get(i) instanceof Checking) {

                    fileWriter.printf("%s|%d|%s|%.2f%n", "Checking", accounts.get(i).getNumber(),
                            accounts.get(i).getOwner(), accounts.get(i).getBalance());
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File cannot be written to");

        } finally {

            if (fileWriter != null) {
                fileWriter.close();
            }
        }

    }

    // Big O notation: constant time complexity -- O(1)
    /**
     * Adds the bankAccount to the accounts array
     * 
     * @param bankAccount
     */
    public void addAccount(BankAccount bankAccount) {
        accounts.add(bankAccount);
    }

    // Big O notation: linear time complexity -- O(n)
    /**
     * 
     * @param number
     * @return
     */
    public BankAccount findAccount(long number) {
        return findAccount(number, 0);
    }

    /**
     * Recursively searches for the BankAccount with the given account number and
     * returns it. If not found, null is returned
     * 
     * @param number
     * @param position
     * @return null if not BankAccount has the given account number, else returns
     *         the BankAccount with the given account number
     */
    private BankAccount findAccount(long number, int position) {
        if (position >= accounts.size()) {
            return null;
        } else if (accounts.get(position).getNumber() == number) {
            return accounts.get(position);
        } else {

            return findAccount(number, ++position);
        }

    }

    // Big O notation: quadratic time complexity -- O(n^2)
    public boolean removeAccount(long number) {
        return accounts.remove(findAccount(number));

    }

    // Big O notation: quadratic time complexity -- O(n^2)
    public void sortAccounts(Comparator<BankAccount> c) {
        // do something
        // java.util.Arrays.sort(accounts, 0, size());
        Utility.mergeSort(accounts, c);
    }

    // Big O notation: linear time -- O(n)
    public ArrayList<BankAccount> getClosableAccounts() {
        ArrayList<BankAccount> closeables = new ArrayList<>();
        for (BankAccount account : accounts) {
            if (account.isCloseable()) {
                closeables.add(account);
            }
        }

        return closeables;
    }

    // Big O notation: constant time O(1)
    public boolean isCloseable() {
        return (accounts.size() < 100 && getTotalFunds() <= 2000000) ? true : false;
    }

    // Big O notation: linear time complexity -- O(n)
    /**
     * 
     * @return returns the account balance
     */
    public double getTotalFunds() {
        double totalAccountsBalance = 0;

        for (BankAccount account : accounts) {
            totalAccountsBalance += account.getBalance();
        }
        return totalAccountsBalance;
    }

    // Big O notation : linear time -- O(n)
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (BankAccount account : accounts) {
            if (account != null) {
                sb.append(account.toString()).append("\n");
            }
        }
        return sb.toString();
    }

}
