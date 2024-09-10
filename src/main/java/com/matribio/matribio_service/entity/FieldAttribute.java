package com.matribio.matribio_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fields_attribute_tbl")
public class FieldAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String fieldKey;
    private String fieldValue;
    
    public FieldAttribute() {
    }
    
    public FieldAttribute(Integer id, String fieldKey, String fieldValue) {
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

    public static class FieldAttributeBuilder {
        private Integer id;
        private String fieldKey;
        private String fieldValue;
        
        public FieldAttributeBuilder id(Integer id) {
            this.id=id;
            return this;
        }
        public FieldAttributeBuilder fieldKey(String fieldKey) {
            this.fieldKey=fieldKey;
            return this;
        }
        public FieldAttributeBuilder  fieldValue(String fieldValue) {
            this.fieldValue=fieldValue;
            return this;
        }

        public FieldAttribute build() {
            return new FieldAttribute(id, fieldKey, fieldValue);
        }
    }
    
    public static FieldAttributeBuilder builder() {
        return new FieldAttributeBuilder();
    }
}
