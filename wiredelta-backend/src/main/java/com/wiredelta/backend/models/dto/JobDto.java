package com.wiredelta.backend.models.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class JobDto {

    private Long id;

    private  Boolean isEmergency;

    private String title;

    private String description;

    private Double latitude;

    private Double longitude;

    private BigDecimal price;

    private Date dueDate;

    private Boolean isDone;

    private Date createdAt;

    private Date updatedAt;

    private String location;

    private String boatType;

    private String service;

    private String boatLocation;

    private String userName;

}
