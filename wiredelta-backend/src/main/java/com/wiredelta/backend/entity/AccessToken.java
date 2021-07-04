package com.wiredelta.backend.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class AccessToken {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String token;

    private Long ttl;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

}
