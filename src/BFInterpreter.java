import java.io.File;

/* Written by
 * Knut Erik Eldjarn
 * 
 * Interprets brainfuck files with the following commands:
 * "java BFInterpreter sourcefile inputstream"
 * 
*/ 

public class BFInterpreter {
	public static void main(String[] args) {
		if(args.length == 0) {
			System.out.println("Usage: \"java BFInterpreter sourcefile inputstream\"");
			return;
		}
		System.out.println("Interpreting " + args[0]);
		System.out.println("Output:");
		System.out.println("----------------------------------------\n");
		String input = "";
		if(args.length > 1) input = args[1];
		File fil = new File(args[0]);
		Reader reader =	new Reader(fil);
		Interpreter interpreter = new Interpreter(reader, input);
		if(interpreter.interpret()) {
			System.out.println("\n----------------------------------------");
			System.out.println("Program execution complete");
		} else {
			System.out.println("\n----------------------------------------");
			System.out.println("Program execution failed");
		}
	}
}