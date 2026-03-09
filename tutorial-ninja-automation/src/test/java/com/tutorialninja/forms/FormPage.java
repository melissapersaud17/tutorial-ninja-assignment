package com.tutorialninja.forms;

import org.openqa.selenium.By;

import java.util.Collections;
import java.util.Map;

public interface FormPage {

    Map<String, By> getFields();

    By getSubmitButton();

    By getPageWarning();

    default Map<String, By> getErrors() {
        return Collections.emptyMap();
    }
}
