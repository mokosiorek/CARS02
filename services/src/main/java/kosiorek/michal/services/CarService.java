package kosiorek.michal.services;

import kosiorek.michal.converters.CarJsonConverter;
import kosiorek.michal.exceptions.MyException;
import kosiorek.michal.model.Car;
import kosiorek.michal.model.enums.*;
import kosiorek.michal.validators.CarValidator;
import lombok.Data;
import org.eclipse.collections.impl.collector.BigDecimalSummaryStatistics;
import org.eclipse.collections.impl.collector.Collectors2;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class CarService {

    private Set<Car> cars = new HashSet<>();

    public CarService(String... filenames) {
        cars = getCarsFromJson(filenames);
    }

    private Set<Car> getCarsFromJson(String... filenames) {
        CarValidator carValidator = new CarValidator();
        AtomicInteger counter = new AtomicInteger(1);
        return Arrays
                .stream(filenames)
                .map(filename -> new CarJsonConverter(filename).fromJson().orElseThrow(() -> new MyException("JSON DATA ARE NOT CORRECT IN " + filename)))
                .filter(car -> {

                    Map<String, String> errors = carValidator.validate(car);

                    if (carValidator.hasErrors()) {
                        System.out.println("---------------------------------- VALIDATION ERROR -----------------------");
                        System.out.println("CAR NO. " + counter);
                        errors.forEach((k, v) -> System.out.println(k + " " + v));
                    }
                    counter.incrementAndGet();

                    return !carValidator.hasErrors();
                })
                .collect(Collectors.toSet());
    }


    public List<Car> sort(SortType sortType, boolean descending) {
        Stream<Car> carsStream = this.cars.stream();

        switch (sortType) {
            case COMPONENT_COUNT -> carsStream = this.cars.stream().sorted(Comparator.comparing(car -> car.getCarBody().getComponents().size()));
            case ENGINE_POWER -> carsStream = this.cars.stream().sorted(Comparator.comparing(car -> car.getEngine().getPower()));
            case WHEEL_SIZE -> carsStream = this.cars.stream().sorted(Comparator.comparing(car -> car.getWheel().getSize()));
        }

        List<Car> sortedCars = carsStream.collect(Collectors.toList());

        if (descending) {
            Collections.reverse(sortedCars);
        }

        return sortedCars;
    }


    public List<Car> getCarsWithCarBodyTypeWithPriceBetween(CarBodyType carBodyType, BigDecimal a, BigDecimal b) {

        if (carBodyType == null) {
            throw new MyException( "Car Body Type null!");
        }
        if (a == null || b == null) {
            throw new MyException("One or more price BigDecimal objects are null!");
        }
        if (a.compareTo(b) > 0) {
            throw new MyException( "Price b can't be lower than a!");
        }


        return this.cars.stream()
                .filter(car -> car.getCarBody().getCarBodyType().equals(carBodyType) && car.getPrice().compareTo(a) >= 0 && car.getPrice().compareTo(b) <= 0)
                .collect(Collectors.toList());

    }

    public List<String> getCarsNamesWithEngineType(EngineType engineType) {

        if (engineType == null) {
            throw new MyException("Engine Type object is null!");
        }

        return this.cars.stream()
                .filter(car -> car.getEngine().getType().equals(engineType))
                .map(Car::getModel)
                .collect(Collectors.toList());
    }


    public void statistics(StatisticsType statisticsType) {


        switch (statisticsType) {

            case PRICE -> {
                BigDecimalSummaryStatistics carPriceSummaryStatistics = cars.stream().collect(Collectors2.summarizingBigDecimal(Car::getPrice));

                System.out.println("PRICE");
                System.out.println("MIN: " + carPriceSummaryStatistics.getMin());
                System.out.println("MAX: " + carPriceSummaryStatistics.getMax());
                System.out.println("AVG: " + carPriceSummaryStatistics.getAverage());
            }

            case ENGINE_POWER -> {
                System.out.println("ENGINE POWER");
                DoubleSummaryStatistics dss = cars.stream()
                        .collect(Collectors.summarizingDouble(car -> car.getEngine().getPower()));
                System.out.println("MIN: " + dss.getMin());
                System.out.println("MAX: " + dss.getMax());
                System.out.println("AVG: " + dss.getAverage());
            }

            case MILEAGE -> {
                System.out.println("MILEAGE");
                IntSummaryStatistics iss = cars.stream()
                        .collect(Collectors.summarizingInt(Car::getMileage));
                System.out.println("MIN: " + iss.getMin());
                System.out.println("MAX: " + iss.getMax());
                System.out.println("AVG: " + iss.getAverage());
            }
        }

    }

    public Map<Car, Integer> getCarAndMileageMap() {
        return cars.stream()
                .sorted(Comparator.comparing(Car::getMileage).reversed())
                .collect(Collectors.toMap(Function.identity(), Car::getMileage, Integer::max, LinkedHashMap::new));
    }

    public Map<TireType, List<Car>> getTireTypeAndCarMap() {

        return cars
                .stream()
                .collect(Collectors.groupingBy(car -> car.getWheel().getTireType()))
                .entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));

    }

    public List<Car> getCarsWithComponents(List<String> components) {

        return cars
                .stream()
                .filter(car -> car.getCarBody().getComponents().containsAll(components))
                .sorted(Comparator.comparing(Car::getModel))
                .collect(Collectors.toList());

    }


}
