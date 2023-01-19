import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {

    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();
    public static final int NumberOfThreads = 1000;
    public static final int Route = 100;
    public static final String Letters = "RLRFR";


    public static void main(String[] args) {
        for (int i = 0; i < NumberOfThreads; i++) {
            new Thread(()->{
                String route = generateRoute(Letters,Route);
                int counter = (int) route.chars().filter(value -> value == 'R').count();
                synchronized (sizeToFreq){
                    if (sizeToFreq.containsKey(counter)) {
                        sizeToFreq.put(counter, sizeToFreq.get(counter) + 1);
                    }else{
                        sizeToFreq.put(counter,1);
                    }
                    }
                }).start();
            }
        Map.Entry<Integer,Integer> pair = sizeToFreq
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get();
        System.out.println("Самое частое количество повторений" + pair.getKey() + "(встретилось " + pair.getValue() + "раз.)");
        System.out.println("Другие размеры: ");

        sizeToFreq.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(e -> System.out.println("-" + e.getKey() + "(встретилось " + e.getValue() + "раз.)"));
        }
    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }
}
