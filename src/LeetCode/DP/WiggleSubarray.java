package LeetCode.DP;

public class WiggleSubarray {

    public static int wiggleMaxLength(int[] nums) {
        int n = nums.length;

        if (n == 0) return 0;

        int[] up = new int[n];
        int[] down = new int[n];

        up[0] = 1;
        down[0] = 1;

        int max = 1;

        for (int i = 1; i < n; i++) {

            up[i] = up[i - 1];
            down[i] = down[i - 1];

            if (nums[i] > nums[i - 1]) {
                up[i] = down[i - 1] + 1;
            }
            else if (nums[i] < nums[i - 1]) {
                down[i] = up[i - 1] + 1;
            }

            max = Math.max(max, Math.max(up[i], down[i]));
        }

        return max;
    }

    public static void main(String[] args) {
        int[] nums = {1, 7, 4, 9, 2, 5, 8, 3};

        System.out.println(wiggleMaxLength(nums));
    }
}

