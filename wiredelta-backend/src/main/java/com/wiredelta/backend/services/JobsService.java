package com.wiredelta.backend.services;

import com.wiredelta.backend.exception.JobsException;
import com.wiredelta.backend.models.request.UpdateProposalStatusRequest;
import com.wiredelta.backend.models.response.JobProposalResponse;
import com.wiredelta.backend.models.response.UpdateProposalStatusResponse;

public interface JobsService {

    JobProposalResponse getAllJobProposals() throws JobsException;

    UpdateProposalStatusResponse updateJobProposals(UpdateProposalStatusRequest jobProposalDto) throws JobsException;
}
