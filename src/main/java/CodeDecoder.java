import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class CodeDecoder {
    public static HashMap <String,Character> MorseCode=new HashMap<>();
    public static void createHashMap(){
        MorseCode.put("....",'J');MorseCode.put("..-.",'U');MorseCode.put("_--_",'D');
    }

        public static String decode(String morseCode) {
            // your brilliant code here, remember that you can access the preloaded Morse code table through MorseCode.get(code)
            List<Character> currCode=new ArrayList<>();
            byte blankSpaceCounter=0;
            StringBuilder result=new StringBuilder();
            for(char c:morseCode.toCharArray()){
                if(c==' '&& blankSpaceCounter==0) {
                    blankSpaceCounter++;
                    if(currCode.size()>0){
                        Character decoded = MorseCode.get(currCode.toString().substring(1, 3 * currCode.size() - 1)
                                .replaceAll(", ", ""));
                        result.append(decoded);
                        currCode.clear();
                    }
                }
                else if(c==' '){
                    blankSpaceCounter++;
                    if(blankSpaceCounter==3){
                        if(result.length()>0){
                            result.append(" ");
                        }
                    }
                }
                else{
                    currCode.add(c);
                    if( blankSpaceCounter>0){
                        blankSpaceCounter=0;
                    }
                }
            }
            if(currCode.size()>0)
                result.append(MorseCode.get(currCode.toString().substring(1, 3 * currCode.size() - 1)
                        .replaceAll(", ", "")));
            return result.toString();
        }


    public static void main(String[] args) {
        createHashMap();
        System.out.println(" Code= " +decode(".... ..-.  _--_   _--_ ...."));
    }
    }

/*
public class MorseCodeDecoder {
    public static String decode(String morseCode) {
      String result = "";
      for(String word : morseCode.trim().split("   ")) {
        for(String letter : word.split("\\s+")) {
          result += MorseCode.get(letter);
        }
        result += ' ';
      }
      return result.trim();
    }
}

import java.util.Arrays;
import java.util.stream.Collectors;

public class MorseCodeDecoder {

    public static String decode(String morseCode) {
        return Arrays.stream(morseCode.trim().split("   "))
                .map(MorseCodeDecoder::decodeWord)
                .collect(Collectors.joining(" "));
    }

    private static String decodeWord(String word) {
        return Arrays.stream(word.split(" ")).map(MorseCode::get).collect(Collectors.joining());
    }
}
 */