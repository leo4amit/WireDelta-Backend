package com.wiredelta.backend.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class Jobs {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private  Boolean isEmergency;

    private String title;

    private String description;

    private Double latitude;

    private Double longitude;

    private BigDecimal price;

    private Date dueDate;

    private Boolean isDone;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "userId")
    private User user;

    private String service;

    private String boatType;

    private String location;

}
