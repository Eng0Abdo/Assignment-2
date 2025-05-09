public class Main {
    public static void main(String[] args) {

        Database db = new Database("accounts.db");
        BankAccountDatabase bankDatabase = new BankAccountDatabase("bank.db");

        Screen screen = new Screen(db, bankDatabase);
        Application app = new Application(screen);
        app.run();
    }
}
