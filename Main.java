import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Main {

  static String fileName;

  @SuppressWarnings("resource")
  public static void main(String[] args) throws FileNotFoundException {

    //reading used from my asgmt2
    Scanner scanner = new Scanner(System.in);

    System.out.print("Please input the txt file name you would live to use (must include extension at end, ex: test.txt): ");
    fileName = scanner.nextLine(); //file to be opened

    File in = new File(fileName); //checking if file exists
    if (! in .exists()) { //if not found
      System.out.println();
      System.out.println("File not found, terminating. . .");
      System.exit(0); // terminating
    }

    ArrayList < String > info = FileIO.readFile(fileName); //reads file
    int[][] matrix = AdjacencyMatrixParser.parseFile(info);

    DijkstrasAlgorithm.dijkstra(matrix, 0, fileName);

    System.out.println("\nOperation complete!");
  }
}
