import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class findEvenElement {
    public static void main(String[] args) {
        System.out.println("Result="+findEvenIndex(new int[] {1,2,3,4,3,2,1}));
    }
    public static int findEvenIndex(int[] arr) {
        // your code
        HashMap<Integer,Long> numsAndCurrTotal=new HashMap<>();
        numsAndCurrTotal.put(0, (long) arr[0]);
        final AtomicInteger counter = new AtomicInteger(1);

       Arrays.stream(arr).skip(1).forEach( i->numsAndCurrTotal.put(counter.getAndIncrement(),(long)(i+ numsAndCurrTotal.get(counter.get()-2))));
       Optional<Integer> result=numsAndCurrTotal.keySet().stream().sorted()
           .filter( a->  numsAndCurrTotal.get(a)-arr[a] == numsAndCurrTotal.get(arr.length-1)-numsAndCurrTotal.get(a)).findFirst();
       return result.orElse(-1);

    }
}
/*
import java.util.stream.IntStream;

public class Kata {
  public static int findEvenIndex(int[] arr) {
    return IntStream.range(0, arr.length)                                   //All the sum calculations made separately each time
        .filter(n -> IntStream.of(arr).limit(n).sum() == IntStream.of(arr).skip(n + 1).sum())
        .findFirst().orElse(-1);
  }
}
 */