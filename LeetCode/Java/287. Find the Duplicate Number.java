// 287. Find the Duplicate Number
class Solution {
    public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] == nums[i - 1]) {
                return nums[i];
            }
        }

        return -1;
    }
}

class Solution {
    public int findDuplicate(int[] nums) {
        Set<Integer> visited = new HashSet<>();
        for(int n : nums) {
            if(visited.contains(n)) {
                return n;
            }
            visited.add(n);
        }

        return -1;
    }
}

class Solution {
    // 快慢指针，类似 142. Linked List Cycle II    
    public int findDuplicate(int[] nums) {
        if(nums.length <= 1) {
            return -1;
        }
        
        int slow = nums[0];
        int fast = nums[nums[0]];
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        fast = 0;
        while(slow != fast) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }
}