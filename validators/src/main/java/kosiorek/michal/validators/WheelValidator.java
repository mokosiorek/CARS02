package kosiorek.michal.validators;

import kosiorek.michal.model.Wheel;
import kosiorek.michal.validators.generic.AbstractValidator;

import java.util.Map;

public class WheelValidator extends AbstractValidator<Wheel> {

    private static final String MODELREGEX = "[A-Z ]+";

    @Override
    public Map<String, String> validate(Wheel wheel) {

        if (wheel == null) {
            errors.put("wheel", "can't be null");
        }

        if (wheel != null) {
            if (wheel.getModel() == null || !wheel.getModel().matches(MODELREGEX)) {
                errors.put("wheel model", "doesn't match regex");
            }

            if (wheel.getSize() < 0) {
                errors.put("wheel size", "can't be below 0");
            }

        }

        return errors;
    }
}