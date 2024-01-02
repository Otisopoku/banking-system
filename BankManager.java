
/**This class manages the BankAccounts and enable the user make several banking operations */

import java.util.ArrayList;
import java.util.Scanner;

// question: how do I make the format print 3.00 instead of 3.0
public class BankManager {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Bank bank = new Bank("accounts.txt");
        int operation;

        while (true) {
            viewOperations();
            operation = getValidOperation(input);
            switch (operation) {
                case 1:
                    /** Prints all Bank accounts */
                    printBankAccounts(bank);
                    break;
                case 2:

                    /** Allow for the user to perform banking operations */
                    System.out.println("Enter a valid account number: ");
                    long accountNumber = getValidNumber(input);
                    /**
                     * Try and catch block to handle an InvalidAccountNumber Exception if the user
                     * does not give number in the right format
                     */
                    try {

                        if (checkAccountNumber(accountNumber)) {

                            BankAccount foundAccount = bank.findAccount(accountNumber);
                            /** Performs operations if the input account number is exists */
                            if (foundAccount != null) {
                                System.out.println("Account found. Balance = $" + foundAccount.getBalance());
                                boolean loop = true;
                                while (loop) {
                                    /** Prints the operations, allowing for withdrawal, deposit, etc */
                                    System.out.println("Select an operation: ");
                                    System.out.println("1: Withdraw");
                                    System.out.println("2: Deposit");
                                    System.out.println("3: Monthly Interest");
                                    System.out.println("4: Investment profit/ loss");
                                    System.out.println("5: Return to main menu");

                                    // to get the choice of the user
                                    int choice = getValidOperation(input);

                                    /** Allows the user to perform operations displayed based on choice */
                                    switch (choice) {
                                        /**
                                         * Allows user to withdraw an amount. If amount to withdraw is more than
                                         * balance, an error message is displayed
                                         */
                                        case 1:
                                            System.out.println("Enter an amountToWithdraw: ");
                                            double amountToWithdraw = getValidAmount(input);

                                            if (foundAccount.withdraw(amountToWithdraw)) {
                                                System.out.println(
                                                        "Withdrawal successful. The new balance: $"
                                                                + foundAccount.getBalance());
                                                break;
                                            } else {
                                                System.out.println(
                                                        "Withdrawal failed. The available balance: $"
                                                                + foundAccount.getBalance());
                                                break;
                                            }

                                            /** Allows user to make a deposit; displays the balance to the user */
                                        case 2:
                                            System.out.println("Enter an amount to Deposite: ");
                                            double amountToDep = getValidAmount(input);
                                            foundAccount.deposit(amountToDep);
                                            System.out.println("Balance after deposite: $" + foundAccount.getBalance());
                                            break;

                                        /**
                                         * Allows user to see the monthly interest if the account is a savings account.
                                         * Otherwise, an error message is displayed
                                         */
                                        case 3:
                                            if (foundAccount instanceof Savings) {
                                                double monthlyInterest = ((Savings) foundAccount).getMonthlyInterest();
                                                System.out.println("Amount added to balance: $"
                                                        + String.format("%.2f. The new balance: $%.2f", monthlyInterest,
                                                                foundAccount.getBalance()));

                                            } else {
                                                System.out.println(
                                                        "Can not get the monthly interest. It is not a Savings account");
                                            }
                                            break;

                                        /**
                                         * Allows the user to see the investment gain or loss if the account is an
                                         * Investment account; otherwise, an error message is displayed
                                         */
                                        case 4:
                                            if (foundAccount instanceof Investment) {
                                                double profitOrLoss = ((Investment) foundAccount).getProfitOrLoss();
                                                System.out.println("Profit / Loss amount : $"
                                                        + String.format("%.2f. The new balance : $%.2f", profitOrLoss,
                                                                foundAccount.getBalance()));
                                            } else {
                                                System.out.println(
                                                        "Can not get the profit / loss. It is not an Investment account");
                                            }
                                            break;
                                        case 5:
                                            loop = false;

                                    }
                                }

                            } else {
                                /** Prints an error message if the account number does not exist */
                                System.out.println("Account Number does not exist");
                            }
                        }

                        break;
                    } catch (InvalidAccountNumber e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    /**
                     * Sort accounts by virtue of account balance --
                     * 
                     */
                    bank.sortAccounts(new ComparatorByBalance());
                    
                    break;
                case 4:
                    /** sort accounts by virtue of account owner */
                    bank.sortAccounts(new ComparatorByOwner());
                    break;
                case 5:
                    /** Sorts accounts by virtue of account number */
                    bank.sortAccounts(new ComparatorByNumber());
                    break;
                case 6:
                    /* Writes the bank accounts into a file */
                    bank.saveAccount("accounts.txt");
                    if (bank.isCloseable()) {
                        System.out.printf("Total funds = $%.2f%n", bank.getTotalFunds());
                        System.out.println("Number of customers = " + bank.getBankAccounts().size());
                        System.out.println("This bank is closeable");

                    }
                    System.exit(0);

            }
        }

    }

