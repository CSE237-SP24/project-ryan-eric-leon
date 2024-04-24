package bankapp;

public class ProcessUserDisplay {
	public void welcomeMessage() {
		System.out.println(
				"Welcome our App! Here are the list of commands for our app, please let us know what do you want to do!");
	}

	public void showOptions() {
		System.out.println(
				"Commands: options, accounts, balance, deposit, withdraw, transfer, insert, switch, history, quit");
	}

	public void optionsDescriptions() {
		System.out.println("options: check the command options again.");
		System.out.println("accounts: check all the accounts in the bank app.");
		System.out.println("balance: check the balance of your account.");
		System.out.println("deposit, withdraw: deposit/withdraw money to/from your account.");
		System.out.println("transfer: transfer money from your account to another account.");
		System.out.println("insert: create a new account.");
		System.out.println("switch: switch between your accounts.");
		System.out.println("history: display the transaction history of your account.");
		System.out.println("quit: terminate the bank app.");
	}

	public void ByeMessage() {
		System.out.println("Thanks for using our Bank App. Have a good day and bye!");
	}

	public void showError(String message) {
		System.err.println(message);
	}

	public void showInfo(String message) {
		System.out.println(message);
	}
}
