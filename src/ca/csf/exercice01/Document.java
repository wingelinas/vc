package ca.csf.exercice01;

import java.util.ArrayList;
import java.util.List;

public class Document {
	
	private List<String> lines;
	
	public Document() {
		lines = new ArrayList<String>();
	}
	
	public void append(String line) {
		lines.add(line);
	}
	
	public String[] getLines() {
		return (String[])lines.toArray();
	}
}
