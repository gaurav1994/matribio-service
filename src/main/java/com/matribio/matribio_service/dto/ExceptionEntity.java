package com.matribio.matribio_service.dto;

public class ExceptionEntity {
    private String errorId;
    private String errorMessage;
    private String errorDetails;
    private String errorType;

    public ExceptionEntity(String errorId, String errorMessage, String errorDetails, String errorType) {
        this.errorId = errorId;
        this.errorMessage = errorMessage;
        this.errorDetails = errorDetails;
        this.errorType = errorType;
    }
    public String getErrorId() {
        return errorId;
    }
    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public String getErrorDetails() {
        return errorDetails;
    }
    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }
    public String getErrorType() {
        return errorType;
    }
    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    // Implemnt Builder DSP

    public static class ExceptionEntityBuilder {
        private String errorId;
        private String errorMessage;
        private String errorDetails;
        private String errorType;

        public ExceptionEntityBuilder errorId(String errorId){
            this.errorId = errorId;
            return this;
        }
        public ExceptionEntityBuilder errorMessage(String errorMessage){
            this.errorMessage = errorMessage;
            return this;
        }
        public ExceptionEntityBuilder errorDetails(String errorDetails){
            this.errorDetails = errorDetails;
            return this;
        }
        public ExceptionEntityBuilder errorType(String errorType){
            this.errorType = errorType;
            return this;
        }
        public ExceptionEntity build() {
            return new ExceptionEntity(errorId, errorMessage, errorDetails, errorType);
        }
    }
    public static ExceptionEntityBuilder builder() {
        return new ExceptionEntityBuilder();
    }
}
