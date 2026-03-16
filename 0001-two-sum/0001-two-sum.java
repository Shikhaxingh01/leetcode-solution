class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map=new HashMap<>();
        //int ans[]=new int[2];
        for(int i=0;i<nums.length;i++){
        int two=target-nums[i];
        if(map.containsKey(two)){
            return new int[]{map.get(two),i};
        }
        map.put(nums[i],i);
        }
           return new int[]{-1,-1};
    }
}