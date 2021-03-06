// 102. Binary Tree Level Order Traversal
class Solution {
    // 层次遍历模板，也就是BFS
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 注意 size 要先在for循环外面取得
            int size = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; ++i) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(level);
        }

        return res;
    }
}

class Solution {
    // DFS
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, root, 0);
        return res;
    }

    public void dfs(List<List<Integer>> list, TreeNode root, int level) {
        if (root == null) {
            return;
        }
        // 如果当前的层次与二维数组大小一致，说明需要添加一个数组进去作为下一层
        if (level == list.size()) {
            list.add(new ArrayList<Integer>());
        }

        // 放在分治两个dfs前面进行操作也行
        // 取当前属于层次的数组添加元素
        // list.get(level).add(root.val);

        dfs(list, root.left, level + 1);
        dfs(list, root.right, level + 1);

        // 取当前属于层次的数组添加元素
        list.get(level).add(root.val);
    }
}