import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {
    Screen screen;
    Scanner sc;

    public Application(Screen screen) {
        this.screen = screen;
        sc = new Scanner(System.in);
    }

    public void run(){
        while (true){
            int choice = getChoice();
            switch (choice) {
                case 1:
                    signup();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    createBankAccount();
                    break;
                default:
                    break;

            }

        }
    }

    private int getChoice() {
        int choice = -1;

        System.out.println("\n--- Welcome to the Investment App ---");
        System.out.println("1. Sign Up");
        System.out.println("2. Log In");
        System.out.println("3. Create Bank Account");
        System.out.print("Please enter your choice: ");

        try {
            choice = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            sc.nextLine();
        }

        return choice;
    }


    private int getPage() {

        int page = -1;

        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Go to Dashboard");
        System.out.println("2. Link bank account");
        System.out.println("3. Display linked bank accounts");
        System.out.println("4. Log Out");
        System.out.print("Select an option: ");

        try {
            page = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            sc.nextLine();
        }

        return page;
    }


    private int getService() {
        int choice = -1;

        System.out.println("\n--- Dashboard Menu ---");
        System.out.println("1. List Assets");
        System.out.println("2. Add Asset");
        System.out.println("3. Edit Asset");
        System.out.println("4. Remove Asset");
        System.out.println("5. Zakat Calculation");

        System.out.print("Select a service (-1 to quit): ");

        try {
            choice = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            sc.nextLine();
        }

        return choice;
    }


    private void signup() {
        sc.nextLine();
        System.out.println("\n--- Create a New Account ---");
        System.out.print("Enter a username: ");
        String username = sc.nextLine();

        System.out.print("Enter a password: ");
        String password = sc.nextLine();

        System.out.print("Confirm your password: ");
        String confirmPassword = sc.nextLine();

        screen.signUp(username, password, confirmPassword);
    }


    private void login() {
        sc.nextLine();
        System.out.println("\n--- Log In ---\n");
        System.out.print("Enter your username: ");
        String username = sc.nextLine();

        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        screen.logIn(username, password);

        while (screen.isLoggedIn()) {
            int page = getPage();
            switch (page) {
                case 1:
                    dashboardPanel();
                    break;
                case 2:
                    linkBankAccount();
                    break;
                case 3:
                    displayLinkedBankAccounts();
                    break;
                case 4:
                    screen.logOut();
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }


    private void dashboardPanel(){

        int service = getService();
        while (service != -1){
            Portfolio portfolio = screen.getCurrentAccount().getPortfolio();

            switch (service){
                case 1:
                    portfolio.listAssets();
                    break;
                case 2:
                    addAsset(portfolio);
                    break;
                case 3:
                    editAsset(portfolio);
                    break;
                case 4:
                    removeAsset(portfolio);
                    break;
                case 5:
                    ZakatCalculation(portfolio);
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
                    break;
            }
            Database db = screen.getDatabase();
            db.saveToDatabase();
            service = getService();

        }


    }

    public void displayLinkedBankAccounts(){
        screen.getCurrentAccount().displayLinkedBankAccounts();
    }

    public void linkBankAccount() {
        sc.nextLine();
        System.out.print("Enter your card number: ");
        String cardNumber = sc.nextLine();

        BankAccount account = screen.getBankDatabase().getBankAccount(cardNumber);

        if (account == null) {
            System.out.println("Bank account not found in the database!");
            return;
        }
        for (BankAccount linked : screen.getCurrentAccount().getLinkedBankAccounts()) {
            if (linked.getCardNumber().equals(cardNumber)) {
                System.out.println("This bank account is already linked.");
                return;
            }
        }

        System.out.print("Enter the cardholder name: ");
        String name = sc.nextLine();

        System.out.print("Enter CVV: ");
        String cvv = sc.nextLine();

        if (account.getCardHolderName().equalsIgnoreCase(name.trim()) &&
                account.getCvv().equals(cvv.trim())) {

            screen.getCurrentAccount().linkBankAccount(account);
        } else {
            System.out.println("Verification failed! Provided details do not match the bank account.");
        }
    }



    public void ZakatCalculation(Portfolio portfolio) {
        System.out.println("\nZakat Calculation: ");
        System.out.println(portfolio.calculateZakat());
    }

    private void addAsset(Portfolio portfolio){
        sc.nextLine();
        System.out.println("\n--- Add New Asset ---");

        System.out.print("Enter asset name: ");
        String assetName = sc.nextLine();

        System.out.print("Enter purchase price: ");
        double purchasePrice = Double.parseDouble(sc.nextLine());

        System.out.print("Enter current value: ");
        double currentValue = Double.parseDouble(sc.nextLine());

        System.out.print("Enter asset type (e.g., 'Stock', 'Real Estate'): ");
        String type = sc.nextLine();

        System.out.print("Enter asset Amount: ");
        double amount = Double.parseDouble(sc.nextLine());

        portfolio.addAsset(assetName, purchasePrice, currentValue, type, amount);
        System.out.println("Asset added successfully!");

    }

    private void editAsset(Portfolio portfolio) {
        sc.nextLine();
        System.out.println("\n--- Edit Asset ---");

        System.out.print("Enter the Asset ID to edit: ");
        int assetID;
        try {
            assetID = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid asset ID. Operation cancelled.");
            return;
        }

        if (!portfolio.assetExists(assetID)) {
            System.out.println("No asset found with ID " + assetID);
            return;
        }

        System.out.print("Enter new asset name: ");
        String assetName = sc.nextLine();

        System.out.print("Enter new purchase price: ");
        double purchasePrice;
        try {
            purchasePrice = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid purchase price. Operation cancelled.");
            return;
        }

        System.out.print("Enter new current value: ");
        double currentValue;
        try {
            currentValue = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid current value. Operation cancelled.");
            return;
        }

        System.out.print("Enter new asset type: ");
        String type = sc.nextLine();

        System.out.print("Enter new Asset Amount: ");
        double newAmount;
        try {
            newAmount = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Operation cancelled.");
            return;
        }

        portfolio.editAsset(assetName, purchasePrice, currentValue, type, assetID, newAmount);
        System.out.println("Asset updated successfully.");
    }

    private void removeAsset(Portfolio portfolio) {

        sc.nextLine();
        System.out.println("\n--- Remove Asset ---");

        System.out.print("Enter the Asset ID to remove: ");
        int assetID;
        try {
            assetID = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Asset ID must be a number.");
            return;
        }

        if (!portfolio.assetExists(assetID)) {
            System.out.println("No asset found with ID " + assetID);
            return;
        }

        portfolio.removeAsset(assetID);
        System.out.println("Asset removed successfully.");
    }

    private void createBankAccount() {
        sc.nextLine();
        System.out.print("Enter bank name: ");
        String bankName = sc.nextLine();

        System.out.print("Enter card number (16 digits): ");
        String cardNumber = sc.nextLine();

        System.out.print("Enter card holder name: ");
        String cardHolderName = sc.nextLine();

        System.out.print("Enter expiry date (MM/YY): ");
        String expiryDate = sc.nextLine();

        System.out.print("Enter CVV: ");
        String cvv = sc.nextLine();

        BankAccount account = new BankAccount(bankName, cardNumber, cardHolderName, expiryDate, cvv);

        if (account.validateCard()) {
            System.out.println("Bank account created successfully!");
            screen.getBankDatabase().addBankAccount(account);
        } else {
            System.out.println("Invalid card details. Please try again.");
        }

    }


    
}
