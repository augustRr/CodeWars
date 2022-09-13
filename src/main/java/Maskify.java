import javax.lang.model.SourceVersion;
import java.util.Arrays;

public class Maskify {

    public static void main(String[] args) {
        System.out.println("result = " + maskify("sdsdsxxxfsfsfr"));
    }

    public static String maskify(String str) {

        if(str.length()>4){
            char[] chars=new char[str.length()-4];
            Arrays.fill(chars,'#');
            return new String(chars)+str.substring(str.length()-4,str.length());
        }
        else{
            return str;
        }

    }

}
/*
public class Maskify {
    public static String maskify(String str) {
        return str.replaceAll(".(?=.{4})", "#");
    }
}
public class Maskify {
    public static String maskify(String str) {
        return str.length()<=4 ? str : str.substring(0,str.length()-4).replaceAll(".","#") + str.substring(str.length()-4);
    }
}
class Maskify {
  static String maskify(String str) {
    return str.length() < 5 ? str : "#".repeat(str.length() - 4) + str.substring(str.length() - 4);
  }
}
 */