package com.wiredelta.backend.entity;

import com.wiredelta.backend.models.enums.ProposalStatus;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class JobsProposal {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;

    private Date date;

    private String time;

    private String description;

    private Boolean negotiable;

    private String company;

    @Enumerated(EnumType.ORDINAL)
    private ProposalStatus status;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    @ManyToOne
    @JoinColumn(name="job_id", referencedColumnName = "id")
    private Jobs job;


}
