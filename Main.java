public class Main {
    public static void main(String[] args) {
        Database db = new Database("accounts.dat");



        // Optional: test retrieval
        Account acc = db.getAccount("user1", "pass1");
        if (acc != null) {
            System.out.println("Retrieved: " + acc.getUsername());
        } else {
            System.out.println("Account not found.");
        }
    }
}
