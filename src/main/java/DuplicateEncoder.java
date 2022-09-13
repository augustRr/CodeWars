import java.util.HashSet;

public class DuplicateEncoder {
    public static void main(String[] args) {
        System.out.println("result:"+encode("Prespecialized")+"result2:"+encode("dsfd12315fqf"));
    }
        static String encode(String word){
            HashSet <Character> recordedChars=new HashSet<>();
            HashSet <Character> duplicates=new HashSet<>();
            StringBuffer result=new StringBuffer();
            for(char c:word.toCharArray()){
                if(recordedChars.contains(Character.toLowerCase(c)))
                    duplicates.add(Character.toLowerCase(c));
                recordedChars.add(Character.toLowerCase(c));

            }
            for(char c:word.toCharArray()){
                if(duplicates.contains(Character.toLowerCase(c)))
                    result.append(")");
                else
                    result.append("(");
            }
            return result.toString();
        }
}
/*
public class DuplicateEncoder {
  static String encode(String word){
    word = word.toLowerCase();
    String result = "";
    for (int i = 0; i < word.length(); ++i) {
      char c = word.charAt(i);
      result += word.lastIndexOf(c) == word.indexOf(c) ? "(" : ")";
    }
    return result;
  }
}

import java.util.stream.Collectors;

public class DuplicateEncoder {
  static String encode(String word){
        return word.toLowerCase()
                   .chars()
                   .mapToObj(i -> String.valueOf((char)i))
                   .map(i -> word.toLowerCase().indexOf(i) == word.toLowerCase().lastIndexOf(i) ? "(" : ")")
                   .collect(Collectors.joining());
  }
}
 */