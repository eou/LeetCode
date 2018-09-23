// 107. Binary Tree Level Order Traversal II
// 同102层次遍历，BFS，改动最后的results数组即可，要么尾部添加元素然后翻转，要么从头部添加元素
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
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
            results.add(0, level);
        }

        return results;
    }
}

class Solution {
    // 层次遍历也可以用DFS
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        levelMaker(results, root, 0);
        return results;
    }

    public void levelMaker(List<List<Integer>> list, TreeNode root, int level) {
        if (root == null) {
            return;
        }
        // 如果当前的层次与二维数组大小一致，说明需要添加一个数组进去作为下一层
        if (level == list.size()) {
            list.add(0, new ArrayList<Integer>());
        }

        levelMaker(list, root.left, level + 1);
        levelMaker(list, root.right, level + 1);

        // 取当前属于层次的数组添加元素
        list.get(list.size() - level - 1).add(root.val);
    }
}