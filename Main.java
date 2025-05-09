public class Main {
    public static void main(String[] args) {

        Database db = new Database("accounts.dat");
        Screen screen = new Screen(db);
        Application app = new Application(screen);
        app.run();
    }
}
