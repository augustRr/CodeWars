import java.util.*;
import java.util.regex.Pattern;


public class AlphabetWars {
    public static Map<Character,Character> transitionMap;
    private static Map<Character,Integer> scoreMap;

    public static String woLoLoooooo(String battlefield) {
        createTransMap();
        createScoreMap();
        Pattern pattern = Pattern.compile(("[^t][wpbs][j]|[t][mqdz][^j]|[j][wpbs][^t]|[^j][mqdz][t]"));
        Set<Integer> indexes =new HashSet<>();
        char [] result=battlefield.toCharArray();

        for(int currIndex=0;currIndex<battlefield.length()-2;currIndex++) {
            if(pattern.matcher(battlefield).region(currIndex, currIndex + 3).find()) {
                indexes.add(currIndex);

            }
        }

        handleTheEdges(battlefield,result);  //considering first and last 2 chars separately

        for(int i:indexes) {


            result[i + 1] = transitionMap.get(result[i + 1]);

        }
        int finalScore=0;

        for (char c:result) {
            if(scoreMap.get(c)!=null)          //if the letter converts to a value
                finalScore+=scoreMap.get(c);

        }
        if(finalScore==0){
            return "Let's fight again!";
        }
        else if(finalScore>0) {
            return "Right side wins!";
        }else{
            return "Left side wins!";
        }
    }
    private static void createScoreMap() {
        scoreMap=new HashMap<>();
        scoreMap.put('w',-4);
        scoreMap.put('p',-3);
        scoreMap.put('b',-2);
        scoreMap.put('s',-1);
        scoreMap.put('m',4);
        scoreMap.put('q',3);
        scoreMap.put('d',2);
        scoreMap.put('z',1);
    }

    public static void createTransMap(){
        transitionMap=new HashMap<>();
        transitionMap.put('w','m');
        transitionMap.put('p','q');
        transitionMap.put('b','d');
        transitionMap.put('s','z');
        transitionMap.put('m','w');
        transitionMap.put('q','p');
        transitionMap.put('d','b');
        transitionMap.put('z','s');

    }
    public static void handleTheEdges(String battlefield,char [] result){
        if(battlefield.length()>1&&(battlefield.charAt(1)=='t'||battlefield.charAt(1)=='j')) {
            if (transitionMap.get(result[0]) != null) {
                if((battlefield.charAt(1)=='t'&&scoreMap.get(battlefield.charAt(0))>0)){
                    result[0] = transitionMap.get(result[0]);
                } else if (battlefield.charAt(1) == 'j' && scoreMap.get(battlefield.charAt(0))<0) {
                    result[0] = transitionMap.get(result[0]);
                }

            }
        }

        if(battlefield.length()>1&&(battlefield.charAt(battlefield.length()-2)=='t'||battlefield.charAt(battlefield.length()-2)=='j')) {
            if (transitionMap.get(result[battlefield.length()-1]) != null) {
                if((battlefield.charAt(battlefield.length()-2)=='t'&&scoreMap.get(result[battlefield.length()-1])>0)){
                    result[battlefield.length()-1] = transitionMap.get(result[battlefield.length()-1]);
                } else if (battlefield.charAt(battlefield.length()-2) == 'j' && scoreMap.get(result[battlefield.length()-1])<0) {
                    result[battlefield.length()-1] = transitionMap.get(result[battlefield.length()-1]);
                }

            }
        }
        if(battlefield.length()==2&&battlefield.charAt(0)=='t'||battlefield.length()==2&&battlefield.charAt(0)=='j'){
            if (transitionMap.get(result[1]) != null) {
                if((battlefield.charAt(0)=='t'&&scoreMap.get(result[1])>0)){
                    result[1] = transitionMap.get(result[1]);
                } else if ((battlefield.charAt(0) == 'j' && scoreMap.get(result[1]) < 0)) {
                    result[1] = transitionMap.get(result[1]);
                }
            }
        }
    }
    public static void main(String[] args) {
        System.out.println(woLoLoooooo("jbmfdpfsbwqtjctabtd"));
    }
}
/*
public class AlphabetWars {

    public static String woLoLoooooo(String b) {
    int l = b.length();
    int sum = 0;
    for (int i = 0; i < l ; i++)
        if("sbpw".contains(""+b.charAt(i)))
                sum += ((b.substring(i == 0 ? 0 : i - 1, i >= l - 1 ? l : i + 2).matches("^j[sbpw][^t]?$|^[^t]?[sbpw]j$")) ? -1 : 1)
                    * "0sbpw".indexOf(b.charAt(i));
        else if("zdqm".contains(""+b.charAt(i)))
            sum += ((b.substring(i == 0 ? 0 : i - 1, i >= l - 1 ? l : i + 2).matches("^t[zdqm][^j]?$|^[^j]?[zdqm]t$")) ? 1 : -1)
                    * "0zdqm".indexOf(b.charAt(i));
    return sum > 0 ? "Left side wins!" : sum < 0 ? "Right side wins!" : "Let's fight again!";
    }
}

 */
///////////////////////////////////////////////////////////////////////////
// 
///////////////////////////////////////////////////////////////////////////
/*
public class AlphabetWars {

    public static String woLoLoooooo(String battlefield) {
        int totalPowerDiff = 0;
        for (int i = 0, a = 0, b = 0, c = 0, len = battlefield.length(); i < len + 2; i++, a = b, b = c) {
            c = i < len ? battlefield.charAt(i) : 0;
            int powerDiff = ("wpbs?zdqm".indexOf(b) - 4) % 5;
            boolean nextToT = (a == 't' || c == 't');
            boolean nextToJ = (a == 'j' || c == 'j');
            if (nextToT ^ nextToJ &&
                (nextToT && powerDiff > 0 || nextToJ && powerDiff < 0))
                powerDiff = -powerDiff;
            totalPowerDiff += powerDiff;
        }
        return
            totalPowerDiff < 0 ? "Left side wins!" :
            totalPowerDiff > 0 ? "Right side wins!" :
            "Let's fight again!";
    }

}
 */
///////////////////////////////////////////////////////////////////////////
// 
///////////////////////////////////////////////////////////////////////////
/*
import java.util.regex.Pattern;

public class AlphabetWars {
    private static final String left = "tsbpw";
    private static final String right = "jzdqm";
  
    public static String woLoLoooooo(String battlefield) {
      battlefield = Pattern.compile("(?<!t)[sbpw](?=j)|(?<=j)[sbpw](?!t)").matcher(battlefield).replaceAll(m -> right.charAt(left.indexOf(m.group())) + "");
      battlefield = Pattern.compile("(?<!j)[zdqm](?=t)|(?<=t)[zdqm](?!j)").matcher(battlefield).replaceAll(m -> left.charAt(right.indexOf(m.group())) + "");
      int r = battlefield.chars().map(c -> Math.max(left.indexOf(c), 0) - Math.max(right.indexOf(c), 0)).sum();
      return r > 0 ? "Left side wins!" : r < 0 ? "Right side wins!" : "Let's fight again!";
    }
}
 */