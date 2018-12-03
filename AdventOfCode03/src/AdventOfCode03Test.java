import org.junit.Assert;
import org.junit.Test;


public class AdventOfCode03Test {

    @Test
    public void start03Test() {
        AdventOfCode03 adventOfCode03 = new AdventOfCode03();

        String input = new String(
                "#1 @ 1,3: 4x4\n" +
                "#2 @ 3,1: 4x4\n" +
                "#3 @ 5,5: 2x2");

        String result = adventOfCode03.start03(input);

        Assert.assertEquals(result, "4");

    }


}
