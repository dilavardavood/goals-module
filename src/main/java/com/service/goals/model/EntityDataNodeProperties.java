package com.service.goals.model;


public class EntityDataNodeProperties {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    private Long id;

    private Long entityDataNodeId;
    private String propertyName;
    private String propertyValue;

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    public Long getEntityDataNodeId() {
        return entityDataNodeId;
    }

    public void setEntityDataNodeId(Long entityDataNodeId) {
        this.entityDataNodeId = entityDataNodeId;
    }


    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
}
