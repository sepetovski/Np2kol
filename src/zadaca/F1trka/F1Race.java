package zadaca.F1trka;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.lang.Integer.parseInt;

class Driver implements Comparable<Driver> {
    private String driverName;
    private int[] lap;
    private Integer lap1;
    private Integer lap2;

    private Integer lap3;
    private Integer best;

    public Driver(String driverName, int[] lap) {
        this.driverName = driverName;
        this.lap1 = lap[0];
        this.lap2 = lap[1];
        this.lap3 = lap[2];
        this.best = Math.min(Math.min(lap1, lap2), lap3);
    }

    public String getDriverName() {
        return driverName;
    }

    @Override
    public int compareTo(Driver o) {
        return this.best - o.best;
    }

    public static int stringToTime(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 * 1000
                + Integer.parseInt(parts[1]) * 1000
                + Integer.parseInt(parts[2]);
    }

    public static String timeToString(int time) {
        int minutes = (time / 1000) / 60;
        int seconds = (time - minutes * 1000 * 60) / 1000;
        int ms = time % 1000;
        return String.format("%d:%02d:%03d", minutes, seconds, ms);
    }

    @Override
    public String toString() {
        return String.format("%-10s%10s", driverName, timeToString(best));
    }


    public int getBestLap() {
        return best;
    }
}

public class F1Race {
    private List<Driver> drivers;

    public F1Race() {
        this.drivers = new ArrayList<>();
    }

    public void readResults(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split(" ");
            String name = tokens[0];
            int[] laps = new int[3];
            for (int i = 0; i < 3; i++) {
                laps[i] = convertToSeconds(tokens[i + 1]);
            }
            Driver driver = new Driver(name, laps);
            drivers.add(driver);

        }
    }


    public void printSorted(OutputStream outputStream) {
        Collections.sort(drivers, new Comparator<Driver>() {
            @Override
            public int compare(Driver o1, Driver o2) {
                return o1.getBestLap() - o2.getBestLap();
            }
        });
        for (Driver driver : drivers) {
            System.out.println(driver);
        }
    }

    private int convertToSeconds(String time) {
        String[] tokens = time.split(":");
        int minutes = Integer.parseInt(tokens[0]);
        int seconds = Integer.parseInt(tokens[1]);
        int milliseconds = Integer.parseInt(tokens[2]);
        return minutes * 60 + seconds + milliseconds / 1000;
    }
}