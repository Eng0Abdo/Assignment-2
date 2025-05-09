import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BankAccountDatabase implements Serializable{

    private List<BankAccount> bankAccountList;
    private File bankDatabaseFile;

    public BankAccountDatabase(String filePath) {
        this.bankDatabaseFile = new File(filePath);
        this.bankAccountList = loadFromDatabase();
    }

    public boolean accountExists(String cardNumber) {
        for (BankAccount acc : bankAccountList) {
            if (acc.getCardNumber().equals(cardNumber)) {
                return true;
            }
        }
        return false;
    }

    public void addBankAccount(BankAccount account) {
        if (!accountExists(account.getCardNumber())) {
            bankAccountList.add(account);
            saveToDatabase();
        }
    }

    public BankAccount getBankAccount(String cardNumber) {
        for (BankAccount acc : bankAccountList) {
            if (acc.getCardNumber().equals(cardNumber)) {
                return acc;
            }
        }
        return null;
    }

    private void saveToDatabase() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(bankDatabaseFile))) {
            oos.writeObject(bankAccountList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<BankAccount> loadFromDatabase() {
        if (!bankDatabaseFile.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(bankDatabaseFile))) {
            return (List<BankAccount>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void printBankAccounts() {
        for (BankAccount acc : bankAccountList) {
            System.out.println(acc.getBankName() + " - " + acc.getCardNumber());
        }
    }
}
