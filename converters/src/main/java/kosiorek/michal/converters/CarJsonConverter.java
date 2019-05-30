package kosiorek.michal.converters;


import kosiorek.michal.model.Car;

public class CarJsonConverter extends JsonConverter<Car> {

    public CarJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }

}
