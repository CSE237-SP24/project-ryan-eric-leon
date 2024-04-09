package bankapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class TransferBtn extends JButton {

    public TransferBtn(JSpinner spinner, JTextField textField, MenuGUI gui) {
        this.setText("Transfer");

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = (double) spinner.getValue();
                    String receiverAccountName = textField.getText().trim();

                    BankAccount senderAccount = gui.getAccount();
                    
                    HashMap<String, BankAccount> accounts = gui.getMenu().getAllAccounts();
                    BankAccount receiverAccount = accounts.get(receiverAccountName);

                    if (receiverAccount == null) {
                        JOptionPane.showMessageDialog(gui.getFrame(), "Target account not found.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    senderAccount.transfer(amount, receiverAccount);
                    gui.updateBalance();

                    JOptionPane.showMessageDialog(gui.getFrame(), "Transfer successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(gui.getFrame(), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
