package com.tutorialninja.validators;

import com.tutorialninja.forms.GenericFormWrapper;
import com.tutorialninja.forms.RegistrationForm;

public class FirstNameValidator implements FieldValidator {

    @Override
    public String getFieldName() {
        return "firstName";
    }

    @Override
    public String getExpectedError() {
        return "First Name must be between 1 and 32 characters!";
    }

    @Override
    public String getActualError(GenericFormWrapper<RegistrationForm> wrapper) {
        return wrapper.getText(wrapper.getForm().getErrors().get("firstName"));
    }
}
