// 300. Longest Increasing Subsequence
class Solution {
    // DP版本，时间复杂度 O(n^2)
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        dp[0] = 1;

        int max = 1;
        for (int i = 1; i < dp.length; i++) {
            int maxPreVal = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxPreVal = Math.max(maxPreVal, dp[j]);
                }
            }
            dp[i] = maxPreVal + 1;
            max = Math.max(max, dp[i]);
        }

        return max;
    }
}

class Solution {
    // 维护一个LIS的数组，用二分查找确定每个元素的位置，时间复杂度降到 O(nlogn)
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        
        // 也可以用数组，但是list添加元素比较直观
        List<Integer> list = new ArrayList<>();
        for(int n : nums) {
            int left = 0, right = list.size();
            while(left < right) {
                int mid = left + (right - left) / 2;
                if(list.get(mid) < n) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            if(left >= list.size()) {
                list.add(n);
            } else {
                list.set(left, n);
            }
        }
        
        return list.size();
    }
}