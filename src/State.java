import java.util.ArrayList;
import java.util.List;

enum Side {RIGHT, LEFT}

public class State {

	private int cannibalLeft;
	private int missionaryLeft;
	private int cannibalRight;
	private int missionaryRight;
	private Side boat;

	private State parentState;

	public State(int cannibalLeft, int missionaryLeft, Side boat, int cannibalRight, int missionaryRight) {
		this.cannibalLeft = cannibalLeft;
		this.missionaryLeft = missionaryLeft;
		this.boat = boat;
		this.cannibalRight = cannibalRight;
		this.missionaryRight = missionaryRight;
	}

	public boolean isGoal() {
		return cannibalLeft == 0 && missionaryLeft == 0;
	}

	public boolean isValid() {
		if (missionaryLeft >= 0 && missionaryRight >= 0 && cannibalLeft >= 0 && cannibalRight >= 0
	               && (missionaryLeft == 0 || missionaryLeft >= cannibalLeft)
	               && (missionaryRight == 0 || missionaryRight >= cannibalRight)) {
			return true;
		}
		return false;
	}

	public double findCost()
	{
		return 1;
	}

	public ArrayList<State> generateSuccessors() {
		ArrayList<State> successors = new ArrayList<>();
		if (boat == Side.LEFT) {
			testAndAdd(successors, new State(cannibalLeft, missionaryLeft - 2, Side.RIGHT, cannibalRight, missionaryRight + 2));
			testAndAdd(successors, new State(cannibalLeft - 2, missionaryLeft, Side.RIGHT, cannibalRight + 2, missionaryRight));
			testAndAdd(successors, new State(cannibalLeft - 1, missionaryLeft - 1, Side.RIGHT, cannibalRight + 1, missionaryRight + 1));
			testAndAdd(successors, new State(cannibalLeft, missionaryLeft - 1, Side.RIGHT, cannibalRight, missionaryRight + 1));
			testAndAdd(successors, new State(cannibalLeft - 1, missionaryLeft, Side.RIGHT, cannibalRight + 1, missionaryRight));
		} else {
			testAndAdd(successors, new State(cannibalLeft, missionaryLeft + 2, Side.LEFT, cannibalRight, missionaryRight - 2));
			testAndAdd(successors, new State(cannibalLeft + 2, missionaryLeft, Side.LEFT, cannibalRight - 2, missionaryRight));
			testAndAdd(successors, new State(cannibalLeft + 1, missionaryLeft + 1, Side.LEFT, cannibalRight - 1, missionaryRight - 1));
			testAndAdd(successors, new State(cannibalLeft, missionaryLeft + 1, Side.LEFT, cannibalRight, missionaryRight - 1));
			testAndAdd(successors, new State(cannibalLeft + 1, missionaryLeft, Side.LEFT, cannibalRight - 1, missionaryRight));
		}
		return successors;
	}

	private void testAndAdd(List<State> successors, State newState) {
		if (newState.isValid()) {
			newState.setParentState(this);
			successors.add(newState);
		}
	}

	public int getCannibalLeft() {
		return cannibalLeft;
	}

	public void setCannibalLeft(int cannibalLeft) {
		this.cannibalLeft = cannibalLeft;
	}

	public int getMissionaryLeft() {
		return missionaryLeft;
	}

	public void setMissionaryLeft(int missionaryLeft) {
		this.missionaryLeft = missionaryLeft;
	}

	public int getCannibalRight() {
		return cannibalRight;
	}

	public void setCannibalRight(int cannibalRight) {
		this.cannibalRight = cannibalRight;
	}

	public int getMissionaryRight() {
		return missionaryRight;
	}

	public void setMissionaryRight(int missionaryRight) {
		this.missionaryRight = missionaryRight;
	}

	public void goToLeft() {
		boat = Side.LEFT;
	}

	public void goToRight() {
		boat = Side.RIGHT;
	}

	public boolean isOnLeft() {
		return boat == Side.LEFT;
	}

	public boolean isOnRigth() {
		return boat == Side.RIGHT;
	}

	public State getParentState() {
		return parentState;
	}

	public void setParentState(State parentState) {
		this.parentState = parentState;
	}


	public void printState(){
		System.out.println(this);
	}

	@Override
	public String toString() {

		if (boat == Side.LEFT) {
			return ("\t"+cannibalLeft + "\t\t,\t\t" + missionaryLeft
					+ "\t\t,\t" + "Left" + "\t,\t\t" + cannibalRight
					+ "\t\t,\t\t" + missionaryRight);
		} else {
			return ("\t"+cannibalLeft + "\t\t,\t\t" + missionaryLeft
					+ "\t\t,\t" + "Right" + "\t,\t\t" + cannibalRight
					+ "\t\t,\t\t" + missionaryRight);
		}
     }

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof State)) {
			return false;
		}
		State s = (State) obj;
        return (s.cannibalLeft == cannibalLeft && s.missionaryLeft == missionaryLeft
        		&& s.boat == boat && s.cannibalRight == cannibalRight
        		&& s.missionaryRight == missionaryRight);
	}
}
