import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SmileFaces {
    public static void main(String[] args) {
        List<String> list=new ArrayList<String>();
        //list.add(":)"); list.add("XD"); list.add(":0}"); list.add("x:-"); list.add("):-"); list.add("D:");//1
        //list.add(";)p:");//1
        //list.add(";~d");
        list.addAll(Arrays.asList(new String []{";D) (~P, (), 5), ;xD, :X, 4), " +
                ":D, p-)), (p, :D, (~P, p~), pD, :), :dxD, ~8D, ~" +
                ":-), :-x, p5~D, (D, 8~D, d8oD, (D, px"}));
        //list.add(":)");//1
        //list.add("şşşşşşş:)ş");//1
        //list.add(":::):-D;~)ssd");//3
        //list.add("fldf:D;_D:~D");//2
        System.out.println(countSmileys(list));
    }
    public static int countSmileys(List<String> arr) {
        // Just Smile :)
        int smileCounter=0;
        boolean isSearchCompleted=false;
        for(String s:arr){
            isSearchCompleted=false;
            int index1=0;
            int index2=0;

            while (!isSearchCompleted){
                if(s.length()>index1&&((s.indexOf(':',index1))>-1)){
                    index1=(s.indexOf(':',index1)); index1++;

                    if(s.length()>index1&&((s.charAt(index1)=='-') || s.charAt(index1)=='~')){
                        if((s.length()>index1+1&&(s.charAt(index1+1)==')' || s.charAt(index1+1)=='D'))){
                            smileCounter++;
                        }
                    }
                    else if(s.length()>index1 &&( (s.charAt(index1)==')') || s.charAt(index1)=='D')){
                        smileCounter++;
                    }
                }

                else if(s.length()>index2 && ((s.indexOf(';',index2))>-1)){
                    index2=(s.indexOf(';',index2)); index2++;
                    if(s.length()>index2 && ((s.charAt(index2)=='-') || s.charAt(index2)=='~')){
                        if(s.length()>index2+1&&((s.charAt(index2+1)==')') || s.charAt(index2+1)=='D')){
                            smileCounter++;
                        }
                    }
                    else if(s.length()>index2&&((s.charAt(index2)==')') || s.charAt(index2)=='D')){
                            smileCounter++;
                        }
                }
                else{
                    isSearchCompleted=true;
                }
            }
        }
        return smileCounter;
    }
}
