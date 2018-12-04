import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

public class AdventOfCode04 {

    final String SLEEP = "falls asleep";
    final String WAKE = "wakes up";
    final String ID = "Guard #";


    public int start04(String input) {
        System.out.println("Starting Advent of Code 04 ...");

        List<String> inputList = new ArrayList<>(Arrays.asList(input.split("\n")));

        Map<LocalDateTime, String> inputMap = this.convertList(inputList);

        Map<Integer, Integer> mapMinutes = this.convertToMinutes(inputMap);

        int maxSleep = this.getMaxSleep(mapMinutes);

        int maxSleepID = this.getMaxSleepID(mapMinutes, maxSleep);

        int mostMinute = this.getMostMinute(inputMap, maxSleepID);



        return maxSleepID * mostMinute;
    }

    private int getMostMinute(Map<LocalDateTime, String> map, int maxSleepID) {

        Map<Integer, Integer> timeMap = new HashMap<>();
        boolean startSession = false;
        boolean isSleep = false;
        int minSleep = 0;
        boolean isAwake = false;
        int minAwake = 0;
        LocalDate remDate = null;



        for (Map.Entry<LocalDateTime, String> entry : map.entrySet()) {

            LocalDateTime dateTime = entry.getKey();
            LocalDate date = dateTime.toLocalDate();
            String str = entry.getValue();


            if ((remDate != null) && (!(remDate.isEqual(entry.getKey().toLocalDate())))) {
                startSession = false;
                remDate = null;
            }

            if (this.getID(entry.getValue()) == maxSleepID) {

                 startSession = true;
                 remDate = date;

           }

            if (startSession && str.equals(this.SLEEP)) {
                isSleep = true;
                minSleep = dateTime.getMinute();

            }

            if (startSession && str.equals(this.WAKE)) {
                isAwake = true;
                minAwake = dateTime.getMinute();

            }

            if (startSession && isSleep && isAwake) {


                while (minSleep != minAwake) {

                    int sleepCounter = 0;

                    if (timeMap.get(minSleep) != null) {
                        sleepCounter = timeMap.get(minSleep);
                    }

                    timeMap.put(minSleep, sleepCounter + 1);

                    minSleep++;
                }

                isAwake=false;
                isSleep=false;

            }


        }




        return this.getMaxSleepID(timeMap, 0);

    }


    private int getMaxSleepID(Map<Integer, Integer> map, int maxSleep) {

        Map.Entry<Integer, Integer> firstEntry = map.entrySet().iterator().next();
        int largestValue = firstEntry.getValue();
        Integer keyValue = firstEntry.getKey();

        for (Map.Entry<Integer, Integer> mapCheck : map.entrySet()) {
            int value = mapCheck.getValue();
            if (value > largestValue) {
                largestValue = value;
                keyValue = mapCheck.getKey();
            }
        }
        return keyValue;
    }

    private int getMaxSleep(Map<Integer, Integer> map) {

        int max = map.values().stream().max(Integer::compare).get();

        return max;

    }

    private Map convertList(List<String> list) {

        Map<LocalDateTime, String> inputMap = new TreeMap<>();

        for (int i = 0; i < list.size(); i++) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime date = LocalDateTime.parse(list.get(i).substring(1, 17), formatter);

            String str = list.get(i).substring(19);

            inputMap.put(date, str);


        }

        return inputMap;

    }

    private Map convertToMinutes(Map<LocalDateTime, String> inputMap) {

        Map<Integer, Integer> map = new TreeMap<>();
        boolean isID = false;
        boolean isSleep = false;
        boolean isAwake = false;
        boolean startSession = false;
        int countMin = 0;
        int remID = 0;
        int id = 0;
        int minSleep = 0;
        int minAwake = 0;

         for (Map.Entry<LocalDateTime, String> entry : inputMap.entrySet()) {

            LocalDateTime date = entry.getKey();
            String str = entry.getValue();

           if (remID != id) {
                startSession = false;
            }

            if (this.getID(str) != 0) {
                id = this.getID(str);
                remID = id;
                startSession = true;

            }

            if (startSession && str.equals(this.SLEEP)) {
                isSleep = true;
                minSleep = date.getMinute();

            }

            if (startSession && str.equals(this.WAKE)) {
                isAwake = true;
                minAwake = date.getMinute();

            }

            if (startSession && isSleep && isAwake) {
                int sleepMin = 0;

                if (map.get(remID) != null) {
                    sleepMin = map.get(id);
                }

                map.put(remID, sleepMin + minAwake - minSleep);

                isAwake=false;
                isSleep=false;


            }
        }

         return map;


    }

    private int getMinute(LocalDateTime date) {

        return date.getMinute();

    }

    private int getID (String str) {

        int id = 0;

        if (!(str.matches(this.SLEEP) || str.matches(this.WAKE))) {
            id = Integer.parseInt(str.replaceAll("[\\D]", ""));
        }

        return id;


    }


}
