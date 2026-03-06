package com.tutorialninja.validators;

import com.tutorialninja.forms.GenericFormWrapper;
import com.tutorialninja.forms.RegistrationForm;

public class PasswordValidator implements FieldValidator {

    @Override
    public String getFieldName() {
        return "password";
    }

    @Override
    public String getExpectedError() {
        return "Password must be between 4 and 20 characters!";
    }

    @Override
    public String getActualError(GenericFormWrapper<RegistrationForm> wrapper) {
        return wrapper.getText(wrapper.getForm().getErrors().get("password"));
    }
}
