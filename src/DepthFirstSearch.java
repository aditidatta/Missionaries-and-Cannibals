import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearch
{

	public static void search(State initState, boolean d)
	{
		SearchNode root = new SearchNode(initState);
		Stack<SearchNode> stack = new Stack<>();

		stack.add(root);

		performSearch(stack, d);
	}

	private static boolean checkRepeats(SearchNode n)
	{
		boolean state = false;
		SearchNode checkNode = n;

		while (n.getParent() != null && !state)
		{
			if (n.getParent().getCurrentState().equals(checkNode.getCurrentState()))
			{
				state = true;
			}
			n = n.getParent();
		}

		return state;
	}

	public static void performSearch(Stack<SearchNode> s, boolean d)
	{
		int searchCount = 1;

		while (!s.isEmpty())
		{
			SearchNode temp = s.pop();

			if (!temp.getCurrentState().isGoal())
			{
				ArrayList<State> successors = temp.getCurrentState()
						.generateSuccessors();

				for (int i = 0; i < successors.size(); i++)
				{
					SearchNode newNode = new SearchNode(temp,
							successors.get(i), temp.getCost()
									+ successors.get(i).findCost(), 0);

					if (!checkRepeats(newNode))
					{
						s.add(newNode);
					}
				}
				searchCount++;
			}
			else

			{
				Stack<SearchNode> solutionStack = new Stack<>();
				solutionStack.push(temp);
				temp = temp.getParent();

				while (temp.getParent() != null)
				{
					solutionStack.push(temp);
					temp = temp.getParent();
				}
				solutionStack.push(temp);

				int length = solutionStack.size();

				for (int i = 0; i < length; i++)
				{
					temp = solutionStack.pop();
					temp.getCurrentState().printState();
					System.out.println();
					System.out.println();
				}
				System.out.println("The cost was: " + temp.getCost());
				if (d)
				{
					System.out.println("The number of nodes examined: "
							+ searchCount);
				}

				System.exit(0);
			}
		}
		System.out.println("Error! No solution found!");
	}
}
