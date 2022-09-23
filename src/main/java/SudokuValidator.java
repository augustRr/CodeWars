import java.util.HashMap;
import java.util.Map;

public class SudokuValidator {
    public static Map<Integer,Boolean> check1to9;
    static{
        check1to9=new HashMap<>();
        resetMap();
    }
    public static void resetMap(){
        check1to9.put(1,false);
        check1to9.put(2,false);
        check1to9.put(3,false);
        check1to9.put(4,false);
        check1to9.put(5,false);
        check1to9.put(6,false);
        check1to9.put(7,false);
        check1to9.put(8,false);
        check1to9.put(9,false);
    }
    public static boolean check(int[][] sudoku) {
        resetMap();
        for(int i=0;i<9;i++){
            for(int k=0;k<9;k++){
                if(check1to9.get(sudoku[i][k])!=null&&check1to9.get(sudoku[i][k])==false){
                    check1to9.put(sudoku[i][k],true);
                }
                else return false;
            }
            for(int j=0;j<9;j++){
                if(check1to9.get(sudoku[j][i])!=null&&check1to9.get(sudoku[j][i])==true){
                    check1to9.put(sudoku[j][i],false);
                }
                else return false;
            }
        }
        if(checkBoxes(sudoku)==false){
            return false;
        }

        return true;
    }
    private static boolean checkBoxes(int[][] sudoku){
        resetMap();
        for(int rowBox=0;rowBox<3;rowBox++) {
            for (int colBox = 0; colBox < 3; colBox++) {
                for (int eachBoxRow = 0; eachBoxRow < 3; eachBoxRow++) {
                    for (int eachBoxCol = 0; eachBoxCol < 3; eachBoxCol++) {

                        if (check1to9.get(sudoku[rowBox * 3 + eachBoxRow][colBox * 3 + eachBoxCol]) != null && check1to9.get(sudoku[rowBox * 3 + eachBoxRow][colBox * 3 + eachBoxCol]) == false) {
                            check1to9.put(sudoku[rowBox * 3 + eachBoxRow][colBox * 3 + eachBoxCol], true);
                        } else return false;
                    }
                }
                resetMap();
            }
        }
        return true;
    }
}