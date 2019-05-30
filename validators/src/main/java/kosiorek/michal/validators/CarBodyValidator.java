package kosiorek.michal.validators;

import kosiorek.michal.model.CarBody;
import kosiorek.michal.validators.generic.AbstractValidator;

import java.util.Map;

public class CarBodyValidator extends AbstractValidator<CarBody> {

    private static final String MODELREGEX = "[A-Z ]+";

    @Override
    public Map<String, String> validate(CarBody carBody) {

        if (carBody == null) {
            errors.put("car body", "car body can't be null");
        }

        if (carBody != null) {
            carBody.getComponents().forEach(component -> {
                if (!component.matches(MODELREGEX)) {
                    errors.put("car body component", "doesn't match regex");
                }
            });
        }
      return errors;
    }
}
