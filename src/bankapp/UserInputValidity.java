package bankapp;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInputValidity {
    private Scanner in;

    public UserInputValidity(Scanner in) {
        this.in = in;
    }

    public double getValidUserInput() {
        double amount;
        while (true) {
            try {
                amount = in.nextDouble();
                if (amount < 0) {
                    System.err.println("Invalid value! Please enter a non-negative number.");
                } else {
                    return amount;
                }
            } catch (InputMismatchException e) {
                System.err.println("Invalid value! Please enter a non-negative number.");
                in.next(); 
            }
        }
    }
}
