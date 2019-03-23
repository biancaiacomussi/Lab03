package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.*;
import java.util.ResourceBundle;


import it.polito.tdp.spellchecked.model.Dictionary;
import it.polito.tdp.spellchecked.model.RichWord;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class SpellCheckerController {
	
	private Dictionary dictionary = new Dictionary();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> btnLanguage;

    @FXML
    private TextArea txtInput;

    @FXML
    private Button btnSpellChecked;

    @FXML
    private TextArea txtMessaggi;

    @FXML
    private Label labelErrori;

    @FXML
    private Button btnClear;

    @FXML
    private Label labelSecondi;

    @FXML
    void doClear(ActionEvent event) {
    	txtMessaggi.clear();

    }

    @FXML
    void doSpellChecked(ActionEvent event) {
    	double tempo= System.nanoTime();
    	if(btnLanguage.getValue()!=null) {
    	dictionary.loadDictionary(btnLanguage.getValue());
    	String input = txtInput.getText();
    	input.toLowerCase();
    	input = input.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_'~()\\[\\]\"]", "");
    	
    	if(input.contains("[0-9]+")) {
			txtMessaggi.appendText("Devi inserire solo testo\n");
			throw new InvalidParameterException("Devi inserire solo testo\n");
		}
    	List <String> list = new LinkedList();
    	String [] parole = input.split("\\s+"); //separa anche se ci sono tanti spazi
    	
    	for(String s : parole) {
    		list.add(s.trim());
    	}
    	
    	List<RichWord> listRichWord = dictionary.spellCheckedText(list);
    	
    	int cont = 0;
    	
    	for(RichWord rw : listRichWord) {
    		if(rw.getCorretto()==false) {
    			txtMessaggi.appendText(rw.getInput()+"\n");
    			
    			cont++;
    		}
    		
    	}
    	
    	labelErrori.setText("The text contains "+cont+"errors");
    	labelSecondi.setText("Spell checked completed in "+(tempo-System.nanoTime())/1000000000+"seconds");
    	}

    }
    @FXML
    void initialize() {
        assert btnLanguage != null : "fx:id=\"btnLanguage\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnSpellChecked != null : "fx:id=\"btnSpellChecked\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtMessaggi != null : "fx:id=\"txtMessaggi\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert labelErrori != null : "fx:id=\"labelErrori\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert labelSecondi != null : "fx:id=\"labelSecondi\" was not injected: check your FXML file 'SpellChecker.fxml'.";

    }
    
    public void setModel(Dictionary model) {
		dictionary = model;

		btnLanguage.getItems().addAll("Italian","English");

	}
}

