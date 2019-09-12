# Binary Search

[Binary search algorithm](https://en.wikipedia.org/wiki/Binary_search_algorithm)

## Template

```java
class Solution {
    // non-recursive
    public int binarySearch(int[] nums, int target) {
        // preprocess
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int start = 0, end = nums.length - 1;
        // terminate condition
        while (start <= end) {
            // note that `(start + end) / 2` may overflow
            // or `start + (end - start) >> 1;`
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            else if (nums[mid] < target) {
                start = mid + 1; 
            }
            else {
                end = mid - 1;
            }
        }
        
        return -1;
    }
}
```

```java
class Solution {
    // recursive
    private int binarySearch(int[] nums, int start, int end, int target) {
        int mid = start + (end - start) / 2;
        
        if (nums[mid] == target) {
            return mid;
        }
        // note that `nums[mid] == target` has been checked. Thus if start == end, need to return
        // similer with non-recursion version: while (start < end) {... start = mid + 1; ... end = mid - 1; ...}
        if (start >= end) {
            return -1;
        }
        else if (nums[mid] < target) {
            return binarySearch(nums, mid + 1, end, target);
        }
        else {
            return binarySearch(nums, start, end - 1, target);
        }
    }
    
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        return binarySearch(nums, 0, nums.length - 1, target);
    }
}
```

## Termination

- `start <= end`：after breaking the loop, start - 1 = end，no target
- `start < end`：after breaking the loop, start = end，target only one element
- `start + 1 < end`：after breaking the loop, start + 1= end，need compare last two elements