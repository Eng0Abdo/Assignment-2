/**
 * Manages user authentication, session state, and bank account linking.
 * Acts as the facade between Application and Database layers.
 *
 * @author Abdelrahman Emad, Mahmoud Mohamed, Peter Gerges
 * @version 1.0
 */
public class Screen {
    /**
     * Initializes the Screen with required databases
     *
     * @param database User accounts database
     * @param bankDatabase Bank accounts database
     */

    private boolean loggedIn;
    private Database database;
    private BankAccountDatabase bankDatabase;
    private Account currentAccount;

    public Screen(Database database, BankAccountDatabase bankDatabase) {
        this.database = database;
        loggedIn = false;
        currentAccount = null;
        this.bankDatabase = bankDatabase;
    }



    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Authenticates user credentials
     *
     * @param username User's unique identifier
     * @param password Plaintext password (will be hashed)
     * @return true if authentication succeeds
     * @throws SecurityException if account is locked
     */

    public boolean logIn(String username, String password) {
        if (database.accountExists(username, password)) {
            loggedIn = true;
            currentAccount = database.getAccount(username, password);
            return true;
        } else {
            System.out.println("Incorrect username or password\n");
            return false;
        }


    }

    public boolean signUp(String username, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match\n");
            return false;
        }
        if (database.accountExists(username, password)) {
            System.out.println("Account already exists\n");
            return false;
        }

        database.createAccount(username, password);
        System.out.println("Signed up successfully\n");
        return true;

    }

    public void logOut() {
        loggedIn = false;
        currentAccount = null;


    }

    Account getCurrentAccount() {
        return currentAccount;
    }

    public Database getDatabase() {
        return database;
    }

    public BankAccountDatabase getBankDatabase() {
        return bankDatabase;
    }


}
