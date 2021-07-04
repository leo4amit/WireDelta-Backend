package com.wiredelta.backend.models.dto;

import com.wiredelta.backend.models.enums.ProposalStatus;
import lombok.Data;

import java.util.Date;

@Data
public class JobProposalDto {

    private Long id;

    private Date date;

    private String description;

    private Boolean negotiable;

    private String company;

    private JobDto jobDto;

    private ProposalStatus status;

}
