import java.util.concurrent.atomic.AtomicInteger;

public class Config {
    public static int SPEED = 5;

    public static int[] atomicIntegerToInt(AtomicInteger[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            res[i] = arr[i].get();
        return res;
    }

    public static AtomicInteger[] intToAtomicInteger(int[] arr) {
        AtomicInteger[] res = new AtomicInteger[arr.length];
        for (int i = 0; i < arr.length; i++)
            res[i] = new AtomicInteger(arr[i]);
        return res;
    }
}
