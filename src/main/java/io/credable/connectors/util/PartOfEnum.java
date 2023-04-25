package io.credable.connectors.util;

import io.credable.connectors.DatabaseType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;


public class PartOfEnum implements ConstraintValidator<DatabaseEnumValidator, String> {
    @Override
    public void initialize(DatabaseEnumValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String databaseType, ConstraintValidatorContext constraintValidatorContext) {
        Set<String> allowedValues = Arrays.stream(DatabaseType.values()).map(DatabaseType::getType).collect(Collectors.toSet());
        return allowedValues.contains(databaseType);
    }
}
