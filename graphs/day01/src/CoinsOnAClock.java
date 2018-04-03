import java.util.ArrayList;
import java.util.List;

public class CoinsOnAClock {

    public static List<char[]> coinsOnAClock(int pennies, int nickels, int dimes, int hoursInDay) {

        List<char[]> result = new ArrayList<>();
        char[] starting = new char[hoursInDay];
        for(int j=0; j<hoursInDay; j++){
            starting[j] = '.';
        }

        enumerate(pennies, nickels, dimes, hoursInDay, starting, result, 0);
        return result;
    }

    public static List<char[]> enumerate(int pennies, int nickels, int dimes, int hoursInDay, char[] order, List<char[]> result, int position){
        if (pennies <= 0 && nickels <= 0 && dimes <= 0){

            char[] tempRes = new char[hoursInDay];
            System.arraycopy(order, 0, tempRes, 0, hoursInDay);
            result.add(tempRes);
            return result;
        }
        List<Character> looper = createLooper(pennies, nickels, dimes);

        for (int i = 0; i<looper.size(); i++){

            if (order[position] == '.'){
                order[position] = looper.get(i);

                int nextSpot = findNextSpot(convertCoin(looper.get(i)), position, hoursInDay);

                int tpennies = pennies;
                int tdimes = dimes;
                int tnickels = nickels;
                if (looper.get(i) == 'p'){
                    tpennies -= 1;
                } else if (looper.get(i) == 'd'){
                    tdimes -= 1;
                } else if (looper.get(i) == 'n'){
                    tnickels -=1;
                }

                enumerate(tpennies, tnickels, tdimes, hoursInDay, order, result, nextSpot);
                order[position] = '.';
            }

        }
        return result;

    }

    public static int convertCoin(int coin){
        if (coin == 'p'){
            return 1;
        } else if (coin == 'n'){
            return 5;
        } else {
            return 10;
        }
    }

    public static List<Character> createLooper(int pennies, int nickels, int dimes){
        List<Character> looper = new ArrayList<>();
        if (pennies > 0){
            looper.add('p');
        }
        if (nickels > 0) {
            looper.add('n');
        }
        if (dimes > 0){
            looper.add('d');
        }
        return looper;
    }
    public static int findNextSpot(int coin, int currentSpot, int hoursInDay){
        int newHour = currentSpot+coin;
        if (newHour>=hoursInDay){
            newHour = newHour % hoursInDay;
        }
        return newHour;
    }
}
