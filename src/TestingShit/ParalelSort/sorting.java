package TestingShit.ParalelSort;

import java.util.Arrays;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class sorting {
    public static void main(String[] args) {
        int[] array = {2,12,5,34,51,4,123,53,1,5,32,5132,132,5,8,13255,123,7,51325,51,3,4,3,343};
        long t0 = System.nanoTime();

        long count = Arrays.stream(array).parallel().sum();
        System.out.println(count);
        long t1 = System.nanoTime();

        long mill = TimeUnit.NANOSECONDS.toMillis(t1-t0);
        System.out.println(mill);
    }

}
