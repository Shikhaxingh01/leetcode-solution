class Solution {
    public boolean isGood(int[] nums) {
        int n = nums.length;

        if(nums.length < 2) return false;
        cyclicSort(nums);

        for(int i =0; i < n - 1; i++){
            if(nums[i] != i + 1)return false;
        }

        return nums[n-1] == nums[n-2];
    }

    public static void cyclicSort(int arr[]){
        int i = 0;

        while(i < arr.length){
            int corr = arr[i] - 1;

            if(arr[i] > 0 && arr[i] < arr.length && 
             arr[i] != arr[corr]){

                int temp = arr[i];
                arr[i] = arr[corr];
                arr[corr] = temp;
            }else{
                i++;
            }
        }
    }
}