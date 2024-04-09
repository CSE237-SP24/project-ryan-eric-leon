package bankapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

//action listener doc: https://docs.oracle.com/javase%2Ftutorial%2Fuiswing%2F%2F/events/actionlistener.html
//stack overflow: https://stackoverflow.com/questions/40619661/how-to-use-actionlistener-to-print-values-in-the-textarea-in-my-calculator
public class SwitchAccountBtn extends JButton {

	public SwitchAccountBtn(JTextField textField, MenuGUI gui) {
		this.setText("Switch");

		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText().trim();
				if (!name.isEmpty()) {
					try {
						gui.getMenu().switchAccount(name);
						gui.updateAccountName(name);
						gui.updateBalance();

						JOptionPane.showMessageDialog(null, "Account '" + name + "' switched to successfully.");
					} catch (IllegalArgumentException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Account name cannot be empty.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

}
