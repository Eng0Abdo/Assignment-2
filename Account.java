import java.io.Serializable;

public class Account implements Serializable {

    private String username;
    private String password;
    private boolean isAdmin;
    private Portfolio portfolio;

    public Account(String username, String password, boolean isAdmin) {

        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        portfolio = new Portfolio();

    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public boolean isAdmin() { return isAdmin; }
    public Portfolio getPortfolio() { return portfolio; }
}
