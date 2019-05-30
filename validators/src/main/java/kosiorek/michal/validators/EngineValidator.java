package kosiorek.michal.validators;

import kosiorek.michal.model.Engine;
import kosiorek.michal.validators.generic.AbstractValidator;

import java.util.Map;

public class EngineValidator extends AbstractValidator<Engine> {
    @Override
    public Map<String, String> validate(Engine engine) {

        if (engine == null || engine.getPower() < 0) {
            errors.put("engine power", "less than 0");
        }

        return errors;
    }
}
