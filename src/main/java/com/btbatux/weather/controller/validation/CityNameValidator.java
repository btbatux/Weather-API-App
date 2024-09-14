package com.btbatux.weather.controller.validation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CityNameValidator implements ConstraintValidator<CityNameConstraint, String> {


    @Override
    public void initialize(CityNameConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        value = value.replaceAll("[^a-zA-Z0-9]", "");

        return value != null && value.matches("[a-zA-Z ]+");
    }
}
