public class Screen {
    private boolean loggedIn;
    private Database database;
    private Account currentAccount;

    public Screen(Database database) {
        this.database = database;
        loggedIn = false;
        currentAccount = null;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }


    public boolean logIn(String username, String password){
        if(database.accountExists(username,password)){
            loggedIn = true;
            currentAccount = database.getAccount(username,password);
            return true;
        } else {
            System.out.println("Incorrect username or password\n");
            return false;
        }


    }

    public boolean signUp(String username, String password, String confirmPassword){
        if(!password.equals(confirmPassword)){
            System.out.println("Passwords do not match\n");
            return false;
        }
        if(database.accountExists(username,password)){
            System.out.println("Account already exists\n");
            return false;
        }

        database.createAccount(username, password);
        System.out.println("Signed up successfully\n");
        return true;

    }

    public void logOut(){
        loggedIn = false;
        currentAccount = null;


    }

    Account getCurrentAccount(){
        return currentAccount;
    }

    public Database getDatabase(){
        return database;
    }


}
