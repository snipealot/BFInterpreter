/* INF1000 Oblig 5
 * Oppgave 5.5
 * Interpreter.java
 * Knut Erik Eldjarn
 * keeldjar
 * 
 */

public class Interpreter {
	Reader reader;
	byte[] bfProg; // Et BF program har en array av max int definert størrelse
	int bfCounter, inCounter; // samt en implisit peker som flyttes ved hjelp av '>' og '<' - For å simulere dette i java bruker jeg en counter
	String input;
	
	Interpreter(Reader r, String in) {
		reader = r;
		bfProg = new byte[Integer.MAX_VALUE-5]; // Oppretter en byte array av str. Integer.MAX_VALUE-5 fordi JVM er tullete
		// byte array istedetfor char fordi brainfuck vanligvis er array av 8bits(1byte), mens char som man ellers kunne brukt er 2bytes i java
		bfCounter = 0; // Setter "pekeren" på 0
		input = in;
		inCounter = 0;
	}
	
	boolean interpret() {
		int returnPos = reader.counter; // Setter en returposisjon for while loop - hele programmet tolkes altså som en do-while(false) loop
		while(reader.readNext()) {
			switch(reader.getC()) {
			case '[': { // While er skrevet som [ og ] i bf. Så lenge det pekeren peker på ikke er 0 skal while loopen kjøre - I praksis er dette en do-while loop
				interpret(); // Kaller seg selv rekursivt
				break;
			}
			case ']': {
				if(bfProg[bfCounter] == 0) {
					return false; // Returner fra while loop interpret() til original program interpret()
				} else {
					reader.goTo(returnPos); // Pekeren peker ikke på 0, dvs. while statement er fortsatt TRUE - repeat ved å sette reader til posisjonen man var i når while loopen ble opprettet
					break;
				}
			}
			case '+': {
				bfProg[bfCounter]++;
				break;
			}
			case '-': {
				bfProg[bfCounter]--;
				break;
			}
			case '>': {
				bfCounter++;
				break;
			}
			case '<': {
				bfCounter--;
				break;
			}
			case '.': {
				System.out.print((char)bfProg[bfCounter]);
				break;
			}
			case ',': {
				if(inCounter < input.length()) {
					bfProg[bfCounter] = (byte)input.charAt(inCounter);
					inCounter++;
				}
				break;
			}
			default: {
				System.out.println("Illegal char " + reader.getC());
				return false;
			}
			}
		}
		return true;
	}
	// For testing
	void printProg() {
		for(int i = 0; i < 20; i++) {
			System.out.print((int)bfProg[i]+" ");
		}
		System.out.println();
	}
}