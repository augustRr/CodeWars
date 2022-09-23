
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayingCards {
    int [] numberOn10;

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new PlayingCards().encode("A")));
    }

    private static final String [] deckOfCards=new String []{
            "AC", "2C", "3C", "4C", "5C", "6C", "7C", "8C", "9C", "TC", "JC", "QC", "KC",
            "AD", "2D", "3D", "4D", "5D", "6D", "7D", "8D", "9D", "TD", "JD", "QD", "KD",
            "AH", "2H", "3H", "4H", "5H", "6H", "7H", "8H", "9H", "TH", "JH", "QH", "KH",
            "AS", "2S", "3S", "4S", "5S", "6S", "7S", "8S", "9S", "TS", "JS", "QS", "KS"};


    public String[] encode(String message) {


        int [] numValOn27=new int[message.length()];
        for(int i=0;i<message.length();i++){
            if(message.charAt(i)==32){
                numValOn27[i]=0;
            } else if (message.charAt(i)>64&&message.charAt(i)<91) {
                numValOn27[i]=0;
            }
            numValOn27[i]=message.charAt(i)-64;
        }
        numberOn10=BaseHelper.calculateOnBase(27,numValOn27);   //optimization needed
        int start=findStartingPermDigit();



        String[] result=computePermutationTill(start);
        result=computePermutationAfter(start,result);
        return result;


    }

    private String[] computePermutationAfter(int start, String[] result) {
        List<String> remainingCards=new ArrayList<>();
        for(int i=start;i>0;i--){
            remainingCards.add(deckOfCards[52-i]);
        }
        for(int i=start;i>0;i--){
            int [] factorialVal=FactorialHelper.factorial(i-1);
            if(bigNumCompare(numberOn10,factorialVal)){
                int divisionByFact=FactorialHelper.divide(numberOn10,factorialVal);
                result[52-i]=remainingCards.remove(divisionByFact);

                FactorialHelper.multiply(
                        divisionByFact,
                        factorialVal,
                        FactorialHelper.findSizeOfFact(factorialVal));
                FactorialHelper.subtract(numberOn10,factorialVal );
            }else{
                result[52-i]= remainingCards.get(0);
            }
        }
        return result;
    }

    private String[] computePermutationTill(int start) {
        String [] resultTill=new String [52];
        if (52 - start >= 0) System.arraycopy(deckOfCards, 0, resultTill, 0, 52 - start);
        return resultTill;
    }

    private int findStartingPermDigit() {
        int counter=1;
        int numArrSize=1;
        int [] factArr=new int[70];
        factArr[factArr.length-1]=1;

        while(bigNumCompare(numberOn10,factArr)){
            counter++;
            numArrSize=FactorialHelper.multiply(counter,factArr,numArrSize);
        }
        return counter;
    }

    public static boolean bigNumCompare(int[] numberOn27, int[] numArr) {
        int counter=0;
        while(counter<numberOn27.length){
            if(numArr[counter]>numberOn27[counter]){
                return false;
            }else if(numArr[counter]<numberOn27[counter]){
                return true;
            }
            counter++;
        }
        return true;
    }

    /*public String decode(String[] deck) {
        PlayingCardsOutput.printDeck(deck, true); // Using unicode characters
        PlayingCardsOutput.printDeck(deck, false); // Using regular characters

        return "";
    }
     */
    public static class BaseHelper {
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

    public static class FactorialHelper{

        // This function finds factorial of
        // large numbers and prints them
        static int[] factorial(int n)
        {
            int res[] = new int[70];

            // Initialize result
            res[69] = 1;
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
            int [] numHolder=Arrays.copyOf(numberOn10,70);
            int counter=1;
            while(bigNumCompare(numHolder,factorialVal)){
                counter++;
                subtract(numHolder,factorialVal);
            }
            return --counter;
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
                numberOn10[i] =(res + 10) % 10;
                carry = res<0?1:0;
            }

        }
    }

}
