package com.techelevator.view;


import com.techelevator.tenmo.models.AppUser;
import com.techelevator.tenmo.models.User;
import com.techelevator.tenmo.models.transfer.Transfer;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class ConsoleService {

	private PrintWriter out;
	private Scanner in;

	public ConsoleService(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output, true);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		out.println();
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}

	public String getUserInput(String prompt) {
		out.print(prompt+": ");
		out.flush();
		return in.nextLine();
	}

	public Integer getUserInputInteger(String prompt) {
		Integer result = null;
		do {
			out.print(prompt+": ");
			out.flush();
			String userInput = in.nextLine();
			try {
				result = Integer.parseInt(userInput);
			} catch(NumberFormatException e) {
				out.println(System.lineSeparator() + "*** " + userInput + " is not valid ***" + System.lineSeparator());
			}
		} while(result == null);
		return result;
	}
	//Added in to cover the input if Double is needed
	public Double getUserInputDouble(String prompt) {
		Double result = null;
		do {
			out.print(prompt+": ");
			out.flush();
			String userInput = in.nextLine();
			try {
				result = Double.parseDouble(userInput);
			} catch(NumberFormatException e) {
				out.println(System.lineSeparator() + "*** " + userInput + " is not valid ***" + System.lineSeparator());
			}
		} while(result == null);
		return result;
	}

	public Long getUserInputLong(String prompt) {
		Long result = null;
		do {
			out.print(prompt+": ");
			out.flush();
			String userInput = in.nextLine();
			try {
				result = Long.parseLong(userInput);
			} catch(NumberFormatException e) {
				out.println(System.lineSeparator() + "*** " + userInput + " is not valid ***" + System.lineSeparator());
			}
		} while(result == null);
		return result;
	}

	public void printError(String errorMessage) {
		System.err.println(errorMessage);
	}

	public void printBalance(Double balance) {
		System.out.println("Your current account balance is: $" + balance);
	}

	public void printUsers(List<User> users) {
		System.out.println("ID     USER");
		for (User user : users) {
			System.out.println(user.getId() + "     " + user.getUsername());
		}
	}

	public void printTransferReceipt(Transfer transfer) {
		System.out.println("Your transfer of $" + transfer.getAmount() + " was successful.");
	}
}
