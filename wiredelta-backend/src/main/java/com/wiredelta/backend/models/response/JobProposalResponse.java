package com.wiredelta.backend.models.response;

import com.wiredelta.backend.models.dto.JobProposalDto;
import lombok.Data;

import java.util.List;

@Data
public class JobProposalResponse {

    private List<JobProposalDto> jobProposals;
}
