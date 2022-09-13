import java.util.Arrays;

public class PigLatin {
    public static void main(String[] args) {
        System.out.println(pigIt("kl  jkljo :  :  mşihj"));
        System.out.println("trimResult:"+new String("   mk  "));

    }

    public static String pigIt(String str) {

        String [] words=str.split(" ");
        StringBuilder strBldr=new StringBuilder();
        Arrays.stream(words).filter(i ->!i.isBlank()).forEach(s->strBldr.append(convertToPiggish(s.strip())));

        return strBldr.toString().trim();

    }
    public static String convertToPiggish(String s){
        char firstChar=s.charAt(0);
        if((firstChar>64 && firstChar<91) || (firstChar>96 && firstChar<123)) {
            return s.substring(1) + s.charAt(0) + "ay ";
        }
        else{
            return s;

        }

    }
}
