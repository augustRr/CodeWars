public class SquareConcat {
    public static void main(String[] args) throws NumberFormatException{
        int value=969;
        String strN=String.valueOf(value);
        StringBuilder SbResult=new StringBuilder ();
        for(char c: strN.toCharArray()){

            int sqrDgt=(int)Math.pow(Character.getNumericValue(c),2);
            SbResult.append(sqrDgt);
        }
        int result=Integer.parseInt(SbResult.toString());
        System.out.println("result = " + result);
    }

}/*import java.util.stream.Collectors;

public class SquareDigit {

    public int squareDigits(int n) {
        return Integer.parseInt(String.valueOf(n)
                                      .chars()
                                      .map(i -> Integer.parseInt(String.valueOf((char) i)))
                                      .map(i -> i * i)
                                      .mapToObj(String::valueOf)
                                      .collect(Collectors.joining("")));
    }

}
public class SquareDigit {

  public int squareDigits(int n) {

    String strDigits = String.valueOf(n);
    String result = "";

    for (char c : strDigits.toCharArray()) {
      int digit = Character.digit(c, 10);
      result += digit * digit;
    }

    return Integer.parseInt(result);
  }

}
*/




