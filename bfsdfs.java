import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class bfsdfs {

    // Breadth-First Search
    public static boolean bfs(TreeNode root, int target) {
        if (root == null) return false;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current.val == target) return true;

            if (current.left != null) queue.offer(current.left);
            if (current.right != null) queue.offer(current.right);
        }

        return false;
    }

    // Depth-First Search
    public static boolean dfs(TreeNode root, int target) {
        if (root == null) return false;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            if (current.val == target) return true;

            if (current.right != null) stack.push(current.right);
            if (current.left != null) stack.push(current.left);
        }

        return false;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        int target = 23;
        System.out.println("Using Breadth-First Search:");
        System.out.println("Does " + target + " exist? " + bfs(root, target));

        System.out.println("\nUsing Depth-First Search:");
        System.out.println("Does " + target + " exist? " + dfs(root, target));
    }
}
