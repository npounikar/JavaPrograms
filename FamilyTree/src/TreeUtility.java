import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class TreeUtility {


	public List<Cell> getListOfCells() {

		List<Cell> list = new ArrayList<Cell>();

		list.add(new Cell("b", "a"));
		list.add(new Cell("c", "a"));
		list.add(new Cell("d", "a"));
		list.add(new Cell("e", "b"));
		list.add(new Cell("f", "e"));
		list.add(new Cell("g", "c"));
		list.add(new Cell("a", null));

		return list;
	}


	public void createFamilyTree(List<Cell> cells) {

		HashMap<String, ArrayList<String>> map = createMap(cells);
		String[][] adjMatrix = getAdjacencyMatrix(cells);
		performLevelByLeveltraversal(cells, map, adjMatrix);

	}


	private HashMap<String, ArrayList<String>> createMap(List<Cell> cells) {

		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		for(int i=0; i<cells.size(); i++) {
			String parent = cells.get(i).parent;
			String name = cells.get(i).name;

			if(!map.containsKey(parent)) {
				ArrayList<String> children = new ArrayList<String>();
				children.add(name);
				map.put(parent, children);
			} else {
				map.get(parent).add(name);
			}

		}

		return map;
	}



	private String[][] getAdjacencyMatrix(List<Cell> cells) {

		String[][] adj = new String[cells.size()+1][cells.size()+1];

		//init array
		for(int i=0; i<adj.length; i++) 
			for(int j=0; j<adj[0].length; j++) 
				adj[i][j] = "0";

		//label rows
		for(int i = 1; i<adj[0].length; i++)
			adj[i][0] = cells.get(i-1).name;

		//label cols
		for(int i = 1; i<adj[0].length; i++)
			adj[0][i] = cells.get(i-1).name;


		//set 0 - no a child and , 1 - child
		for(int i=1; i<adj.length; i++) {
			String parent = adj[i][0];
			for(int j=1; j<adj[0].length; j++) 
				if(cells.get(j-1).parent == parent)
					adj[i][j] = "1";
		}



		//print
		for(int i=0; i<adj.length; i++) {
			for(int j=0; j<adj[0].length; j++) { 
				System.out.print(adj[i][j]+", ");
			}
			System.out.println();
		}

		return adj;
	}



	private void performLevelByLeveltraversal(List<Cell> cells, HashMap<String, ArrayList<String>> map, String[][] adj) {

		String root="";
		int count = 0;
		for(int i=1; i<adj.length; i++) {
			String col = adj[0][i];
			count = 0; 
			for(int j=1; j<adj[0].length; j++) {
				if(adj[j][i] == "1")
					break;
				else
					count++;
			}
			if(count ==  cells.size()) {
				root = col;
				break;
			}
		}


		Queue<String> q1 = new LinkedList<String>();
		Queue<String> q2 = new LinkedList<String>();
		q1.add(root);
		while(!q1.isEmpty() || !q2.isEmpty()) {
			String cell;
			while(!q1.isEmpty()) {
				cell = q1.poll();
				System.out.print(cell+", ");
				ArrayList<String> children = new ArrayList<String>();
				children = map.get(cell);
				if(children != null) {
					for(int i =0; i< children.size(); i++)
						q2.add(children.get(i));
				}
			}
			System.out.println();
			
			while(!q2.isEmpty()) {
				cell = q2.poll();
				System.out.print(cell+", ");
				ArrayList<String> children = map.get(cell);
				if(children != null) {
					for(int i =0; i< children.size(); i++)
						q1.add(children.get(i));
				}
			}
			System.out.println();
			
			
			
		}

	}

}
