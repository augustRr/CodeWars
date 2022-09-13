import java.util.*;
public class Metro {
    public static void main(String[] args) {
        System.out.println(countPassengers(createStopList()));
    }

    private static List<int[]> createStopList() {
        List<int[]> stopList=new ArrayList<>();
        stopList.add(new int[]{7,0});
        stopList.add(new int[]{5,2});
        stopList.add(new int[]{6,3});
        stopList.add(new int[]{4,2});
        stopList.add(new int[]{2,5});
        stopList.add(new int[]{2,6});
        return stopList;
    }

    public static int countPassengers(List<int[]> stops) {
        //Code here!
        return stops.stream().mapToInt(x -> x[0] - x[1]).sum();
    }
}
