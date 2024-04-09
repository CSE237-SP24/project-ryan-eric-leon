package bankapp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

public class MenuGUI {

	private JFrame frame;
	private JPanel panel;
	private BankAccount currentAccount;
	private JLabel balanceLabel;

	public MenuGUI(BankAccount currentAccount) {
		frame = new JFrame("Bank APP Menu");
		this.currentAccount = currentAccount;
		runGUI();
	};

	public void runGUI() {
		JPanel panel = new JPanel();
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setSize(400, 300);
		this.frame.setLocationRelativeTo(null);

		panel = new JPanel(new BorderLayout());

		JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		JSpinner spinner = new JSpinner();
		spinner.setPreferredSize(new Dimension(40, 30));
		DepositBtn depositBtn = new DepositBtn(spinner);

		inputPanel.add(spinner);
		inputPanel.add(depositBtn);

		balanceLabel = new JLabel("Current Balance: $" + currentAccount.getBalance());
		balanceLabel.setPreferredSize(new Dimension(200, 30));

		panel.add(balanceLabel, BorderLayout.NORTH); // balance label at the top
		panel.add(inputPanel, BorderLayout.CENTER); // input panel in the center

		frame.add(panel);
		frame.setVisible(true);
	}

	public void updateBalance() {
		balanceLabel.setText("Current Balance: $" + currentAccount.getBalance());
	}
}
