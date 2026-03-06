package com.tutorialninja.validators;

public class LengthBoundValidator implements FieldValidator {

    public enum ViolationType { BELOW_MIN, ABOVE_MAX }

    private final String fieldName;
    private final String displayName;
    private final int min;
    private final int max;
    private final ViolationType violationType;

    public LengthBoundValidator(String fieldName, String displayName, int min, int max, ViolationType violationType) {
        this.fieldName = fieldName;
        this.displayName = displayName;
        this.min = min;
        this.max = max;
        this.violationType = violationType;
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public String getInvalidValue() {
        int length = switch (violationType) {
            case ABOVE_MAX -> max + 1;
            case BELOW_MIN -> Math.max(0, min - 1);
        };
        return "a".repeat(length);
    }

    @Override
    public String getExpectedError() {
        return displayName + " must be between " + min + " and " + max + " characters!";
    }
}
