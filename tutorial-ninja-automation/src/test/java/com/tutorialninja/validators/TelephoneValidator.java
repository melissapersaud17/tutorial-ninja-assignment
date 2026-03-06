package com.tutorialninja.validators;

import com.tutorialninja.forms.GenericFormWrapper;
import com.tutorialninja.forms.RegistrationForm;

public class TelephoneValidator implements FieldValidator {

    @Override
    public String getFieldName() {
        return "telephone";
    }

    @Override
    public String getExpectedError() {
        return "Telephone must be between 3 and 32 characters!";
    }

    @Override
    public String getActualError(GenericFormWrapper<RegistrationForm> wrapper) {
        return wrapper.getText(wrapper.getForm().getErrors().get("telephone"));
    }
}
