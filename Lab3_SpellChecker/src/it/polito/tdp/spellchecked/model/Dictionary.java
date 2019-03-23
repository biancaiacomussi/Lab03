package it.polito.tdp.spellchecked.model;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {
	
	private List<String> parole;
	private String lingua;
	
	public Dictionary() {
		parole = new LinkedList();
	}

	public void loadDictionary(String language) {
		
		lingua=language;
		try {
			parole.clear();
			FileReader fr = new FileReader("rsc/"+language+".txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			while((word = br.readLine())!=null) {
			parole.add(word);	
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Errore nella lettura del file");
		}
	}
	
	public List<RichWord> spellCheckedText(List<String> inputTextList){
		List <RichWord> rwlist = new LinkedList();
		
			for(String s: inputTextList) {
				RichWord rw = new RichWord(s);
				for(String t: parole) {
					
					if(s.equals(t))
						rw.isCorretto();
					rwlist.add(rw);
				}
			}
		
		return rwlist;
	}
}
