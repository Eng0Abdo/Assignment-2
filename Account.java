import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {
    private String username;
    private String password;
    private boolean isAdmin;
    private Portfolio portfolio;
    private List<BankAccount> linkedBankAccounts;

    public Account(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.portfolio = new Portfolio();
        this.linkedBankAccounts = new ArrayList<>();
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public boolean isAdmin() { return isAdmin; }
    public Portfolio getPortfolio() { return portfolio; }

    public void linkBankAccount(BankAccount bankAccount) {
        if(bankAccount.validateCard()) {
            this.linkedBankAccounts.add(bankAccount);
            System.out.println("Bank account linked successfully!");
        } else {
            System.out.println("Invalid bank account details!");
        }
    }

    public List<BankAccount> getLinkedBankAccounts() {
        return linkedBankAccounts;
    }

    public void displayLinkedBankAccounts() {
        System.out.println("\nLinked Bank Accounts for " + username + ":");
        for(int i = 0; i < linkedBankAccounts.size(); i++) {
            BankAccount acc = linkedBankAccounts.get(i);
            System.out.println((i+1) + ". " + acc.getBankName() +
                    " - Card ending with: " + acc.getCardNumber().substring(12));
        }
    }
}
