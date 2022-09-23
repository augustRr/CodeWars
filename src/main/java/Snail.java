import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Snail {
    public static void main(String[] args) {
        System.out.println("Result="+ Arrays.toString(snail(new int[][]{
                {1, 2, 3, 9},
                        {4, 5, 6, 4},
                        {7, 8, 9, 1},
                        {1, 2, 3, 4}
        })));
    }
    public static int[] snail(int[][] array) {
        int counter=0;
        return sortSnail(array,counter,new int[array.length * array.length]);
    }
    /*
    counter=0 -> n//(n-2)//n//(n-2)=4n-4
     */

    private static int indexCount=0;
    private static int[] sortSnail(int[][] array, int counter, int[] resultArr) {
        if(array.length<2) return array[0];
        if(counter*2==array.length-1){
            resultArr[indexCount++]=array[array.length/2][array.length/2];
            return  resultArr;
        }

        for(int i=counter;i<array.length-counter;i++){///////////0->n///4n-4->5n-6//...top side
            if(indexCount<resultArr.length) resultArr[indexCount++]=array[counter][i];
        }
        for(int i=counter;i+2<array.length-counter;i++){///////////////n->2n-2// 5n-6->6n-10 //...right side wo corners
            if(indexCount<resultArr.length) resultArr[indexCount++]=array[i+1][array.length-(counter+1)];
        }
        for(int i=counter;i<array.length-counter;i++) {///////////////2n-2->3n-2//6n-10 -> 7n-
            if(indexCount<resultArr.length) resultArr[indexCount++]=array[array.length - (counter+1)][array.length-(i+1)];
        }
        for(int i=counter;i+2<array.length-counter;i++){//3n-2 -> 4n-4//
            if(indexCount<resultArr.length)resultArr[indexCount++]=array[array.length-(i+2)][counter];
        }
        if ((counter+1)<(array.length+1)/2)sortSnail(array,counter+1,resultArr);
        return resultArr;
    }
}
