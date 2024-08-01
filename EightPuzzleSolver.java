import java.util.*;

public class EightPuzzleSolver {
    private static final int[][] goalState = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    private static final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private static class Node implements Comparable<Node> {
        int[][] board;
        int moves;
        Node parent;

        public Node(int[][] board, int moves, Node parent) {
            this.board = board;
            this.moves = moves;
            this.parent = parent;
        }

        public int compareTo(Node that) {
            return Integer.compare(this.moves + heuristic(), that.moves + that.heuristic());
        }

        public int heuristic() {
            int count = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] != 0 && board[i][j] != goalState[i][j]) {
                        count++;
                    }
                }
            }
            return count;
        }

        public boolean isGoal() {
            return Arrays.deepEquals(board, goalState);
        }

        public ArrayList<Node> getNeighbors() {
            ArrayList<Node> neighbors = new ArrayList<>();
            int zeroRow = 0, zeroCol = 0;
            outer:
            for (zeroRow = 0; zeroRow < 3; zeroRow++) {
                for (zeroCol = 0; zeroCol < 3; zeroCol++) {
                    if (board[zeroRow][zeroCol] == 0) {
                        break outer;
                    }
                }
            }

            for (int[] dir : dirs) {
                int newRow = zeroRow + dir[0];
                int newCol = zeroCol + dir[1];
                if (newRow >= 0 && newRow < 3 && newCol >= 0 && newCol < 3) {
                    int[][] newBoard = new int[3][3];
                    for (int i = 0; i < 3; i++) {
                        System.arraycopy(board[i], 0, newBoard[i], 0, 3);
                    }
                    newBoard[zeroRow][zeroCol] = newBoard[newRow][newCol];
                    newBoard[newRow][newCol] = 0;
                    neighbors.add(new Node(newBoard, moves + 1, this));
                }
            }
            return neighbors;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    sb.append(board[i][j]).append(" ");
                }
                sb.append("\n");
            }
            return sb.toString();
        }
    }

    public static int[][] solve(int[][] initial) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(initial, 0, null));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.isGoal()) {
                return node.board;
            }
            for (Node neighbor : node.getNeighbors()) {
                if (node.parent == null || !Arrays.deepEquals(neighbor.board, node.parent.board)) {
                    pq.add(neighbor);
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[][] initial = {
            {1, 2, 3},
            {4, 5, 6},
            {0, 7, 8}
        };
        int[][] solution = solve(initial);
        if (solution != null) {
            System.out.println("Solution found:");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(solution[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("No solution exists.");
        }
    }
}

