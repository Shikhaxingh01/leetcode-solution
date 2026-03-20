class Solution {
    public int numOfSubarrays(int[] arr) {
        int MOD = 1_000_000_007;

        int evenCount = 1; // empty prefix
        int oddCount = 0;

        int sum = 0;
        int result = 0;

        for (int num : arr) {
            sum += num;

            if (sum % 2 == 0) {
                // even sum → pair with odd prefixes
                result = (result + oddCount) % MOD;
                evenCount++;
            } else {
                // odd sum → pair with even prefixes
                result = (result + evenCount) % MOD;
                oddCount++;
            }
        }

        return result;
    }
}