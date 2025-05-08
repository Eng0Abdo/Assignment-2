import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.Serializable;
import java.util.List;



public class Database {

    private List<Account> accountList;
    private File databaseFile;

    public Database(String filePath) {
        this.databaseFile = new File(filePath);
        this.accountList = loadFromDatabase();
    }

    public boolean accountExists(String username, String password) {
        for (Account acc : accountList) {
            if (acc.getUsername().equals(username) && acc.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void createAccount(String username, String password) {
        if (!accountExists(username, password)) {
            Account newAccount = new Account(username, password, false);
            accountList.add(newAccount);
            saveToDatabase();
        }
    }

    public Account getAccount(String username, String password) {
        for (Account acc : accountList) {
            if (acc.getUsername().equals(username) && acc.getPassword().equals(password)) {
                return acc;
            }
        }
        return null;
    }

    private void saveToDatabase() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(databaseFile))) {
            oos.writeObject(accountList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Account> loadFromDatabase() {
        if (!databaseFile.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(databaseFile))) {
            return (List<Account>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
