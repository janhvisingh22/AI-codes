import java.util.*;

public class missionariesandcannibals {
    // Class to represent a state of the problem
    static class State {
        int missionariesLeft;
        int cannibalsLeft;
        int missionariesRight;
        int cannibalsRight;
        boolean boat;

        State(int ml, int cl, int mr, int cr, boolean b) {
            missionariesLeft = ml;
            cannibalsLeft = cl;
            missionariesRight = mr;
            cannibalsRight = cr;
            boat = b;
        }

        boolean isValidState() {
            if (missionariesLeft < 0 || cannibalsLeft < 0 ||
                    missionariesRight < 0 || cannibalsRight < 0 ||
                    (missionariesLeft != 0 && missionariesLeft < cannibalsLeft) ||
                    (missionariesRight != 0 && missionariesRight < cannibalsRight))
                return false;
            return true;
        }

        boolean isGoalState() {
            return missionariesLeft == 0 && cannibalsLeft == 0;
        }

        // Override hashCode and equals to enable storing in HashSet
        @Override
        public int hashCode() {
            return Objects.hash(missionariesLeft, cannibalsLeft, missionariesRight, cannibalsRight, boat);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof State)) return false;
            State other = (State) obj;
            return missionariesLeft == other.missionariesLeft &&
                    cannibalsLeft == other.cannibalsLeft &&
                    missionariesRight == other.missionariesRight &&
                    cannibalsRight == other.cannibalsRight &&
                    boat == other.boat;
        }
    }

    static void printPath(Map<State, State> parentMap, State curr) {
        List<State> path = new ArrayList<>();
        while (curr != null) {
            path.add(curr);
            curr = parentMap.get(curr);
        }
        Collections.reverse(path);
        for (State state : path) {
            System.out.println(state.missionariesLeft + " " + state.cannibalsLeft + " " +
                    state.missionariesRight + " " + state.cannibalsRight + " " +
                    (state.boat ? "Left to Right" : "Right to Left"));
        }
    }

    static void solve(int m, int c) {
        State initialState = new State(m, c, 0, 0, true); // Start with all missionaries and cannibals on the left bank
        Queue<State> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();
        Map<State, State> parentMap = new HashMap<>();

        queue.add(initialState);
        visited.add(initialState);
        while (!queue.isEmpty()) {
            State currState = queue.poll();

            if (currState.isGoalState()) {
                System.out.println("Solution found:");
                printPath(parentMap, currState);
                return;
            }

            // Generate possible next states
            List<State> nextStates = new ArrayList<>();
            if (currState.boat) { // Boat is on the left bank
                for (int i = 0; i <= 2; i++) {
                    for (int j = 0; j <= 2; j++) {
                        if (i + j >= 1 && i + j <= 2) { // Only 1 or 2 people can travel
                            State nextState = new State(currState.missionariesLeft - i, currState.cannibalsLeft - j,
                                    currState.missionariesRight + i, currState.cannibalsRight + j, false);
                            if (nextState.isValidState() && !visited.contains(nextState)) {
                                nextStates.add(nextState);
                                visited.add(nextState);
                                parentMap.put(nextState, currState);
                            }
                        }
                    }
                }
            } else { // Boat is on the right bank
                for (int i = 0; i <= 2; i++) {
                    for (int j = 0; j <= 2; j++) {
                        if (i + j >= 1 && i + j <= 2) { // Only 1 or 2 people can travel
                            State nextState = new State(currState.missionariesLeft + i, currState.cannibalsLeft + j,
                                    currState.missionariesRight - i, currState.cannibalsRight - j, true);
                            if (nextState.isValidState() && !visited.contains(nextState)) {
                                nextStates.add(nextState);
                                visited.add(nextState);
                                parentMap.put(nextState, currState);
                            }
                        }
                    }
                }
            }
            queue.addAll(nextStates);
        }
        System.out.println("No solution exists.");
    }

    public static void main(String[] args) {
        int missionaries = 3; // Number of missionaries on each bank
        int cannibals = 3; // Number of cannibals on each bank
        solve(missionaries, cannibals);
    }
}
