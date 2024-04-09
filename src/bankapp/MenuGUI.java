package bankapp;

import java.awt.BorderLayout;
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
	// set up a spinner number model that begins at 0.0
	// and goes from negative MAX_VALUE to positive MAX_VALUE
	// with a step size of 0.01 which is 1 cent
	private SpinnerNumberModel model = new SpinnerNumberModel(0.0, -Double.MAX_VALUE, Double.MAX_VALUE, 0.01);

	public MenuGUI(BankAccount currentAccount) {
		frame = new JFrame("Bank APP Menu");
		this.currentAccount = currentAccount;
		runGUI();
	};

	public void runGUI() {
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setSize(400, 300);
		this.frame.setLocationRelativeTo(null);

		panel = new JPanel(new BorderLayout());
		JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		createDepositBtn(inputPanel);

		createBalanceLabel();
		panel.add(inputPanel, BorderLayout.CENTER); // input panel in the center

		frame.add(panel);
		frame.setVisible(true);
	}

	private void createBalanceLabel() {
		balanceLabel = new JLabel("Current Balance: $" + currentAccount.getBalance());
		balanceLabel.setPreferredSize(new Dimension(200, 30));

		panel.add(balanceLabel, BorderLayout.NORTH); // balance label at the top
	}

	private void createDepositBtn(JPanel inputPanel) {
		JSpinner spinner = new JSpinner(model);
		spinner.setPreferredSize(new Dimension(80, 30));
		DepositBtn depositBtn = new DepositBtn(spinner, this);

		inputPanel.add(spinner);
		inputPanel.add(depositBtn);
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
