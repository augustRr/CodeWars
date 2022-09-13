import java.util.*;

public class biggestNum {
    public static void main(String[] args) {
        System.out.println("nextBiggerNum = " + biggestNumber(234)) ;
    }

    public static long biggestNumber(long n)
    {
            long tempNum=n;
            long result=0;
            int curDigitNum=0;
            List<Long> digits=new ArrayList<>();
            for(;Math.pow(10,curDigitNum)<n;curDigitNum++){
                digits.add((long) (tempNum%10));
                tempNum-=(tempNum%10);
                tempNum/=10;
            }
            Collections.sort(digits);
            for(int i=0;i<digits.size();i++){
                result+=digits.get(i)*Math.pow(10,i);
            }
            if(result==n)
                return -1;
            else{
                return result;
            }
    }

}
