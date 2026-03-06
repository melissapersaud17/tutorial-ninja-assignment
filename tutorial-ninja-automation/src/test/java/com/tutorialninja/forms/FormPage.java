package com.tutorialninja.forms;

import org.openqa.selenium.By;

import java.util.Map;

public interface FormPage {

    Map<String, By> getFields();

    By getSubmitButton();
}
