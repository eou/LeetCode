// 377. Combination Sum IV
// 这个只需返回 results 个数，所以之前一一返回 subset 的算法显然重复计算了很多，会TLE
// DFS, TLE
class Solution {
    int result = 0;
    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        dfs(nums, target, 0);
        return result;
    }
    
    public void dfs(int[] nums, int target, int sum) {
        if (sum == target) {
            ++result;
            return;
        }
        
        for (int i = 0; i < nums.length; ++i) {
            if (sum + nums[i] <= target) {
                dfs(nums, target, sum + nums[i]);
            }
        }
    }
}

// DFS with memo
class Solution {
    private Map<Integer, Integer> map = new HashMap<>();
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0 || target <= 0) {
            return 0;
        } 
 
        return dfs(nums, target, 0, map);
    }
    
    private int dfs(int[] nums, int target, int sum, Map<Integer, Integer> map) {
        if (map.containsKey(target - sum)) {
            return map.get(target - sum);
        }

        if (sum > target) {
            return 0;
        }
        
        if (target == sum) {
            return 1;
        }
        
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res += dfs(nums, target, sum + nums[i], map);
        }
        
        map.put(target - sum, res);
        return res;
    }
}

class Solution {
    Map<Integer, Integer> map = new HashMap<>(); // sum : frequency
    public int combinationSum4(int[] nums, int target) {
        int count = 0; // result
        if (target < 0) {
            return 0;
        }
        if (target == 0) {
            return 1;
        }
        // 剪枝
        if (map.containsKey(target)) {
            return map.get(target);
        }
            
        for (int num : nums) {
            count += combinationSum4(nums, target - num);
        }

        map.put(target, count);
        return count;
    }
}

class Solution {
    // DP
    public int combinationSum4(int[] nums, int target) {
        // dp[i] means how many ways to get sum == i
        int[] dp = new int[target + 1];
        // initialization with smallest problems
        dp[0] = 1;
        // dp[0], dp[1], ...dp[target]
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }
}