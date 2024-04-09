package bankapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;

//action listener doc: https://docs.oracle.com/javase%2Ftutorial%2Fuiswing%2F%2F/events/actionlistener.html
//stack overflow: https://stackoverflow.com/questions/40619661/how-to-use-actionlistener-to-print-values-in-the-textarea-in-my-calculator
public class DepositBtn extends JButton {
	ActionListener depositAction;

	public DepositBtn(JSpinner spinner, MenuGUI gui) {
		this.setText("Deposit");

		this.depositAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					double amount = (double) spinner.getValue();
					gui.getAccount().deposit(amount);
					gui.updateBalance();
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(gui.getFrame(), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		};
		addAction();
	}

	private void addAction() {
		this.addActionListener(this.depositAction);
	}
}
