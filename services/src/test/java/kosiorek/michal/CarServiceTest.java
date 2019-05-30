package kosiorek.michal;

import kosiorek.michal.exceptions.MyException;
import kosiorek.michal.model.Car;
import kosiorek.michal.model.CarBody;
import kosiorek.michal.model.Engine;
import kosiorek.michal.model.Wheel;
import kosiorek.michal.model.enums.*;
import kosiorek.michal.services.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CarServiceTest {

    @Test
    @DisplayName("Check if sort by component count asc works")
    public void test1(){

        //GIVEN
        CarService carService = new CarService("cars02car1.json", "cars02car2.json", "cars02car3.json");
        //WHEN
        List<Car> cars = carService.sort(SortType.COMPONENT_COUNT,false);
        List<String> models = cars.stream().map(Car::getModel).collect(Collectors.toList());
        //THEN
        Assertions.assertLinesMatch(models, List.of("MAZDA", "AUDI", "BMW"));

    }

    @Test
    @DisplayName("Check if sort by engine power asc works")
    public void test2(){

        //GIVEN
        CarService carService = new CarService("cars02car1.json", "cars02car2.json", "cars02car3.json");
        //WHEN
        List<Car> cars = carService.sort(SortType.ENGINE_POWER,false);
        List<String> models = cars.stream().map(Car::getModel).collect(Collectors.toList());
        //THEN
        Assertions.assertLinesMatch(models, List.of("BMW", "MAZDA", "AUDI"));

    }

    @Test
    @DisplayName("Check if sort by wheel size asc works")
    public void test3(){

        //GIVEN
        CarService carService = new CarService("cars02car1.json", "cars02car2.json", "cars02car3.json");
        //WHEN
        List<Car> cars = carService.sort(SortType.WHEEL_SIZE,false);
        List<String> models = cars.stream().map(Car::getModel).collect(Collectors.toList());
        //THEN
        Assertions.assertLinesMatch(models, List.of("BMW", "AUDI", "MAZDA"));

    }

    @Test
    @DisplayName("Check if get Cars With Car BodyType With Price Between works")
    public void test4(){

        //GIVEN
        CarService carService = new CarService("cars02car1.json", "cars02car2.json", "cars02car3.json");
        //WHEN
        List<Car> cars = carService.getCarsWithCarBodyTypeWithPriceBetween(CarBodyType.HATCHBACK, BigDecimal.valueOf(100),BigDecimal.valueOf(90000));
        List<String> models = cars.stream().map(Car::getModel).collect(Collectors.toList());
        //THEN
        Assertions.assertTrue(models.containsAll(List.of("BMW", "AUDI", "MAZDA")));

    }

    @Test
    @DisplayName("Check if get Cars With Car BodyType With Price Between works 2")
    public void test5(){

        //GIVEN
        CarService carService = new CarService("cars02car1.json", "cars02car2.json", "cars02car3.json");
        //WHEN
        List<Car> cars = carService.getCarsWithCarBodyTypeWithPriceBetween(CarBodyType.SEDAN, BigDecimal.valueOf(100),BigDecimal.valueOf(90000));
        List<String> models = cars.stream().map(Car::getModel).collect(Collectors.toList());
        //THEN
        Assertions.assertTrue(cars.isEmpty());

    }

    @Test
    @DisplayName("Check if get Cars With Car BodyType With Price Between works 3")
    public void test6(){

        //GIVEN
        CarService carService = new CarService("cars02car1.json", "cars02car2.json", "cars02car3.json");
        //WHEN
        List<Car> cars = carService.getCarsWithCarBodyTypeWithPriceBetween(CarBodyType.HATCHBACK, BigDecimal.valueOf(90),BigDecimal.valueOf(110));
        List<String> models = cars.stream().map(Car::getModel).collect(Collectors.toList());
        //THEN
        Assertions.assertTrue(models.containsAll(List.of("BMW")));

    }

    @Test
    @DisplayName("Check if get Cars With Car BodyType With Price Between throws Exception")
    public void test6a(){

        //GIVEN
        CarService carService = new CarService("cars02car1.json", "cars02car2.json", "cars02car3.json");
        //WHEN
        //THEN
        Assertions.assertThrows(MyException.class,()->carService.getCarsWithCarBodyTypeWithPriceBetween(CarBodyType.HATCHBACK, BigDecimal.valueOf(900),BigDecimal.valueOf(110)));

    }

    @Test
    @DisplayName("Check if get Cars Names With EngineType works")
    public void test7(){

        //GIVEN
        CarService carService = new CarService("cars02car1.json", "cars02car2.json", "cars02car3.json", "cars02car4.json");
        //WHEN
        List<String> cars = carService.getCarsNamesWithEngineType(EngineType.GASOLINE);
        //THEN
        Assertions.assertTrue(cars.contains("YAMAHA"));

    }

    @Test
    @DisplayName("Check if get Car And Mileage Map works")
    public void test8(){

        //GIVEN
        CarService carService = new CarService("cars02car1.json", "cars02car2.json", "cars02car3.json", "cars02car4.json");
        //WHEN
        Map<Car,Integer> cars = carService.getCarAndMileageMap();
        Car car1 = Car.builder().model("AUDI").mileage(12000).price(BigDecimal.valueOf(120)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(210).build()).wheel(Wheel.builder().model("PIRELLI").size(18).tireType(TireType.SUMMER).build()).build();
        Car car2 = Car.builder().model("BMW").mileage(1000).price(BigDecimal.valueOf(100)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING","ALUFELGI")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(10).build()).wheel(Wheel.builder().model("PIRELLI").size(17).tireType(TireType.SUMMER).build()).build();
        Car car3 = Car.builder().model("MAZDA").mileage(3000).price(BigDecimal.valueOf(200)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(15).build()).wheel(Wheel.builder().model("PIRELLI").size(19).tireType(TireType.SUMMER).build()).build();
        Car car4 = Car.builder().model("YAMAHA").mileage(1000).price(BigDecimal.valueOf(100)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING","ALUFELGI")).build()).engine(Engine.builder().type(EngineType.GASOLINE).power(10).build()).wheel(Wheel.builder().model("PIRELLI").size(17).tireType(TireType.SUMMER).build()).build();

        Map<Car,Integer> carsExpected = Map.of(car1,12000,car2,1000,car3,3000,car4,1000);
        //THEN
        Assertions.assertEquals(cars,carsExpected);

    }

    @Test
    @DisplayName("Check if get TireType And Car Map works")
    public void test9(){

        //GIVEN
        CarService carService = new CarService("cars02car1.json", "cars02car2.json", "cars02car3.json", "cars02car4.json");
        //WHEN
        Map<TireType, List<Car>> map = carService.getTireTypeAndCarMap();
        Car car1 = Car.builder().model("AUDI").mileage(12000).price(BigDecimal.valueOf(120)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(210).build()).wheel(Wheel.builder().model("PIRELLI").size(18).tireType(TireType.SUMMER).build()).build();
        Car car2 = Car.builder().model("BMW").mileage(1000).price(BigDecimal.valueOf(100)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING","ALUFELGI")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(10).build()).wheel(Wheel.builder().model("PIRELLI").size(17).tireType(TireType.SUMMER).build()).build();
        Car car3 = Car.builder().model("MAZDA").mileage(3000).price(BigDecimal.valueOf(200)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(15).build()).wheel(Wheel.builder().model("PIRELLI").size(19).tireType(TireType.SUMMER).build()).build();
        Car car4 = Car.builder().model("YAMAHA").mileage(1000).price(BigDecimal.valueOf(100)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING","ALUFELGI")).build()).engine(Engine.builder().type(EngineType.GASOLINE).power(10).build()).wheel(Wheel.builder().model("PIRELLI").size(17).tireType(TireType.SUMMER).build()).build();

        Map<TireType, List<Car>> mapExpected = Map.of(TireType.SUMMER, Arrays.asList(car4,car2,car1,car3));

        map.forEach((tireType, cars) -> Collections.sort(cars, (c1,c2)-> c1.getModel().compareTo(c2.getModel())));
        mapExpected.forEach((tireType, cars) -> Collections.sort(cars, (c1,c2)-> c1.getModel().compareTo(c2.getModel())));

        //THEN
        Assertions.assertEquals(map,mapExpected);

    }

    @Test
    @DisplayName("Check if get Cars with Components works")
    public void test10(){

        //GIVEN
        CarService carService = new CarService("cars02car1.json", "cars02car2.json", "cars02car3.json", "cars02car4.json");
        //WHEN
        List<Car> cars = carService.getCarsWithComponents(List.of("ABS"));
        Car car1 = Car.builder().model("AUDI").mileage(12000).price(BigDecimal.valueOf(120)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(210).build()).wheel(Wheel.builder().model("PIRELLI").size(18).tireType(TireType.SUMMER).build()).build();
        Car car2 = Car.builder().model("BMW").mileage(1000).price(BigDecimal.valueOf(100)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING","ALUFELGI")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(10).build()).wheel(Wheel.builder().model("PIRELLI").size(17).tireType(TireType.SUMMER).build()).build();
        Car car3 = Car.builder().model("MAZDA").mileage(3000).price(BigDecimal.valueOf(200)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(15).build()).wheel(Wheel.builder().model("PIRELLI").size(19).tireType(TireType.SUMMER).build()).build();
        Car car4 = Car.builder().model("YAMAHA").mileage(1000).price(BigDecimal.valueOf(100)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING","ALUFELGI")).build()).engine(Engine.builder().type(EngineType.GASOLINE).power(10).build()).wheel(Wheel.builder().model("PIRELLI").size(17).tireType(TireType.SUMMER).build()).build();

        List<Car> carsExpected = List.of(car1,car2,car3,car4);

        //THEN
        Assertions.assertEquals(cars,carsExpected);

    }

    @Test
    @DisplayName("Check if get Cars with Components works 2")
    public void test11(){

        //GIVEN
        CarService carService = new CarService("cars02car1.json", "cars02car2.json", "cars02car3.json", "cars02car4.json");
        //WHEN
        List<Car> cars = carService.getCarsWithComponents(List.of("ABS","AIR CONDITIONING"));
        Car car1 = Car.builder().model("AUDI").mileage(12000).price(BigDecimal.valueOf(120)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(210).build()).wheel(Wheel.builder().model("PIRELLI").size(18).tireType(TireType.SUMMER).build()).build();
        Car car2 = Car.builder().model("BMW").mileage(1000).price(BigDecimal.valueOf(100)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING","ALUFELGI")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(10).build()).wheel(Wheel.builder().model("PIRELLI").size(17).tireType(TireType.SUMMER).build()).build();
        Car car3 = Car.builder().model("MAZDA").mileage(3000).price(BigDecimal.valueOf(200)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(15).build()).wheel(Wheel.builder().model("PIRELLI").size(19).tireType(TireType.SUMMER).build()).build();
        Car car4 = Car.builder().model("YAMAHA").mileage(1000).price(BigDecimal.valueOf(100)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING","ALUFELGI")).build()).engine(Engine.builder().type(EngineType.GASOLINE).power(10).build()).wheel(Wheel.builder().model("PIRELLI").size(17).tireType(TireType.SUMMER).build()).build();

        List<Car> carsExpected = List.of(car1,car2,car4);

        //THEN
        Assertions.assertEquals(cars,carsExpected);

    }

}
