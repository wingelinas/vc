package ca.csf.exercice01;

import java.util.jar.JarException;


@SuppressWarnings("unused")
public class HpPrinter implements Printer {

	private String printerURL;
	private String configuration;

	public HpPrinter(String printerURL, String configuration) {
		this.printerURL = printerURL;
		this.configuration = configuration;
	}
	
	public void print(Document document) {
		document.getLines();
		//Assumons que cela fait quelque chose d'autre ensuite, mais pour cet exercice, lançons tout simplement une exception
		throw new RuntimeException("Cannot find the driver for this printer. Please contact your administrator.");
	}
	
}
