package com.tutorialninja.validators;

import com.tutorialninja.forms.GenericFormWrapper;
import com.tutorialninja.forms.RegistrationForm;

public class EmailValidator implements FieldValidator {

    @Override
    public String getFieldName() {
        return "email";
    }
    @Override
    public String getExpectedError() {
        return "E-Mail Address does not appear to be valid!";
    }

    @Override
    public String getActualError(GenericFormWrapper<RegistrationForm> wrapper) {
        return wrapper.getText(wrapper.getForm().getErrors().get("email"));
    }
}
