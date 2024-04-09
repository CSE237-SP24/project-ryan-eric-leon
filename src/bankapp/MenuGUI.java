package bankapp;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class MenuGUI {

	private JFrame frame;
	private JPanel panel;
	private BankAccount currentAccount;
	private JLabel balanceLabel;

	public MenuGUI(BankAccount currentAccount) {
		this.frame = new JFrame("Bank APP Menu");
		this.panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.currentAccount = currentAccount;
		runGUI();
	};

	public void runGUI() {
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setSize(400, 300);
		this.frame.setLocationRelativeTo(null);

		createBalanceLabel();
		createDepositBtn();
		createWithdrawBtn();

		frame.add(panel);
		frame.setVisible(true);
	}

	private void createBalanceLabel() {
		balanceLabel = new JLabel("Current Balance: $" + currentAccount.getBalance());
		balanceLabel.setPreferredSize(new Dimension(360, 30));

		panel.add(balanceLabel);
	}

	private void createDepositBtn() {
		// set up a spinner number model that begins at 0.0
		// and goes from negative MAX_VALUE to positive MAX_VALUE
		// with a step size of 0.01 which is 1 cent
		SpinnerNumberModel model = new SpinnerNumberModel(0.0, -Double.MAX_VALUE, Double.MAX_VALUE, 0.01);
		JSpinner spinner = new JSpinner(model);
		spinner.setPreferredSize(new Dimension(80, 30));
		DepositBtn depositBtn = new DepositBtn(spinner, this);

		panel.add(spinner);
		panel.add(depositBtn);
	}

	private void createWithdrawBtn() {
		// set up a spinner number model that begins at 0.0
		// and goes from negative MAX_VALUE to positive MAX_VALUE
		// with a step size of 0.01 which is 1 cent
		SpinnerNumberModel model = new SpinnerNumberModel(0.0, -Double.MAX_VALUE, Double.MAX_VALUE, 0.01);
		JSpinner spinner = new JSpinner(model);
		spinner.setPreferredSize(new Dimension(80, 30));
		WithdrawBtn withdrawBtn = new WithdrawBtn(spinner, this);

		panel.add(spinner);
		panel.add(withdrawBtn);
	}

	public void updateBalance() {
		balanceLabel.setText("Current Balance: $" + currentAccount.getBalance());
	}

	public BankAccount getAccount() {
		return currentAccount;
	}

	public JFrame getFrame() {
		return frame;
	}
}
