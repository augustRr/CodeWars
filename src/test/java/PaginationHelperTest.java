import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;

public class PaginationHelperTest {
         // TODO: Replace examples and use TDD by writing your own tests
        PaginationHelper<Character> helper;
        PaginationHelper<Character> helper1;
        @BeforeAll
        void preparePaginationHelper(){

             helper = new PaginationHelper<Character>(Arrays.
                            asList('a', 'b', 'c', 'd', 'e', 'f'), 4);

             helper1 = new PaginationHelper<Character>(Arrays.
                            asList('s', 'd', 'f', 'd', 'g'), 1);
        }

        @Test
        void testSomething() {
            // assertEquals("expected", "actual");
            assertEquals(4,helper.pageItemCount(0));
                assertEquals(2,helper.pageItemCount(1));
                assertEquals(-1,helper.pageItemCount(2));
                assertEquals(-1,helper.pageItemCount(45));
                assertEquals(1,helper1.pageItemCount(0));
                assertEquals(1,helper1.pageItemCount(4));
                assertEquals(-1,helper1.pageItemCount(6));
                assertEquals(-1,helper1.pageItemCount(12));
        }
}


