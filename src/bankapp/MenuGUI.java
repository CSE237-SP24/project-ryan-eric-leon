package bankapp;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import java.awt.*;

public class MenuGUI {
	
	private JFrame frame;
	private JPanel panel;
	private BankAccount currentAccount;
	
	public MenuGUI(BankAccount currentAccount) {
		frame = new JFrame();
		this.currentAccount = currentAccount;
		runGUI();
	};
	
	public void runGUI() {
		JPanel panel = new JPanel();	
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setSize(500, 400);
		this.frame.setLocationRelativeTo(null);
		
		
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		
		JSpinner spinner = new JSpinner();
		DepositBtn depositBtn = new DepositBtn(spinner);
		
		spinner.setPreferredSize(new Dimension(50, 30));
		
		panel.add(spinner);
		panel.add(depositBtn);
		frame.add(panel, BorderLayout.CENTER);
		this.frame.setVisible(true);
	}
}
