
import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.JUnit4;
import java.util.Arrays;
import java.util.Random;
import static java.util.stream.Collectors.joining;

    public class SnailTest {

        @Test
        public void SnailTest1() {
            int[][] array
                    = {{1, 2, 3, 9},
                    {4, 5, 6, 4},
                    {7, 8, 9, 1},
                    {1, 2, 3, 4}};
            int[] r = {1, 2, 3, 9, 4, 1, 4, 3, 2, 1, 7, 4, 5, 6, 9, 8};
            test(array, r);
        }

        public String int2dToString(int[][] a) {
            return Arrays.stream(a).map(row -> Arrays.toString(row)).collect(joining("\n"));
        }

        public void test(int[][] array, int[] result) {
            String text = int2dToString(array) + " should be sorted to " + Arrays.toString(result);
            System.out.println(text);
            Assert.assertArrayEquals( result, Snail.snail(array));
        }


    }

