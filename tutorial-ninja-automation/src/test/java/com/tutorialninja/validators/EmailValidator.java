package com.tutorialninja.validators;


public class EmailValidator implements FieldValidator {

    @Override
    public String getFieldName() {
        return "email";
    }

    @Override
    public String getInvalidValue() {
        return "test@test";
    }

    @Override
    public String getExpectedError() {
        return "E-Mail Address does not appear to be valid!";
    }
}
