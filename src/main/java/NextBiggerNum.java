
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class NextBiggerNum {
    public static void main(String[] args) {
        System.out.println("nextBiggerNumber = " + nextBiggerNumber(4895876));
    }

    public static long nextBiggerNumber(long input)
    { long inputHolder=input;
        boolean isDigitFound=false;
        int counter=0;
        List<Long> digits=new LinkedList<>();
        while(!isDigitFound){
            long currNum=input; if(input==0) return -1;
            digits.add(input%10);
            if(digits.stream().anyMatch(integer -> integer > currNum%10)){
                isDigitFound=true;
            }
            input/=10;
        }
        long result= (long) (input*Math.pow(10, digits.size()));


        while(counter<digits.size()&& digits.get(counter)<=digits.get(digits.size()-1)){counter++;}
        result += digits.remove(counter)*Math.pow(10,digits.size());
        Collections.sort(digits);

        while(digits.size()>0){
                result += (digits.remove(0) * Math.pow(10, digits.size()));
        }


        return result>inputHolder ? result :-1 ;
    }

}

/*
import java.util.Arrays;

public class Kata {
    public static long nextBiggerNumber(long n) {
        byte[] a = (n + "").getBytes();
        for (int l = a.length, i = l - 2; i >= 0; i--)
            for (int p = l - 1; p > i; p--)
                if (a[i] < a[p]) {
                    a[i] = (byte) ((a[i] + a[p]) - (a[p] = a[i]));
                    Arrays.sort(a, i + 1, l);
                    return Long.parseLong(new String(a));
                }
        return -1;
    }
}
 */