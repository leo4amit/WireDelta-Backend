package com.wiredelta.backend.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long userId;

    private String userName;

    private String password;

    private Boolean active;
    private String profilePic;
    private String email;
    private String phoneNo;

    @OneToMany(mappedBy = "user")
    private List<AccessToken> accessTokens;

    @ManyToOne
    @JoinColumn(name="role_id", referencedColumnName = "id")
    private UserRole userRole;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;
}
