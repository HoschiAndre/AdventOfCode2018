import java.util.*;

public class AdventOfCode02 {

    public int start02(String input) {
        System.out.println("Starting Advent of Code 01 ...");

        List<String> inputList = new ArrayList<>(Arrays.asList(input.split("\n")));
        Set<Integer> resultTwice = new HashSet<>();

        int result = 0;
        boolean twice = true;
        int i = 0;

        for (; ; ) {

            if (i == inputList.size()) {
                i = 0;
            }

            result = result + Integer.parseInt(inputList.get(i));

            twice = resultTwice.add(result);

            if (twice == false) {
                return result;
            }

            i++;
        }
    }

}
