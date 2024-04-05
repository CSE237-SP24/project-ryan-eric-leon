package bankapp;
import javax.swing.JButton;
import javax.swing.JSpinner;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//action listener doc: https://docs.oracle.com/javase%2Ftutorial%2Fuiswing%2F%2F/events/actionlistener.html
//stack overflow: https://stackoverflow.com/questions/40619661/how-to-use-actionlistener-to-print-values-in-the-textarea-in-my-calculator
public class DepositBtn extends JButton{
	ActionListener depositAction;
	
	public DepositBtn(JSpinner spinner) {
		this.setText("Deposit");
		
		this.depositAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                 System.out.println(spinner.getValue().toString());
            }
		};
		addAction(spinner);
	}
	
	private void addAction(JSpinner spinner) {
		this.addActionListener(this.depositAction);
	}
}
