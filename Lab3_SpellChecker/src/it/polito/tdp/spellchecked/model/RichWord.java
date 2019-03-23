package it.polito.tdp.spellchecked.model;

public class RichWord {

	private String input;
	private boolean corretto;
	
	
	
	public RichWord(String input) {
		this.input = input;
		corretto=false;
	}



	public void isCorretto() {
		this.corretto=true;
	}



	public String getInput() {
		return input;
	}



	public void setInput(String input) {
		this.input = input;
	}



	public void setCorretto(boolean corretto) {
		this.corretto = corretto;
	}
	
	
	public boolean getCorretto() {
		return corretto;
	}
}
