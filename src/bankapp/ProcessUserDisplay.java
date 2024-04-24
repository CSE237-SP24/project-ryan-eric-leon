package bankapp;

public class ProcessUserDisplay {
    public void WelcomeMessage() {
        System.out.println("Welcome our App! Here are the list of commands for our app, please let us know what do you want to do!");
    }

    public static void showOptions() {
        System.out.println("Commands: balance, deposit, withdraw, transfer, insert, switch, history, quit");
    }
    
    public static void optionsDescriptions() {
    	System.out.println("balance: checking the balance of your account.");
        System.out.println("deposit, withdraw: deposit/withdraw money to/from your account.");
        System.out.println("transfer: transfer money from your account to another account.");
        System.out.println("insert: creating a new account.");
        System.out.println("switch: switching between your accounts.");
        System.out.println("history: displaying the transaction history of your account.");
        System.out.println("quit: terminating the commands.");
    }

    public static void ByeMessage() {
        System.out.println("Thanks for using our App. Have a good day and bye!");
    }

    public void showError(String message) {
        System.err.println(message);
    }

    public void showInfo(String message) {
        System.out.println(message);
    }
}
