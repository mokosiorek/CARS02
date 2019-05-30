package kosiorek.michal;

import kosiorek.michal.model.Car;
import kosiorek.michal.model.CarBody;
import kosiorek.michal.model.Engine;
import kosiorek.michal.model.Wheel;
import kosiorek.michal.model.enums.CarBodyType;
import kosiorek.michal.model.enums.Color;
import kosiorek.michal.model.enums.EngineType;
import kosiorek.michal.model.enums.TireType;
import kosiorek.michal.validators.CarValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class CarValidatorTest {

    @Test
    @DisplayName("Check car validator validates Car Model properly")
    public void test1(){

        //GIVEN
        CarValidator carValidator = new CarValidator();
        CarValidator carValidator2 = new CarValidator();
        CarValidator carValidator3 = new CarValidator();
        CarValidator carValidator4 = new CarValidator();
        //WHEN

        Car car1 = Car.builder().model("AUDI").mileage(12000).price(BigDecimal.valueOf(120)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(210).build()).wheel(Wheel.builder().model("PIRELLI").size(18).tireType(TireType.SUMMER).build()).build();
        Car car2 = Car.builder().model("BMW").mileage(1000).price(BigDecimal.valueOf(100)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING","ALUFELGI")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(10).build()).wheel(Wheel.builder().model("PIRELLI").size(17).tireType(TireType.SUMMER).build()).build();
        Car car3 = Car.builder().model("MAZDA").mileage(3000).price(BigDecimal.valueOf(200)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(15).build()).wheel(Wheel.builder().model("PIRELLI").size(19).tireType(TireType.SUMMER).build()).build();
        Car car4 = Car.builder().model("YAMAHA").mileage(1000).price(BigDecimal.valueOf(100)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING","ALUFELGI")).build()).engine(Engine.builder().type(EngineType.GASOLINE).power(10).build()).wheel(Wheel.builder().model("PIRELLI").size(17).tireType(TireType.SUMMER).build()).build();

        Map<String,String> map = carValidator.validate(car1);
        Map<String,String> map2 = carValidator2.validate(car2);
        Map<String,String> map3 = carValidator3.validate(car3);
        Map<String,String> map4 = carValidator4.validate(car4);

        //THEN
        Assertions.assertTrue(map.isEmpty());
        Assertions.assertTrue(map2.isEmpty());
        Assertions.assertTrue(map3.isEmpty());
        Assertions.assertTrue(map4.isEmpty());

    }

    @Test
    @DisplayName("Check car validator validates Car Model properly")
    public void test2(){

        //GIVEN
        CarValidator carValidator = new CarValidator();
        CarValidator carValidator2 = new CarValidator();
        CarValidator carValidator3 = new CarValidator();
        CarValidator carValidator4 = new CarValidator();
        //WHEN

        Car car1 = Car.builder().model("AUDI2").mileage(12000).price(BigDecimal.valueOf(120)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(210).build()).wheel(Wheel.builder().model("PIRELLI").size(18).tireType(TireType.SUMMER).build()).build();
        Car car2 = Car.builder().model("BMw").mileage(1000).price(BigDecimal.valueOf(100)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING","ALUFELGI")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(10).build()).wheel(Wheel.builder().model("PIRELLI").size(17).tireType(TireType.SUMMER).build()).build();
        Car car3 = Car.builder().model("mazda").mileage(3000).price(BigDecimal.valueOf(200)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(15).build()).wheel(Wheel.builder().model("PIRELLI").size(19).tireType(TireType.SUMMER).build()).build();
        Car car4 = Car.builder().model(null).mileage(1000).price(BigDecimal.valueOf(100)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING","ALUFELGI")).build()).engine(Engine.builder().type(EngineType.GASOLINE).power(10).build()).wheel(Wheel.builder().model("PIRELLI").size(17).tireType(TireType.SUMMER).build()).build();

        Map<String,String> map = carValidator.validate(car1);
        Map<String,String> map2 = carValidator2.validate(car2);
        Map<String,String> map3 = carValidator3.validate(car3);
        Map<String,String> map4 = carValidator4.validate(car4);

        //THEN
        Assertions.assertTrue(map.containsKey("model")&&map.containsValue("doesn't match regex"));
        Assertions.assertTrue(map2.containsKey("model")&&map2.containsValue("doesn't match regex"));
        Assertions.assertTrue(map3.containsKey("model")&&map3.containsValue("doesn't match regex"));
        Assertions.assertTrue(map4.containsKey("model")&&map4.containsValue("doesn't match regex"));


    }

    @Test
    @DisplayName("Check car validator validates Car price properly")
    public void test3(){

        //GIVEN
        CarValidator carValidator = new CarValidator();
        CarValidator carValidator2 = new CarValidator();
        CarValidator carValidator3 = new CarValidator();
        CarValidator carValidator4 = new CarValidator();
        //WHEN

        Car car1 = Car.builder().model("AUDI").mileage(12000).price(BigDecimal.valueOf(-1)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(210).build()).wheel(Wheel.builder().model("PIRELLI").size(18).tireType(TireType.SUMMER).build()).build();
        Car car2 = Car.builder().model("BMW").mileage(1000).price(BigDecimal.ZERO).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING","ALUFELGI")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(10).build()).wheel(Wheel.builder().model("PIRELLI").size(17).tireType(TireType.SUMMER).build()).build();
        Car car3 = Car.builder().model("MAZDA").mileage(3000).price(null).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(15).build()).wheel(Wheel.builder().model("PIRELLI").size(19).tireType(TireType.SUMMER).build()).build();
        Car car4 = Car.builder().model("YAMAHA").mileage(1000).price(BigDecimal.valueOf(1)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING","ALUFELGI")).build()).engine(Engine.builder().type(EngineType.GASOLINE).power(10).build()).wheel(Wheel.builder().model("PIRELLI").size(17).tireType(TireType.SUMMER).build()).build();

        Map<String,String> map = carValidator.validate(car1);
        Map<String,String> map2 = carValidator2.validate(car2);
        Map<String,String> map3 = carValidator3.validate(car3);
        Map<String,String> map4 = carValidator4.validate(car4);

        //THEN
        Assertions.assertTrue(map.containsKey("price")&&map.containsValue("less than 0"));
        Assertions.assertTrue(map2.isEmpty());
        Assertions.assertTrue(map3.containsKey("price")&&map3.containsValue("less than 0"));
        Assertions.assertTrue(map4.isEmpty());


    }

    @Test
    @DisplayName("Check car validator validates Car mileage properly")
    public void test4(){

        //GIVEN
        CarValidator carValidator = new CarValidator();
        CarValidator carValidator2 = new CarValidator();
        CarValidator carValidator3 = new CarValidator();
        CarValidator carValidator4 = new CarValidator();
        //WHEN

        Car car1 = Car.builder().model("AUDI").mileage(12000).price(BigDecimal.valueOf(120)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(210).build()).wheel(Wheel.builder().model("PIRELLI").size(18).tireType(TireType.SUMMER).build()).build();
        Car car2 = Car.builder().model("BMW").mileage(1000).price(BigDecimal.valueOf(100)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING","ALUFELGI")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(10).build()).wheel(Wheel.builder().model("PIRELLI").size(17).tireType(TireType.SUMMER).build()).build();
        Car car3 = Car.builder().model("MAZDA").mileage(0).price(BigDecimal.valueOf(200)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(15).build()).wheel(Wheel.builder().model("PIRELLI").size(19).tireType(TireType.SUMMER).build()).build();
        Car car4 = Car.builder().model("YAMAHA").mileage(-1).price(BigDecimal.valueOf(100)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING","ALUFELGI")).build()).engine(Engine.builder().type(EngineType.GASOLINE).power(10).build()).wheel(Wheel.builder().model("PIRELLI").size(17).tireType(TireType.SUMMER).build()).build();

        Map<String,String> map = carValidator.validate(car1);
        Map<String,String> map2 = carValidator2.validate(car2);
        Map<String,String> map3 = carValidator3.validate(car3);
        Map<String,String> map4 = carValidator4.validate(car4);

        //THEN
        Assertions.assertTrue(map.isEmpty());
        Assertions.assertTrue(map2.isEmpty());
        Assertions.assertTrue(map3.isEmpty());
        Assertions.assertTrue(map4.containsKey("mileage")&&map4.containsValue("less than 0"));

    }

    @Test
    @DisplayName("Check car validator validates Car engine properly")
    public void test5(){

        //GIVEN
        CarValidator carValidator = new CarValidator();
        CarValidator carValidator2 = new CarValidator();
        CarValidator carValidator3 = new CarValidator();
        CarValidator carValidator4 = new CarValidator();
        //WHEN

        Car car1 = Car.builder().model("AUDI").mileage(12000).price(BigDecimal.valueOf(120)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(210).build()).wheel(Wheel.builder().model("PIRELLI").size(18).tireType(TireType.SUMMER).build()).build();
        Car car2 = Car.builder().model("BMW").mileage(1000).price(BigDecimal.valueOf(100)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING","ALUFELGI")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(10).build()).wheel(Wheel.builder().model("PIRELLI").size(17).tireType(TireType.SUMMER).build()).build();
        Car car3 = Car.builder().model("MAZDA").mileage(3000).price(BigDecimal.valueOf(200)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS")).build()).engine(null).wheel(Wheel.builder().model("PIRELLI").size(19).tireType(TireType.SUMMER).build()).build();
        Car car4 = Car.builder().model("YAMAHA").mileage(1000).price(BigDecimal.valueOf(100)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING","ALUFELGI")).build()).engine(Engine.builder().type(EngineType.GASOLINE).power(-1).build()).wheel(Wheel.builder().model("PIRELLI").size(17).tireType(TireType.SUMMER).build()).build();

        Map<String,String> map = carValidator.validate(car1);
        Map<String,String> map2 = carValidator2.validate(car2);
        Map<String,String> map3 = carValidator3.validate(car3);
        Map<String,String> map4 = carValidator4.validate(car4);

        //THEN
        Assertions.assertTrue(map.isEmpty());
        Assertions.assertTrue(map2.isEmpty());
        Assertions.assertTrue(map3.containsKey("engine power")&&map3.containsValue("less than 0"));
        Assertions.assertTrue(map4.containsKey("engine power")&&map4.containsValue("less than 0"));

    }

    @Test
    @DisplayName("Check car validator validates Car Body properly")
    public void test6(){

        //GIVEN
        CarValidator carValidator = new CarValidator();
        CarValidator carValidator2 = new CarValidator();
        CarValidator carValidator3 = new CarValidator();
        CarValidator carValidator4 = new CarValidator();
        //WHEN

        Car car1 = Car.builder().model("AUDI").mileage(12000).price(BigDecimal.valueOf(120)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(210).build()).wheel(Wheel.builder().model("PIRELLI").size(18).tireType(TireType.SUMMER).build()).build();
        Car car2 = Car.builder().model("BMW").mileage(1000).price(BigDecimal.valueOf(100)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING","ALUFELGI")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(10).build()).wheel(Wheel.builder().model("PIRELLI").size(17).tireType(TireType.SUMMER).build()).build();
        Car car3 = Car.builder().model("MAZDA").mileage(3000).price(BigDecimal.valueOf(200)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(15).build()).wheel(Wheel.builder().model("PIRELLI").size(19).tireType(TireType.SUMMER).build()).build();
        Car car4 = Car.builder().model("YAMAHA").mileage(1000).price(BigDecimal.valueOf(100)).carBody(null).engine(Engine.builder().type(EngineType.GASOLINE).power(10).build()).wheel(Wheel.builder().model("PIRELLI").size(17).tireType(TireType.SUMMER).build()).build();

        Map<String,String> map = carValidator.validate(car1);
        Map<String,String> map2 = carValidator2.validate(car2);
        Map<String,String> map3 = carValidator3.validate(car3);
        Map<String,String> map4 = carValidator4.validate(car4);

        //THEN
        Assertions.assertTrue(map.isEmpty());
        Assertions.assertTrue(map2.isEmpty());
        Assertions.assertTrue(map3.isEmpty());
        Assertions.assertTrue(map4.containsKey("car body")&&map4.containsValue("car body can't be null"));

    }

    @Test
    @DisplayName("Check car validator validates Car Body Components properly")
    public void test7(){

        //GIVEN
        CarValidator carValidator = new CarValidator();
        CarValidator carValidator2 = new CarValidator();
        CarValidator carValidator3 = new CarValidator();
        CarValidator carValidator4 = new CarValidator();
        //WHEN

        Car car1 = Car.builder().model("AUDI").mileage(12000).price(BigDecimal.valueOf(120)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(210).build()).wheel(Wheel.builder().model("PIRELLI").size(18).tireType(TireType.SUMMER).build()).build();
        Car car2 = Car.builder().model("BMW").mileage(1000).price(BigDecimal.valueOf(100)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of()).build()).engine(Engine.builder().type(EngineType.DIESEL).power(10).build()).wheel(Wheel.builder().model("PIRELLI").size(17).tireType(TireType.SUMMER).build()).build();
        Car car3 = Car.builder().model("MAZDA").mileage(3000).price(BigDecimal.valueOf(200)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("abs")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(15).build()).wheel(Wheel.builder().model("PIRELLI").size(19).tireType(TireType.SUMMER).build()).build();
        Car car4 = Car.builder().model("YAMAHA").mileage(1000).price(BigDecimal.valueOf(100)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS123","AIR CONDITIONING","ALUFELGI")).build()).engine(Engine.builder().type(EngineType.GASOLINE).power(10).build()).wheel(Wheel.builder().model("PIRELLI").size(17).tireType(TireType.SUMMER).build()).build();

        Map<String,String> map = carValidator.validate(car1);
        Map<String,String> map2 = carValidator2.validate(car2);
        Map<String,String> map3 = carValidator3.validate(car3);
        Map<String,String> map4 = carValidator4.validate(car4);

        //THEN
        Assertions.assertTrue(map.isEmpty());
        Assertions.assertTrue(map2.isEmpty());
        Assertions.assertTrue(map3.containsKey("car body component")&&map3.containsValue("doesn't match regex"));
        Assertions.assertTrue(map4.containsKey("car body component")&&map4.containsValue("doesn't match regex"));

    }

    @Test
    @DisplayName("Check car validator validates Car Body Components properly")
    public void test8(){

        //GIVEN
        CarValidator carValidator = new CarValidator();
        CarValidator carValidator2 = new CarValidator();
        CarValidator carValidator3 = new CarValidator();
        CarValidator carValidator4 = new CarValidator();
        //WHEN

        Car car1 = Car.builder().model("AUDI").mileage(12000).price(BigDecimal.valueOf(120)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(210).build()).wheel(Wheel.builder().model("PIRELLI").size(18).tireType(TireType.SUMMER).build()).build();
        Car car2 = Car.builder().model("BMW").mileage(1000).price(BigDecimal.valueOf(100)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of()).build()).engine(Engine.builder().type(EngineType.DIESEL).power(10).build()).wheel(Wheel.builder().model("PIRELLI").size(17).tireType(TireType.SUMMER).build()).build();
        Car car3 = Car.builder().model("MAZDA").mileage(3000).price(BigDecimal.valueOf(200)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("abs")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(15).build()).wheel(Wheel.builder().model("PIRELLI").size(19).tireType(TireType.SUMMER).build()).build();
        Car car4 = Car.builder().model("YAMAHA").mileage(1000).price(BigDecimal.valueOf(100)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS123","AIR CONDITIONING","ALUFELGI")).build()).engine(Engine.builder().type(EngineType.GASOLINE).power(10).build()).wheel(Wheel.builder().model("PIRELLI").size(17).tireType(TireType.SUMMER).build()).build();

        Map<String,String> map = carValidator.validate(car1);
        Map<String,String> map2 = carValidator2.validate(car2);
        Map<String,String> map3 = carValidator3.validate(car3);
        Map<String,String> map4 = carValidator4.validate(car4);

        //THEN
        Assertions.assertTrue(map.isEmpty());
        Assertions.assertTrue(map2.isEmpty());
        Assertions.assertTrue(map3.containsKey("car body component")&&map3.containsValue("doesn't match regex"));
        Assertions.assertTrue(map4.containsKey("car body component")&&map4.containsValue("doesn't match regex"));

    }

    @Test
    @DisplayName("Check car validator validates Car Wheel properly")
    public void test9(){

        //GIVEN
        CarValidator carValidator = new CarValidator();
        CarValidator carValidator2 = new CarValidator();
        CarValidator carValidator3 = new CarValidator();
        CarValidator carValidator4 = new CarValidator();
        //WHEN

        Car car1 = Car.builder().model("AUDI").mileage(12000).price(BigDecimal.valueOf(120)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(210).build()).wheel(Wheel.builder().model("PIRELLI").size(18).tireType(TireType.SUMMER).build()).build();
        Car car2 = Car.builder().model("BMW").mileage(1000).price(BigDecimal.valueOf(100)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING","ALUFELGI")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(10).build()).wheel(null).build();
        Car car3 = Car.builder().model("MAZDA").mileage(3000).price(BigDecimal.valueOf(200)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS")).build()).engine(Engine.builder().type(EngineType.DIESEL).power(15).build()).wheel(Wheel.builder().model(null).size(-1).tireType(TireType.SUMMER).build()).build();
        Car car4 = Car.builder().model("YAMAHA").mileage(1000).price(BigDecimal.valueOf(100)).carBody(CarBody.builder().carBodyType(CarBodyType.HATCHBACK).color(Color.BLACK).components(List.of("ABS","AIR CONDITIONING","ALUFELGI")).build()).engine(Engine.builder().type(EngineType.GASOLINE).power(10).build()).wheel(Wheel.builder().model("pirelli").size(17).tireType(TireType.SUMMER).build()).build();

        Map<String,String> map = carValidator.validate(car1);
        Map<String,String> map2 = carValidator2.validate(car2);
        Map<String,String> map3 = carValidator3.validate(car3);
        Map<String,String> map4 = carValidator4.validate(car4);

        //THEN
        Assertions.assertTrue(map.isEmpty());
        Assertions.assertTrue(map2.containsKey("wheel")&&map2.containsValue("can't be null"));
        Assertions.assertTrue(map3.containsKey("wheel model")&&map3.containsValue("doesn't match regex"));
        Assertions.assertTrue(map3.containsKey("wheel size")&&map3.containsValue("can't be below 0"));
        Assertions.assertTrue(map4.containsKey("wheel model")&&map4.containsValue("doesn't match regex"));

    }

}
