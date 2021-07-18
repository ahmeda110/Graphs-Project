import java.util.ArrayList;
import java.lang.Integer;

public class AdjacencyMatrixParser{

	public static int [][] parseFile(ArrayList<String> info){

		String [] toString = new String[info.size()]; //to read every line
		int matrix [][] = new int[info.size()][info.size()]; //d-matrix
		
		for(int x=0; info.size()>x; x++){
			toString = info.get(x).trim().split("\\s+"); //removing by whitespace
				for(int y=0; info.size()>y; y++){
					matrix[x][y] = Integer.parseInt(toString[y]);} //add to d-matrix
		}
		return matrix;
}}
