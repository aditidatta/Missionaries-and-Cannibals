import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by alien on 5/26/16.
 */
public class BFS {
    public void search(){
        State root = new State(3,3,Side.LEFT,0,0);
        State goal = new State(0,0,Side.RIGHT,3,3);

        Queue<State> states = new LinkedList<>();
        LinkedList<State> usedStates = new LinkedList<>();

        states.add(root);

        while(!states.isEmpty()){
            State s = states.poll();
            usedStates.add(s);
            if(s.equals(goal)){
                for(State sol: usedStates ){
                    System.out.println(sol);
                }
            }
            else{
                for (State s1: s.generateSuccessors()){
                    if(!usedStates.contains(s1) || !states.contains(s1)){
                        states.add(s1);
                    }
                }

            }

        }

    }
}
