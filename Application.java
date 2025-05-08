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
                default:
                    break;

            }

        }
    }

    private int getChoice(){


    }

    private int getPage(){

    }

    private void signup(){
        String username;
        String password;
        String confirmPassword;
        username = sc.nextLine();
        password = sc.nextLine();
        confirmPassword = sc.nextLine();
        screen.signUp(username, password, confirmPassword);
    }

    private void login(){
        String username = sc.nextLine();
        String password = sc.nextLine();
        screen.logIn(username, password);
        while (screen.isLoggedIn()){
            int page = getPage();
            switch (page){
                case 1:
                    dashboardPanel();
                    break;
                case 2:
                    screen.logOut();



            }


        }
    }

    private void dashboardPanel(){

    }

    private void allocationPanel(){

    }

    private void zakatCompliancePanel(){

    }

    
}
