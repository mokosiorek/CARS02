package kosiorek.michal.services;

import kosiorek.michal.exceptions.MyException;
import kosiorek.michal.model.enums.CarBodyType;
import kosiorek.michal.model.enums.Color;
import kosiorek.michal.model.enums.SortType;
import kosiorek.michal.model.enums.StatisticsType;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class UserDataService {

    private Scanner sc = new Scanner(System.in);

    public int getInt(String message) {
        System.out.println(message);

        String data = sc.nextLine();
        if (!data.matches("\\d+")) {
            throw new MyException("INT VALUE IS NOT CORRECT: " + data);
        }

        return Integer.parseInt(data);
    }

    public String getString(String message, String regex) {
        System.out.println(message);

        String data = sc.nextLine();
        if (regex != null && !regex.isEmpty() && !data.matches(regex)) {
            throw new MyException("STRING VALUE IS NOT CORRECT: " + data);
        }

        return data;
    }

    public BigDecimal getBigDecimal(String message) {
        System.out.println(message);

        String data = sc.nextLine();
        if (!data.matches("^([1-9]+\\d*(?:\\.\\d{2})?)*")) {
            throw new MyException( "BIG DECIMAL VALUE IS NOT CORRECT: " + data);
        }

        return BigDecimal.valueOf(Double.parseDouble(data));
    }

    public SortType getSortType() {
        SortType[] sortTypes = SortType.values();
        AtomicInteger counter = new AtomicInteger(1);

        Arrays
                .stream(sortTypes)
                .forEach(sortType -> System.out.println(counter.getAndIncrement() + ". " + sortType));
        System.out.println("Enter sort type:");
        String  option = sc.nextLine();

        if (!option.matches("[1-3]")) {
            throw new MyException("Sort type option number is not correct");
        }

        return sortTypes[Integer.parseInt(option) - 1];
    }

    public CarBodyType getBodyType() {
        CarBodyType[] carBodyTypes = CarBodyType.values();
        AtomicInteger counter = new AtomicInteger(1);

        Arrays
                .stream(carBodyTypes)
                .forEach(carBodyType -> System.out.println(counter.getAndIncrement() + ". " + carBodyType));
        System.out.println("Enter car body type:");
        String  option = sc.nextLine();

        if (!option.matches("[1-3]")) {
            throw new MyException("Car body option number is not correct");
        }

        return carBodyTypes[Integer.parseInt(option) - 1];
    }

    public StatisticsType getStatisticsType() {
        StatisticsType[] statisticsTypes = StatisticsType.values();
        AtomicInteger counter = new AtomicInteger(1);

        Arrays
                .stream(statisticsTypes)
                .forEach(statisticsType -> System.out.println(counter.getAndIncrement() + ". " + statisticsType));
        System.out.println("Enter statistics type:");
        String  option = sc.nextLine();

        if (!option.matches("[1-3]")) {
            throw new MyException( "Statistic type option number is not correct");
        }

        return statisticsTypes[Integer.parseInt(option) - 1];
    }

    public Color getColor() {
        Color[] colors = Color.values();
        AtomicInteger counter = new AtomicInteger(1);

        Arrays
                .stream(colors)
                .forEach(color -> System.out.println(counter.getAndIncrement() + ". " + color));
        System.out.println("Enter sort type:");
        String  option = sc.nextLine();

        if (!option.matches("[1-4]")) {
            throw new MyException( "Sort type option number is not correct");
        }

        return colors[Integer.parseInt(option) - 1];
    }

    public List<String> getComponents() {
        System.out.println("Enter components names separating them by comma");
        String text = sc.nextLine();

        if (!text.matches("([A-Z ]+,)*[A-Z ]+")) {
            throw new MyException("Components are not correct");
        }

        return Arrays.asList(text.split(","));
    }

    public boolean getBoolean(String message) {
        System.out.println(message + " [y/n?]");
        return sc.nextLine().charAt(0) == 'y';
    }

    public void close() {
        if (sc != null) {
            sc.close();
            sc = null;
        }
    }
}
