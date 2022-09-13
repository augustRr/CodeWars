import java.util.ArrayList;
import java.util.List;

public class SqInRect {
    public static void main(String[] args) {
        System.out.println(sqInRect(5,5));
    }
    public static List<Integer> sqInRect(int lng, int wdth) {
        // your code
        List<Integer> resultList=new ArrayList<>();
        if(lng==wdth){
            return null;
        }
        int areaUncovered=lng*wdth;
        while(areaUncovered>0){
            if(lng>wdth){
                resultList.add(wdth);
                areaUncovered-=wdth*wdth;
                lng-=wdth;
            }
            else{
                resultList.add(lng);
                areaUncovered-=lng*lng;
                wdth-=lng;
            }
        }
        return resultList;
    }
}
