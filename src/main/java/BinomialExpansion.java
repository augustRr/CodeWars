public class BinomialExpansion {
    public static void main(String[] args) {
        System.out.println("result:"+expand("(-6m-10)^2"));
    }

    public static String expand(String expr) {

            if(checkIfInvalid(expr))return null;

            int openParIndex=expr.indexOf('(');
            int operIndex=findOperator(expr);
            int closeIndex=expr.indexOf(')');
            int coef=0;
            if(expr.charAt(openParIndex+1)=='-') {
                coef = (-1) * ((openParIndex + 2 == operIndex - 1) ? 1 : Integer.parseInt(
                        expr.substring(openParIndex + 2, operIndex - 1)));

            }else {
                coef = (openParIndex + 1 == operIndex - 1) ? 1 : Integer.parseInt(
                        expr.substring(openParIndex + 1, operIndex - 1));
            }
            int secNum=Integer.parseInt(expr.substring(operIndex,closeIndex));
            int powerNum=Integer.parseInt(expr.substring((closeIndex+2)));
            char var=expr.charAt(operIndex-1);

            return process(coef,var,secNum,powerNum);
    }

    private static boolean checkIfInvalid(String str) {
        if(str.length()<7) return true;
        if(str.charAt(0)!='(') return true;
        //if(!str.matches("(-?\d+[a-zA-Z][+-]\d)^\d+")) return true;
        return false;
    }

    private static String process(int coef,char var,int secNum,int powerNum){
            StringBuilder strBuilder=new StringBuilder();
            if(powerNum==0){
                return "1";
            }
            if(secNum==0){
                return  ((long)Math.pow(coef,powerNum))+""+var+"^"+powerNum;
            }

            for(int i=0;i<=powerNum;i++){
               long tempCoefUnfactored=(long)(Math.pow(coef,powerNum-i)*Math.pow(secNum,i));
               long tempCoef=tempCoefUnfactored*findFactor(powerNum,i);
                String tempStrCoef=String.valueOf(tempCoef);

                if((i==0||tempCoef<0)&&!(tempCoef==1||tempCoef==-1)){
                    strBuilder.append(tempStrCoef);
                }
                else if((i==0||tempCoef<0)&&(tempCoef==1)){
                   //pass if first coef +1
                }
                else if(tempCoef==1) {
                   strBuilder.append("+");//write nothing for coefficient 1
                    if(i==powerNum){
                        strBuilder.append("1");  // for last element  "....+1"
                    }
                }
                else if (tempCoef==-1) {
                    strBuilder.append("-");
                    if(i==powerNum){
                        strBuilder.append("1");  // for last element  "....+1"
                    }

                }
                else {
                    strBuilder.append("+"+tempStrCoef);
                }

                if((powerNum-i)>=1) {
                    if(powerNum-i==1) {
                        strBuilder.append(var);
                    }
                    else{
                        strBuilder.append(var + "^");
                        strBuilder.append(powerNum-i);
                    }
                }
            }
            return strBuilder.toString();
    }

    private static long findFactor(int powerNum, int elemCount) {
        long numerator=1;
        long denominator=1;
        for(int i=0;i<Math.min(powerNum-elemCount,elemCount);i++){
            numerator*=(powerNum-i);
            denominator*=(i+1);
        }
        return  (numerator/denominator);
    }

    private static int findOperator(String expr) {
        int result=expr.indexOf('+');
        if(result<0){
            result=expr.lastIndexOf('-');
        }
        return result;
    }
}

/*
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KataSolution {
  public static String expand(String expr) {
    Pattern pattern = Pattern.compile("\\((-?\\d*)(.)([-+]\\d+)\\)\\^(\\d+)");
    Matcher matcher = pattern.matcher(expr);
    matcher.find();

    final String _a = matcher.group(1);
    final int a = _a.isEmpty() ? 1 : _a.equals("-") ? -1 : Integer.parseInt(_a);
    final String x = matcher.group(2);
    final int b = Integer.parseInt(matcher.group(3).replace("+", ""));
    final int n = Integer.parseInt(matcher.group(4).replace("+", ""));
    double f = Math.pow((double) a, n);

    if (n == 0)
      return "1";
    if (a == 0)
      return String.format("%d", (int) Math.pow((double) b, n));
    if (b == 0)
      return String.format("%d%s%s", (int) f, x, (n > 1) ? String.format("^%d", n) : "");

    final StringBuilder result = new StringBuilder();
    for (int i = 0; i <= n; i++) {
      if (f > 0 && i > 0)
        result.append("+");
      if (f < 0)
        result.append("-");
      if (i > 0 || f * f > 1)
        result.append((long) Math.abs(f));
      if (i < n)
        result.append(x);
      if (i < n - 1)
        result.append(String.format("^%d", n - i));
      f = f * (n - i) * b / (double) a / (double) (i + 1);
    }

    return result.toString();
  }
}
 */