import java.util.*;

class Solution {
    public int minimumDistance(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        // Step 1: store indices
        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        
        int ans = Integer.MAX_VALUE;
        
        // Step 2: process each value
        for (List<Integer> list : map.values()) {
            if (list.size() < 3) continue;
            
            // sliding window of size 3
            for (int i = 0; i + 2 < list.size(); i++) {
                int first = list.get(i);
                int third = list.get(i + 2);
                
                int dist = 2 * (third - first);
                ans = Math.min(ans, dist);
            }
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}