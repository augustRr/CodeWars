package PlayingCards;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayingCards {
    int [] numberOn10;

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new PlayingCards().encode("AA")));
    }
    /**
     * Takes a String containing a message, and returns an array of Strings representing
     * a deck of playing cards ordered to hide the message
     */
    private static String [] deckOfCards=new String []{
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
                int divisonByFact=FactorialHelper.divide(numberOn10,factorialVal);
                result[52-i]=remainingCards.get(divisonByFact);
                FactorialHelper.multiply(
                        divisonByFact,
                        factorialVal,
                        FactorialHelper.findSizeOfFact(factorialVal));
                FactorialHelper.subtract(numberOn10,factorialVal );
            }else{
                result[52-i]= remainingCards.get(start-i);
            }
        }
        return result;
    }

    private String[] computePermutationTill(int start) {
        String [] resultTill=new String [52];
        for(int i=0;52-start>i;i++){
            resultTill[i]=deckOfCards[i];
        }
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
            }
            counter++;
        }
        return true;
    }

    /**
     * Takes an array of Strings representing a deck of playing cards, and returns
     * the message that is hidden inside
     */
    /*public String decode(String[] deck) {
        PlayingCardsOutput.printDeck(deck, true); // Using unicode characters
        PlayingCardsOutput.printDeck(deck, false); // Using regular characters

        return "";
    }*/
}
