import java.util.*;

public class AdventOfCode02 {

    public String start02(String input) {
        System.out.println("Starting Advent of Code 02 ...");

        List<String> inputList = new ArrayList<>(Arrays.asList(input.split("\n")));

         // über die Liste
        for (int i = 0; i < inputList.size(); i++) {


            for (int wordIndex = 0; wordIndex < inputList.get(i).length(); wordIndex++) {

                StringBuilder root = new StringBuilder(inputList.get(i));

                root.setCharAt(wordIndex, '0');

                for (int j = 0; j < inputList.size(); j++) {
                    StringBuilder target = new StringBuilder(inputList.get(j));

                    target.setCharAt(wordIndex, '0');

                    if (!inputList.get(j).equals(inputList.get(i))) {

                        if (root.toString().equals(target.toString())) {

                            root.replace(wordIndex, wordIndex + 1, "");

                            return root.toString();
                        }
                    }


                }
            }

    }
        return null;

}
}
