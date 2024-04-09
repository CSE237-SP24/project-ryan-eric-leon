package bankapp;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class MenuGUI {

	private JFrame frame;
	private JPanel panel;
	private JLabel balanceLabel;
	private JLabel accountNameLabel;
	private Menu menu;

	public static void main(String[] args) {
		Menu menu = new Menu();
		new MenuGUI(menu).runGUI();
	}

	public MenuGUI(Menu menu) {
		this.frame = new JFrame("Bank APP Menu");
		this.panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.menu = menu;

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// call saveAccounts after the GUI is closed
				menu.saveAccounts();
			}
		});
	};

	public void runGUI() {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setLocationRelativeTo(null);

		createAccountNameLabel();
		createBalanceLabel();

		createDepositBtn();
		createWithdrawBtn();

		frame.add(panel);
		frame.setVisible(true);
	}

	private void createAccountNameLabel() {
		accountNameLabel = new JLabel("Account Name: root"); // root is the default name when user opens
		accountNameLabel.setPreferredSize(new Dimension(150, 30));

		panel.add(accountNameLabel);
	}

	private void createBalanceLabel() {
		balanceLabel = new JLabel("Current Balance: $" + menu.getAccount().getBalance());
		balanceLabel.setPreferredSize(new Dimension(150, 30));

		panel.add(balanceLabel);
	}

	private JSpinner createSpinner() {
		// set up a spinner number model that begins at 0.0
		// and goes from negative MAX_VALUE to positive MAX_VALUE
		// with a step size of 0.01 which is 1 cent
		SpinnerNumberModel model = new SpinnerNumberModel(0.0, -Double.MAX_VALUE, Double.MAX_VALUE, 0.01);
		JSpinner spinner = new JSpinner(model);
		spinner.setPreferredSize(new Dimension(80, 30));
		return spinner;
	}

	private void createDepositBtn() {
		JSpinner spinner = createSpinner();
		DepositBtn depositBtn = new DepositBtn(spinner, this);

		panel.add(spinner);
		panel.add(depositBtn);
	}

	private void createWithdrawBtn() {
		JSpinner spinner = createSpinner();
		WithdrawBtn withdrawBtn = new WithdrawBtn(spinner, this);

		panel.add(spinner);
		panel.add(withdrawBtn);
	}

	public void updateBalance() {
		balanceLabel.setText("Current Balance: $" + menu.getAccount().getBalance());
	}

	public BankAccount getAccount() {
		return menu.getAccount();
	}

	public JFrame getFrame() {
		return frame;
	}
}