    /**
     * Checks to see if the user input is in the right format
     * 
     * @param accountNumber
     * @return boolean
     * @throws InvalidAccountNumber
     */
    public static boolean checkAccountNumber(long accountNumber) throws InvalidAccountNumber {
        String number = String.valueOf(accountNumber);

        if (number.matches("\\d{10}")) {
            return true;
        } else {
            throw new InvalidAccountNumber("Invalid Bank Account number (must be 10 digits)");
        }
    }

    /**
     * Prints out the Bank menu for the user to know the types of operations present
     */
    private static void viewOperations() {
        System.out.println("Select an operation: ");
        System.out.println("1: View accounts");
        System.out.println("2: Manage Account");
        System.out.println("3: Sort accounts by balance");
        System.out.println("4: Sort account by owner");
        System.out.println("5: Sort account by number");
        System.out.println("6: Exit");
    }

    /**
     * Validates input of user and reprompts, until a valid one is entered
     * 
     * @param input
     * @return
     */
    private static int getValidOperation(Scanner input) {
        // int option = 0;
        while (true) {
            if (input.hasNextInt()) {
                int option = input.nextInt();
                if (option != 1 && option != 2 && option != 3
                        && option != 4 && option != 5 && option != 6) {
                    System.out.println("Enter a valid choice: ");
                    input.nextLine();
                } else {
                    return option;

                }
            } else {
                input.nextLine();
                System.out.println("Enter an int");

            }
        }

    }

    /**
     * Prints all BankAccounts in Bank instance bank;
     * 
     * @param bank
     */
    private static void printBankAccounts(Bank bank) {
        System.out.println("Type \t\t Number \tOwner \t\t\t\tBalance \tInterest/Type");
        for (int i = 0; i < bank.getBankAccounts().size(); i++) {
            System.out.println(bank.getBankAccounts().get(i));
        }
    }

    /**
     * Prints all closeable bank accounts
     * 
     * @param bank
     */
    private static void printCloseableAccounts(Bank bank) {
        ArrayList<BankAccount> closeableAccounts = bank.getClosableAccounts();
        if (closeableAccounts.size() < 1) {
            System.out.println("There are no closable accounts ");
        } else {
            System.out.println("There are " + closeableAccounts.size() + " closeable accounts");
            for (BankAccount accounts : closeableAccounts) {
                System.out.println(accounts);
            }
        }

    }

    /**
     * Validates the input of user, until a non - zero digit is entered
     * 
     * @param Scanner(System.in)
     * @return
     */
    private static double getValidAmount(Scanner input) {
        while (true) {
            if (input.hasNextDouble()) {
                double option = input.nextDouble();
                if (option < 0) {
                    System.out.println("Enter a valid amount: ");
                    input.nextLine();
                } else {
                    return option;

                }
            } else {
                input.nextLine();
                System.out.println("Enter an int");

            }
        }
    }

    /**
     * Validates the input of the user until a digits are entered
     * 
     * @param Scanner(System.in)
     * @return long
     */
    private static long getValidNumber(Scanner input) {
        while (true) {
            if (input.hasNextLong()) {
                long number = input.nextLong();
                return number;
            } else {

                input.nextLine();
                System.out.println("Enter digits");

            }
        }
    }

}
