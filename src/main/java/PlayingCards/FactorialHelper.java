package PlayingCards;

// JAVA program to compute factorial
// of big numbers
public class FactorialHelper{

    // This function finds factorial of
    // large numbers and prints them
    static int[] factorial(int n)
    {
        int res[] = new int[100];

        // Initialize result
        res[0] = 1;
        int res_size = 1;

        // Apply simple factorial formula
        // n! = 1 * 2 * 3 * 4...*n
        for (int x = 2; x <= n; x++)
            res_size = multiply(x, res, res_size);

        return res;
    }

    // This function multiplies x with the number
    // represented by res[]. res_size is size of res[] or
    // number of digits in the number represented by res[].
    // This function uses simple school mathematics for
    // multiplication. This function may value of res_size
    // and returns the new value of res_size
    static int multiply(int x, int res[], int res_size)
    {
        int carry = 0; // Initialize carry

        // One by one multiply n with individual
        // digits of res[]
        for (int i = res.length-1; i >= res.length-res_size; i--) {
            int prod = res[i] * x + carry;
            res[i] = prod % 10; // Store last digit of
            // 'prod' in res[]
            carry = prod / 10; // Put rest in carry
        }

        // Put carry in res and increase result size
        while (carry != 0) {
            res_size++;
            res[res.length-res_size] = carry % 10;
            carry = carry / 10;

        }
        return res_size;
    }


    public static int divide(int[] numberOn10, int[] factorialVal) {
        int factSize=findSizeOfFact(factorialVal);
        int result=0;
        while(PlayingCards.bigNumCompare(numberOn10,factorialVal)){
            result++;
            factSize=multiply(result,factorialVal,factSize);
        }
        return result;
    }

    public static int findSizeOfFact(int[] factorialVal) {
        int size=factorialVal.length;
        while(factorialVal[factorialVal.length-size]==0){
            size--;
        }
        return size;
    }

    public static void subtract(int[] numberOn10, int[] arrToSubtract) {
        int carry = 0; // Initialize carry

        for (int i = numberOn10.length-1; i >= 0 ; i--) {
            int res = numberOn10[i] - arrToSubtract[i] - carry;
            numberOn10[i] = res % 10;
            carry = res<0?1:0;
        }

    }
}
// This code is produced with some changes by the code contributed by Nikita Tiwari

