import java.util.BitSet;

public class ProblemSolver {

	public static void main(String[] args) {
		System.out.println("Missionary and Cannibal Puzzle" +
				" solver using Breadth First Search");

		State initialState = new State (3, 3, Side.LEFT, 0, 0);
		System.out.println("Cannibal\t,\tMissionary\t,\tBoat\t,\tCannibal\t,\tMissionary");

		//DepthFirstSearch.search(initialState, false);
		BFS bfs = new BFS();
		bfs.search();
	}
}
