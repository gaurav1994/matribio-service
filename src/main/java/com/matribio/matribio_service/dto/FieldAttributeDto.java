package com.matribio.matribio_service.dto;

public class FieldAttributeDto {

    private Integer id;
    private String fieldKey;
    private String fieldValue;

    public FieldAttributeDto() {
    }
    public FieldAttributeDto(Integer id, String fieldKey, String fieldValue) {
        this.id = id;
        this.fieldKey = fieldKey;
        this.fieldValue = fieldValue;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getFieldKey() {
        return fieldKey;
    }
    public void setFieldKey(String fieldKey) {
        this.fieldKey = fieldKey;
    }
    public String getFieldValue() {
        return fieldValue;
    }
    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    // Implement Builder Design Pattern.

    public static class FieldAttributeDtoBuilder {
        private Integer id;
        private String fieldKey;
        private String fieldValue;
        
        public FieldAttributeDtoBuilder id(Integer id) {
            this.id=id;
            return this;
        }
        public FieldAttributeDtoBuilder fieldKey(String fieldKey) {
            this.fieldKey=fieldKey;
            return this;
        }
        public FieldAttributeDtoBuilder  fieldValue(String fieldValue) {
            this.fieldValue=fieldValue;
            return this;
        }

        public FieldAttributeDto build() {
            return new FieldAttributeDto(id, fieldKey, fieldValue);
        }
    }
    
    public static FieldAttributeDtoBuilder builder() {
        return new FieldAttributeDtoBuilder();
    }
}
