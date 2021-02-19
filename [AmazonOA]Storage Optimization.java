public class Solution {
    public int storageOpt(int n, int m, int[] h, int[] v) {
        if (n == 0 || m == 0) {
            return 0;
        }

        Arrays.sort(h);
        Arrays.sort(v);

        int hMax = getMaxConsec(h);
        int vMax = getMaxConsec(v);

        return (hMax + 1) * (vMax + 1);
    }

    private int getMaxConsec(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int len = arr.length;
        int res = 1;
        int maxRes = 1;
        for (int i = 1; i < len; i++) {
            if (arr[i] == arr[i - 1] + 1) {
                res++;
            } else {
                res = 1;
            }
            maxRes = Math.max(maxRes, res);
        }

        return maxRes;
    }
}