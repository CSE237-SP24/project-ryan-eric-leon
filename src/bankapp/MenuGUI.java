package bankapp;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
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
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);

		createLabels();

		createDepositBtn();
		createWithdrawBtn();

		createCreateAccountBtn();

		frame.add(panel);
		frame.setVisible(true);
	}

	private void createLabels() {
		JPanel subPanel = new JPanel();
		subPanel.setLayout(new GridLayout(1, 2));
		subPanel.setPreferredSize(new Dimension(500, 30));

		createAccountNameLabel(subPanel);
		createBalanceLabel(subPanel);

		panel.add(subPanel);
	}

	private void createAccountNameLabel(JPanel subPanel) {
		accountNameLabel = new JLabel("Account Name: root"); // root is the default name when user opens

		subPanel.add(accountNameLabel);
	}

	private void createBalanceLabel(JPanel subPanel) {
		balanceLabel = new JLabel("Current Balance: $" + menu.getAccount().getBalance());

		subPanel.add(balanceLabel);
	}

	private JSpinner createSpinner() {
		// set up a spinner number model that begins at 0.0
		// and goes from negative MAX_VALUE to positive MAX_VALUE
		// with a step size of 0.01 which is 1 cent
		SpinnerNumberModel model = new SpinnerNumberModel(0.0, -Double.MAX_VALUE, Double.MAX_VALUE, 0.01);
		JSpinner spinner = new JSpinner(model);
		return spinner;
	}

	private void createDepositBtn() {
		JPanel subPanel = new JPanel();
		subPanel.setLayout(new GridLayout(1, 2));
		subPanel.setPreferredSize(new Dimension(250, 30));

		JSpinner spinner = createSpinner();
		DepositBtn depositBtn = new DepositBtn(spinner, this);

		subPanel.add(spinner);
		subPanel.add(depositBtn);
		panel.add(subPanel);
	}

	private void createWithdrawBtn() {
		JPanel subPanel = new JPanel();
		subPanel.setLayout(new GridLayout(1, 2));
		subPanel.setPreferredSize(new Dimension(250, 30));

		JSpinner spinner = createSpinner();
		WithdrawBtn withdrawBtn = new WithdrawBtn(spinner, this);

		subPanel.add(spinner);
		subPanel.add(withdrawBtn);
		panel.add(subPanel);
	}

	public void createCreateAccountBtn() {
		JPanel subPanel = new JPanel();
		subPanel.setLayout(new GridLayout(2, 2));
		subPanel.setPreferredSize(new Dimension(500, 60));

		JTextField accountNameField = new JTextField();
		CreateAccountBtn createAccountBtn = new CreateAccountBtn(accountNameField, menu);

		subPanel.add(new JLabel("Account Name:"));
		subPanel.add(accountNameField);
		subPanel.add(createAccountBtn);
		subPanel.add(createAccountBtn);

		panel.add(subPanel);
	}

	public void updateBalance() {
		balanceLabel.setText("Current Balance: $" + menu.getAccount().getBalance());
	}

	public Menu getMenu() {
		return menu;
	}

	public BankAccount getAccount() {
		return menu.getAccount();
	}

	public JFrame getFrame() {
		return frame;
	}
}
