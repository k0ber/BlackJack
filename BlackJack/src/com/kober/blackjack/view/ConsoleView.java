package com.kober.blackjack.view;

import java.util.Scanner;

public class ConsoleView implements View {
	private boolean isFirstAsk = true;
	private static final Scanner scanIn = new Scanner(System.in);
	
	@Override
	public void showLabel(String label) {
		System.out.println(label);		
	}

	@Override
	public String askOptions(String[] options) {
		System.out.println("\nYou must decide what to do:");
		if (isFirstAsk) {
			System.out.println("(Just enter a number!)");
		}
		for (String option : options) {
			System.out.println("> " + option);
		}
		isFirstAsk = false;
		
	    String result = scanIn.nextLine();
	    return result;
	}

	@Override
	public String askQuestion(String question) {
		System.out.println(question);
	    String result = scanIn.nextLine();
	    return result;
	}

	@Override
	public void exit() {
		scanIn.close();
	}

}
