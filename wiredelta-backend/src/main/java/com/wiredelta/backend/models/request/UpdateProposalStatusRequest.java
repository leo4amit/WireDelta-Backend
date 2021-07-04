package com.wiredelta.backend.models.request;

import com.wiredelta.backend.models.enums.ProposalStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateProposalStatusRequest {

    @NotNull(message = "job id should not be null")
    Long id;

    @NotBlank(message = "status is mandatory")
    ProposalStatus status;

}
