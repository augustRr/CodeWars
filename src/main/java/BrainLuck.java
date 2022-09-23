import org.jetbrains.annotations.NotNull;

public class BrainLuck {
    private final String code;
    byte [] memoryTape;
    int dataP,insP,inputP;       //Pointers
    boolean isDataPZero;

    public static void main(String[] args) {

        System.out.println(new BrainLuck(",[.[-],]").process("Codewars" + ((char) 0)));
    }
    private void moveDataPForw() {      //>
        if(dataP<999) dataP++;
        //else throw new IndexOutOfBoundsException();
        insP++;
    }
    private void moveDataPBackw() {     //<
        if(dataP>0) dataP--;
        //else throw new IndexOutOfBoundsException();
        insP++;
    }
    private void incDataAtPointer(){            //+
        if(memoryTape[dataP]==127)memoryTape[dataP]=-128;
        else memoryTape[dataP]+=1;
        insP++;
    }
    private void decDataAtPointer(){            //-
        if(memoryTape[dataP]==-128)memoryTape[dataP]=127;
        else memoryTape[dataP]-=1;
        insP++;
    }
    private void accept(byte b){        //,
        memoryTape[dataP]=b;
        insP++; inputP++;
    }
    private void output(byte b, @NotNull StringBuilder result){
        result.append((char)b);
        insP++;
    }
    private void optionalForwJump(){
        int nextMatchIndex=0;
        if(memoryTape[dataP]==0) {
            nextMatchIndex = findNextMatch();
            if (nextMatchIndex == -1) {
                insP++;
            } else {
                insP = nextMatchIndex + 1;
            }
        }
        else insP++;
    }
    private void optionalBackJump(){
        int nextMatchIndex=0;
        if(memoryTape[dataP]!=0) {
            nextMatchIndex = findPrevMatch();
            if (nextMatchIndex == -1) {
                insP++;
            } else {
                insP = nextMatchIndex + 1;
            }
        }
        else insP++;
    }

    private int findPrevMatch() {
        for(int i=insP-1,bracketsNeeded=1;i>=0;i--){
            char [] chars=this.code.toCharArray();
            if(chars[i]=='['){
                bracketsNeeded--;
                if(bracketsNeeded==0)return i;
            } else if (chars[i]==']') {
                bracketsNeeded++;
            }
        }
        return -1;
    }

    private int findNextMatch () {
        for(int i=insP+1,bracketsNeeded=1;i<memoryTape.length;i++){
            char [] chars=this.code.toCharArray();
            if(chars[i]==']'){
                bracketsNeeded--;
                if(bracketsNeeded==0)return  i;
            } else if (chars[i]=='[') {
                bracketsNeeded++;
            }
        }
        return -1;
    }




    public BrainLuck(String code) {
        memoryTape=new byte [1000];
        this.code=code;

    }


    public String process(String input) {
        StringBuilder result=new StringBuilder();
        boolean isCompilationCompleted = false;
        while (!isCompilationCompleted) {
            switch(code.charAt(insP)){
                case(','):accept((byte)input.charAt(inputP));break;
                case('.'):output(memoryTape[dataP],result);break;
                case('['):optionalForwJump();break;
                case(']'):optionalBackJump();break;
                case('-'):decDataAtPointer();break;
                case('+'):incDataAtPointer();break;
                case('<'):moveDataPBackw();break;
                case('>'):moveDataPForw();break;
                //default:throw new IllegalArgumentException();
            }
            if(insP>=code.length()) isCompilationCompleted=true;
        }
        return result.toString();
    }
}