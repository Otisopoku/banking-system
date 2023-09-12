/** Class that extends the Exeption class; Checks for invalid account number */

public class InvalidAccountNumber extends Exception {

    /** Default contructor */
    public InvalidAccountNumber() {
        super();
    }

    /**
     * One argument constructor
     * 
     * @param message
     */
    public InvalidAccountNumber(String message) {
        super(message);
    }

}
