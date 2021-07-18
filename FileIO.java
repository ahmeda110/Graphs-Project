import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class FileIO {
	public static void main(String[] args) {
		ArrayList<String> lines = readFile(args[0]);
		
		for(int i = 0; i < lines.size(); i++) {
			System.out.println(lines.get(i));
		}
		
		writeFile("XXXXXXX", "XXXXXXX"); // test line (to be modified)
		
	}
	
	public static ArrayList<String> readFile(String path) {
		Path inputfile = Paths.get(path);
		ArrayList<String> lines = new ArrayList<String>();
		try (BufferedReader reader = Files.newBufferedReader(inputfile)) {
    			String line = null;
    			while (((line = reader.readLine()) != null)) {
    				if(!line.contains("#")) {lines.add(line);} //skip lines with #
    			}
		} catch (IOException x) {
 	   		System.err.format("IOException: %s%n", x);
		}
		return lines;
	}
	
	public static void writeFile(String path, String data) {
		Path outputfile = Paths.get(path);	
		try (BufferedWriter writer = Files.newBufferedWriter(outputfile)) {
			writer.write(data, 0, data.length());
		} catch (IOException x) {
    			System.err.format("IOException: %s%n", x);
		}	
	}
}
