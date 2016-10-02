import java.util.List;


public class Genealogy {

	public static void main(String[] args) {
		
		TreeUtility t = new TreeUtility();
		List<Cell> cells = t.getListOfCells();
		t.createFamilyTree(cells);
		
	}

}
