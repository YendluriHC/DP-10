//TC: O(k⋅nlogn)
//SC: O(k)
class Solution {
    public int superEggDrop(int k, int n) {
        // DP table where dp[i][j] represents the minimum number of moves
        // needed with i eggs and j floors.
        int[][] dp = new int[k + 1][n + 1];
        
        // Base case: If we have 1 egg, we must try every floor
        for (int j = 1; j <= n; j++) {
            dp[1][j] = j;
        }
        
        // Fill the DP table for each number of eggs and floors
        for (int i = 2; i <= k; i++) {
            int x = 1; // Binary search optimization starts with floor 1
            for (int j = 1; j <= n; j++) {
                // We increase x to find the best floor for the current dp[i][j]
                while (x < j && dp[i-1][x-1] < dp[i][j-x]) {
                    x++;
                }
                dp[i][j] = 1 + dp[i-1][x-1];
            }
        }
        // Return the minimum moves for k eggs and n floors
        return dp[k][n];
    }
}
