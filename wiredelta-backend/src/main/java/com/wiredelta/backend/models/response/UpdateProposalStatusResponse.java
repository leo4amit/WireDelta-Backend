package com.wiredelta.backend.models.response;

import com.wiredelta.backend.models.enums.ProposalStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateProposalStatusResponse {

    private Long id;
    private ProposalStatus status;
    private String message;

}
