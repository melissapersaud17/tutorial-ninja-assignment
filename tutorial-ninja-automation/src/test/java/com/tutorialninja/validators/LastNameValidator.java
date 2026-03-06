package com.tutorialninja.validators;

import com.tutorialninja.forms.GenericFormWrapper;
import com.tutorialninja.forms.RegistrationForm;

public class LastNameValidator implements FieldValidator {

    @Override
    public String getFieldName() {
        return "lastName";
    }

    @Override
    public String getExpectedError() {
        return "Last Name must be between 1 and 32 characters!";
    }

    @Override
    public String getActualError(GenericFormWrapper<RegistrationForm> wrapper) {
        return wrapper.getText(wrapper.getForm().getErrors().get("lastName"));
    }
}
