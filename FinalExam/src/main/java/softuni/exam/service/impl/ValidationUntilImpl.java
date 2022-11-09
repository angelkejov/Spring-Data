package softuni.exam.service.impl;

import org.springframework.stereotype.Component;
import softuni.exam.util.ValidationUntil;

import javax.validation.Validation;
import javax.validation.Validator;

@Component
public class ValidationUntilImpl implements ValidationUntil {
    private final Validator validator;

    public ValidationUntilImpl() {
        validator = Validation
                .buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public <E> boolean isValid(E entity) {
        return validator.validate(entity).isEmpty();
    }
}
