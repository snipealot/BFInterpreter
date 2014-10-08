import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

/* 
 * Reader.java
 */

public class Reader {
	Scanner scan;
	Pattern pattern = Pattern.compile("[\\[\\]\\.\\+\\<\\>\\-\\,]");
	String program = "";
	char c;
	int counter;
	// Konstruktør
	Reader(File fil) {
		counter = 0;
		try {
			scan = new Scanner(fil);
		} catch (FileNotFoundException e) { System.out.println("File not found"); return;}
		process();
	}
	
	public void process() {
		if(!readFile()) {
			System.out.println("Could not read file");
		}
	}
	
	boolean readFile() {
		if(scan == null) return false;
		while(scan.hasNext()) {
			String inn = scan.next();
			if(!(inn.matches("[\\[\\]\\.\\+\\<\\>\\-\\,]+"))) {
				return false; // Bruker regex til å sjekke om filen er valid bf fil
			}
			program = program + inn; // Legger hele programmet inn i en streng for å gjøre den lettere å prosessere for readNext()
		}
		return true;
	}

	public boolean readNext() {
		if(program.length() > counter) {
			c = program.charAt(counter);
			counter++;
			return true;
		} else {
			return false;
		}
	}
	
	public void goTo(int i) {
		counter = i;
	}
	
	public char getC() {
		return c;
	}
}
