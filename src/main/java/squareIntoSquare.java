import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class squareIntoSquare {
    public static void main(String[] args) {
        System.out.println("Result:"+new squareIntoSquare().decompose(50));
    }
    //StringBuilder strBldr=new StringBuilder();
    List<Long> squares=new ArrayList<>();

    public String decompose(long n) {

        String currOutput="";
        for(long i=n-1;0<i;i--){

            long diff=(long)(Math.pow(n,2)-Math.pow(i,2));
            long sqrt=(long)Math.sqrt(diff);
            squares.add(i);                                                    //optimization with square sum equation
            if (diff==0 && checkIfArraySorted()){
                currOutput+=n+" ";
                if(isSearchCompleted(n)) {
                    return currOutput.trim();
                }
                else{
                    currOutput+=n+" ";
                }
            }
            else if (diff==0) {
                squares.clear();
                return null;
            }
            //currOutput=decompose(sqrt+1);
            if(currOutput==null){
                squares.remove(i);
            }
        }

        return null;

    }

    private boolean isSearchCompleted(long n) {
        if(!checkIfArraySorted())
            return false;
        long total=0;
        for(long l:squares){
            total+=l;
        }
        if(total!=((long)Math.pow(n,2))) {
            return false;
        }
        return true;
    }

    private boolean checkIfArraySorted() {
        for(int i=0;i<squares.size()-1;i++){
            if(squares.get(i)<=squares.get(i+1))
                return false;
        }
        return true;
    }

}
