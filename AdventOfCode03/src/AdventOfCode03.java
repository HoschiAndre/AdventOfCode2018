import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;

public class AdventOfCode03 {

    private Map<String, Boolean> map = new HashMap<>();


    public int start03(String input) {
        System.out.println("Starting Advent of Code 03 ...");

        List<String> inputList = new ArrayList<>(Arrays.asList(input.split("\n")));

        for (int i = 0; i < inputList.size(); i++) {

            this.addToMap(inputList.get(i));

        }

        for (int i = 0; i < inputList.size(); i++) {

            boolean checkClaim = this.checkClaim(inputList.get(i));

            if (checkClaim == true) {
                int indexAT = inputList.get(i).indexOf("@");
                String ID = inputList.get(i).substring(1, indexAT - 1);

                return Integer.parseInt(ID);
            }

        }



        return this.countMap(this.map);
    }

    private boolean checkClaim(String str) {

        String key = this.getKey(str);

        String[] xyVar = key.split(",");

        int xVar = new Integer(xyVar[0]).intValue();
        int yVar = new Integer(xyVar[1]).intValue();

        String value = this.getValue(str);

        String[] xyCount = value.split("x");

        int xCount = new Integer(xyCount[0]).intValue();
        int yCount = new Integer(xyCount[1]).intValue();

        int xPos = xVar;
        int yPos = yVar;
        int xPosCount = xCount;
        int yPosCount = yCount;

        boolean check = false;
        boolean check2 = false;

        // x-Count
        do {

            // y-Count
            do {

                check = this.map.get(this.getStringKey(xPos, yPos));

                if (check) {
                    return false;
                }

                yPos++;
                yPosCount--;

            } while (yPosCount != 0);

            yPos = yVar;
            yPosCount = yCount;
            xPos++;
            xPosCount--;


        } while (xPosCount != 0);


        return true;

    }


    private void addToMap(String str) {

        String key = this.getKey(str);

        String[] xyVar = key.split(",");

        int xVar = new Integer(xyVar[0]).intValue();
        int yVar = new Integer(xyVar[1]).intValue();

        String value = this.getValue(str);

        String[] xyCount = value.split("x");

        int xCount = new Integer(xyCount[0]).intValue();
        int yCount = new Integer(xyCount[1]).intValue();


        this.putMap(xVar, yVar, xCount, yCount);



    }


    private void putMap (int xVar, int yVar, int xCount, int yCount) {

        int xPos = xVar;
        int yPos = yVar;
        int xPosCount = xCount;
        int yPosCount = yCount;


        // x-Count
        do {

            // y-Count
            do {

                if (this.checkField(xPos, yPos))
                    this.map.put(this.getStringKey(xPos, yPos), true);
                else
                    this.map.put(this.getStringKey(xPos, yPos), false);

                yPos++;
                yPosCount--;

            } while (yPosCount != 0);

            yPos = yVar;
            yPosCount = yCount;
            xPos++;
            xPosCount--;


        } while (xPosCount != 0);

        


    }

    private boolean checkField (int xVar, int yVar) {

        boolean isValue = this.map.containsKey(this.getStringKey(xVar, yVar));

        return isValue;

    }


    private String getKey(String str) {

        int begin = str.indexOf('@') + 2;
        int end = str.indexOf(':');

        return str.substring(begin, end);
    }

    private String getStringKey(int xVar, int yVar) {

        return String.valueOf(xVar) + "," + String.valueOf(yVar);
    }

    private int countMap(Map<String, Boolean> map) {

        int count = 0;

        for (Boolean value : map.values()) {
            if (value)
                count++;
        }
        return count;
    }

    private String getValue(String str) {

        return str.substring(str.indexOf(":") + 2);
    }

}
