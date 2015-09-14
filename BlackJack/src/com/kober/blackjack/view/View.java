package com.kober.blackjack.view;

public interface View {

	public void showLabel(String label);
	public String askOptions(String[] options);
	public String askQuestion(String question);
	public void exit();
}
