import java.util.*;

public class FrequencyPrint {

    static String frequencyPrint(String s) {
        String[] wordArray = s.split(" ");
        Map<String, Integer> wordDict = new HashMap<>();
        HashMap<Integer, String> valueCounts = new HashMap<>();
        int wordcount;
        for (int i = 0; i<wordArray.length; i++){
            wordcount = wordDict.getOrDefault(wordArray[i], 0);
            wordcount += 1;
            wordDict.put(wordArray[i], wordcount);
        }
        for (Map.Entry<String, Integer> entry : wordDict.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            if(valueCounts.containsKey(value)){

                String words = "";
                for(int k=0; k<value; k++) {

                    words += key + " ";

                }

                valueCounts.put(value, valueCounts.get(value)+ words);

            }
            else{
                String words = "";
                for(int k=0; k<value; k++) {

                    words += key + " ";

                }
                valueCounts.put(value, words);
            }
        }

        String finalS = "";
        for (Map.Entry<Integer, String> entry : valueCounts.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();
            finalS += value;
        }

        return finalS;
    }

}
