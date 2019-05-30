package kosiorek.michal.validators;

import kosiorek.michal.model.Car;
import kosiorek.michal.validators.generic.AbstractValidator;

import java.math.BigDecimal;
import java.util.Map;

public class CarValidator extends AbstractValidator<Car> {

    private static final String MODELREGEX = "[A-Z ]+";

    @Override
    public Map<String, String> validate(Car car) {

        errors.clear();

        if (car.getModel() == null || !car.getModel().matches(MODELREGEX)) {
            errors.put("model", "doesn't match regex");
        }

        if (car.getPrice() == null || car.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            errors.put("price", "less than 0");
        }

        if (car.getMileage() < 0) {
            errors.put("mileage", "less than 0");
        }

        errors.putAll(new WheelValidator().validate(car.getWheel()));
        errors.putAll(new CarBodyValidator().validate(car.getCarBody()));
        errors.putAll(new EngineValidator().validate(car.getEngine()));
        return errors;
    }


}
