import java.util.List;
import java.util.stream.Collectors;

public class StringSplit {
    public static void main(String[] args) {
        String[] strings=stringSplit("sdfsfrwewetwy");
        System.out.println("result = " + strings[0] + strings[3] +strings[4] +strings[5] +strings[6]);
    }

    private static String[] stringSplit(String str) {
       List<Character> resultList=str.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
       String [] result=new String[str.length()/2+(str.length()%2)];
       StringBuilder strBuilder=new StringBuilder();
       for(int i=0;i<str.length();i++){
           if(i%2==0){
               strBuilder.append(str.charAt(i));
           }
           else{
               strBuilder.append(str.charAt(i));
               result[(i-1)/2]=strBuilder.toString();
               strBuilder.delete(0,2);
           }
       }
        if(str.length()%2==1)
            result[str.length()/2]=str.charAt(str.length()-1)+"_";
        return  result;
    }
}
/*
public class StringSplit {
    public static String[] solution(String s) {
        s = (s.length() % 2 == 0)?s:s+"_";
        return s.split("(?<=\\G.{2})");
    }
}
 */