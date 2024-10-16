//TC: O(n^3)
//SC: O(n^2)

class Solution {
    public int maxCoins(int[] nums) {
      int n = nums.length;
        // Create a new array with padding 1 at both ends
        int[] paddedNums = new int[n + 2];
        paddedNums[0] = paddedNums[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            paddedNums[i + 1] = nums[i];
        }
        
        // DP table
        int[][] dp = new int[n + 2][n + 2];
        
        // Iterate over the length of the subproblems
        for (int length = 2; length <= n + 1; length++) {
            for (int left = 0; left <= n + 1 - length; left++) {
                int right = left + length;
                // Try every possible balloon to burst last between left and right
                for (int k = left + 1; k < right; k++) {
                    dp[left][right] = Math.max(dp[left][right], 
                        dp[left][k] + paddedNums[left] * paddedNums[k] * paddedNums[right] + dp[k][right]);
                }
            }
        }
        
        // The answer is the maximum coins we can get from the whole range (0, n+1)
        return dp[0][n + 1];
    }
}
