package com.rabbitmq.consumer.bean;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,
        property = "@id", scope = ResourceInfo.class)
public class ResourceInfo {

    private String resourceType;
    private String resourceURL;
    private String description;
}