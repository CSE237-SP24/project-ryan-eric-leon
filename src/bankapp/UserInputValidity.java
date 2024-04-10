package bankapp;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInputValidity {
	private Scanner in;
	private double limit;

	public UserInputValidity(Scanner in, double limit) {
		this.in = in;
		this.limit = limit;
	}

	public double getValidUserInput() {
		double amount;
		while (true) {
			try {
				amount = in.nextDouble();
				if (amount < 0) {
					System.err.println("Invalid value! Please enter a non-negative number.");
				} else if (amount >= limit) {
					System.err.println(
							"You are not supposed to process an amount >= " + limit + " in a single transaction.");
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
