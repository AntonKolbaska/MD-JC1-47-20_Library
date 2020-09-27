package by.htp.lib.main;

import java.util.Scanner;

import by.htp.lib.controller.Controller;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Controller control = new Controller();
		String role = "JUNIOR";
		System.out.println("Started...\n"
				+ "Allowed commands:\n"
				+ "ADDBOOK (Title Price Edition Access)\n"
				+ "LOGIN (login password)\n"
				+ "SIGNUP (login password)\n"
				+ "CHANGEROLE (TargetLogin NewRole)\n"
				+ "SHOWLIB\n"
				+ "EXIT");
		
		String line = "";	
		System.out.print("Your command: ");
		
		if (scanner.hasNextLine()) {
			line = scanner.nextLine();
		} else {
			System.out.println("Error occured, please try again after a while");
		}
		
		while (!line.equals("EXIT") && !line.equals("Exit") && !line.equals("exit")) {
			String[] response = control.doAction(role + " " + line).split("\\s+");
			role = response[0];
			for (int i = 1; i < response.length; i++){
				System.out.print(response[i] + " ");
			}
			
			System.out.println("Your command: ");
			
			if (scanner.hasNextLine()) {
				line = scanner.nextLine();
			} else {
				System.out.println("Error. Try again...");
			}
		}
		
		scanner.close();
	}
}
