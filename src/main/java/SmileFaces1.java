import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.List;
import java.util.Arrays;

public class SmileFaces1 {
    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        list.add(":))-;~D");
        list.add("tt:D-;~)");

        System.out.println(" count= " + countSmileys(list) );
    }
        public static int countSmileys(List<String> arrow) {


            final Pattern pattern = Pattern.compile("/[:;][-~]?[D)]/g");

            int count = 0;
            for (String x : arrow) {
                Matcher matcher = pattern.matcher(x);

                while(matcher.find()) {
                    count++;
                }
            }

            return count;
        }

    }

/*
import java.util.*;

public class SmileFaces {

  public static int countSmileys(List<String> arr) {
    return (int)arr.stream().filter( x -> x.matches("[:;][-~]?[)D]")).count();
  }
}

import java.util.List;

class SmileFaces {
  public static int countSmileys(List<String> arr) {
      return arr.stream().mapToInt( w -> w.matches("^[:;][-~]?[)D]$") ? 1 : 0 ).sum();
  }
}
 */