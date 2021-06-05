import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeSprinkler {

    public static void main(String[] args) {
        //String nodes = "0,0,null,0,null,0,null,null,0";
        String nodes = "0,0,null,0,0";
        TreeNode root = deserialize(nodes);
        System.out.println(minCameraCover(root));
    }

    public static int minCameraCover(TreeNode root) {
        int[] ans = solve(root);
        return Math.min(ans[1], ans[2]);
    }

    // 0: Strict ST; All nodes below this are covered, but not this one
    // 1: Normal ST; All nodes below and incl this are covered - no camera
    // 2: Placed camera; All nodes below this are covered, plus camera here
    public static int[] solve(TreeNode node) {
        if (node == null)
            return new int[]{0, 0, 99999};

        int[] L = solve(node.left);
        int[] R = solve(node.right);
        int mL12 = Math.min(L[1], L[2]);
        int mR12 = Math.min(R[1], R[2]);

        int d0 = L[1] + R[1];
        int d1 = Math.min(L[2] + mR12, R[2] + mL12);
        int d2 = 1 + Math.min(L[0], mL12) + Math.min(R[0], mR12);
        return new int[]{d0, d1, d2};
    }

    private static int k;

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (data == null || data.length () == 0) {
            return null ;
        }
        String [] val = data.split(",");
        TreeNode root = new TreeNode (Integer.parseInt (val[0]));
        Queue<TreeNode> q = new LinkedList<> ();
        q.offer(root) ;
        for (int i = 1 ; i < val.length ; i += 2) {
            TreeNode cur = q.poll();
            if (!"null".equals(val[i])) {
                TreeNode left = new TreeNode (Integer.parseInt (val[i]));
                cur.left = left ;
                q.offer (left);
            }
            if (i + 1 < data.length() && !"null".equals(val[i + 1])) {
                TreeNode right = new TreeNode (Integer.parseInt (val[i + 1]));
                cur.right = right ;
                q.offer (right);
            }
        }
        return root ;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
