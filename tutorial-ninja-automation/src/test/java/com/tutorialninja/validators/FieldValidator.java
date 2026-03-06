package com.tutorialninja.validators;

import com.tutorialninja.forms.GenericFormWrapper;
import com.tutorialninja.forms.RegistrationForm;

public interface FieldValidator {

    String getFieldName();

    String getInvalidValue();

    String getExpectedError();

    default String getActualError(GenericFormWrapper<RegistrationForm> wrapper) {
        return wrapper.getText(wrapper.getForm().getErrors().get(getFieldName()));
    }
}
