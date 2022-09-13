import java.util.HashMap;

public class RomanNumerals {
    public static void main(String[] args) {
        System.out.println(RomanCoder.get(1000));
        System.out.println("Result:"+solution(2514));
        System.out.println("Result:"+solution(2514));
    }
    public static HashMap<Integer,Character> RomanCoder=new HashMap<>();
    static {
        RomanCoder.put(1,new Character('I'));
        RomanCoder.put(5,new Character('V'));
        RomanCoder.put(10,new Character('X'));
        RomanCoder.put(50,new Character('L'));
        RomanCoder.put(100,new Character('C'));
        RomanCoder.put(500,new Character('D'));
        RomanCoder.put(1000,new Character('M'));
    };
    public static String solution(int n) {
        StringBuilder strBldr=new StringBuilder();
        for(int i=1000;i>=1;i/=10){
            int denom=n/i;
            if(denom>8) {
                for (int x = denom; x < 10; x++) {
                    strBldr.append(RomanCoder.get(i));
                }
                strBldr.append(RomanCoder.get(i * 10));
            }
            else if(denom>=6) {
                strBldr.append(RomanCoder.get(i * 5));
                for (int x = denom; x > 5; x--) {
                    strBldr.append(RomanCoder.get(i));
                }
            }
            else if(denom>3){
                for(int x=denom;x<5;x++){
                    strBldr.append(RomanCoder.get(i));
                }
                strBldr.append(RomanCoder.get(i*5));
            }
            else{
                for (int a = 0; a < denom; a++) {
                    strBldr.append(RomanCoder.get(i));
                }
            }
            n-=denom*i;
        }
        return strBldr.toString();
    }
}
