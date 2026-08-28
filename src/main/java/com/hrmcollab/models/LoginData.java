package com.hrmcollab.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginData {

    private String testCaseId;
    private String description;
    private String username;
    private String password;
    private String expectedResult;   // SUCCESS or FAILURE
    private String expectedMessage;  // only relevant when expectedResult = FAILURE


    public String getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(String testCaseId) {
        this.testCaseId = testCaseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public String getExpectedMessage() {
        return expectedMessage;
    }

    public void setExpectedMessage(String expectedMessage) {
        this.expectedMessage = expectedMessage;
    }
    public boolean expectsSuccess() {
        return "SUCCESS".equalsIgnoreCase(expectedResult);
    }

    @Override
    public String toString() {
        return testCaseId + " - " + description; // shown in TestNG reports as the parameter label
    }

}
