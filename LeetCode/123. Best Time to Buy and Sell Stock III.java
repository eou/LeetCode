// 123. Best Time to Buy and Sell Stock III
// at most 2 transactions
// time O(n), space O(n)
// find max profit before and after day i,  <= i =>
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        
        int[] left = new int[prices.length];    // left[i] means max profit before day i
        int[] right = new int[prices.length];   // right[i] means max profit after day i

        // minimum price before day i
        int min = prices[0];
        for (int i = 0; i < prices.length; ++i) {
            min = Math.min(min, prices[i]);
            if (i > 0) {
                left[i] = Math.max(left[i - 1], prices[i] - min);
            }
        }
        
        // maximum price after day i
        int max = prices[prices.length - 1];
        for (int i = prices.length - 1; i >= 0; --i) {
            max = Math.max(max, prices[i]);
            if (i < prices.length - 1) {
                right[i] = Math.max(right[i + 1], max - prices[i]);
            }
        }
        
        int res = 0;
        for (int i = 0; i < prices.length; ++i) {
            res = Math.max(res, left[i] + right[i]);
        }
        
        return res;
    }
}

// Using only 1 array, calculate result in second for loop
// get max profit before day i, then get max profit after day i, <= i =>
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int maxTotalProfit = 0;
        List<Integer> firstBuySellProfits = new ArrayList<>();
        int minPriceSoFar = Integer.MAX_VALUE;

        // 1. Forward phase. For each day, we record maximum profit if we sell on that day.
        for (int i = 0; i < prices.length; ++i) {
            minPriceSoFar = Math.min(minPriceSoFar, prices[i]);
            maxTotalProfit = Math.max(maxTotalProfit, prices[i] - minPriceSoFar);
            firstBuySellProfits.add(maxTotalProfit);
        }

        // 2. Backward phase. For each day, find the maximum profit if we make the second buy on that day.
        int maxPriceSoFar = Integer.MIN_VALUE;
        for (int i = prices.length - 1; i > 0; --i) {
            maxPriceSoFar = Math.max(maxPriceSoFar, prices[i]);
            maxTotalProfit = Math.max(maxTotalProfit, maxPriceSoFar - prices[i] + firstBuySellProfits.get(i - 1));
        }
        return maxTotalProfit;
    }
}