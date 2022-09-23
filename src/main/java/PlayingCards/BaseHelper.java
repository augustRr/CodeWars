package PlayingCards;

public class BaseHelper {
    public static int[] calculateOnBase(int base, int[] numberArr) {
        int[] result = new int[70];
        for (int i = numberArr.length - 1; 0 <= i; i--) {
            if (numberArr[i] != 0) {
                arrayAdd(base, i, numberArr,result);
            }
        }
        return result;

    }

    private static void arrayAdd(int base, int i, int[] numberArr,int [] stepResult) {
        int[] result = new int[70];
        result[69] = numberArr[i];
        int resultSize = 1;
        for (int j = 0; j < numberArr.length-i-1; j++) {

            resultSize = FactorialHelper.multiply(base, result, resultSize);
        }
        for (int k = 1; k < result.length; k++) {
            int add = result[result.length - k] + stepResult[stepResult.length - k];
            if (add != 0) {
                if (add >= 10) {
                    stepResult[stepResult.length - k - 1] += add / 10;
                    stepResult[stepResult.length - k] = add % 10;
                } else {
                    stepResult[stepResult.length-k] = add;
                }
            }
        }
    }

}
