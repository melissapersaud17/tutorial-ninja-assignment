package com.tutorialninja.validators;

import com.tutorialninja.forms.FormPage;
import com.tutorialninja.forms.GenericFormWrapper;

public interface FieldValidator {

    String getFieldName();

    String getInvalidValue();

    String getExpectedError();

    default boolean needsMaxLengthRemoved() {
        return false;
    }

    default String getActualError(GenericFormWrapper<? extends FormPage> wrapper) {
        return wrapper.getText(wrapper.getForm().getErrors().get(getFieldName()));
    }
}
