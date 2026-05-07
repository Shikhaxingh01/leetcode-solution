class Solution {
    public int[] maxValue(int[] nums) {
        int n=nums.length;

        int[] prefixmax=new int[n];
        int[] suffixmin=new int[n];

        prefixmax[0]=nums[0];

        for(int i=1;i<n;i++){
            prefixmax[i]=Math.max(prefixmax[i-1],nums[i]);
        }

        suffixmin[n-1]=nums[n-1];

        for(int i=n-2;i>=0;i--){
            suffixmin[i]=Math.min(suffixmin[i+1],nums[i]);
        }

        int[] ans =new int[n];

        ans[n-1]=prefixmax[n-1];

        for(int i=n-2;i>=0;i--){
            if(prefixmax[i]>suffixmin[i+1]){
                ans[i]=ans[i+1];
            }else{
                ans[i]=prefixmax[i];
            }
        }

        return ans;
        
    }
}