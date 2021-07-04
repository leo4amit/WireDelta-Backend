package com.wiredelta.backend.controllers;

import com.wiredelta.backend.models.request.UpdateProposalStatusRequest;
import com.wiredelta.backend.models.response.JobProposalResponse;
import com.wiredelta.backend.models.response.UpdateProposalStatusResponse;
import com.wiredelta.backend.services.JobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/jobs")
public class JobsController {

    @Autowired
    JobsService jobsService;

    @GetMapping(value = "/getAllJobs")
    public JobProposalResponse jobsProposalResponse() throws Exception {
        return jobsService.getAllJobProposals();
    }

    @PostMapping(value = "/updateStatus")
    public UpdateProposalStatusResponse updateStatus(@RequestBody UpdateProposalStatusRequest updateJobProposalRequest) throws Exception {
        return jobsService.updateJobProposals(updateJobProposalRequest);
    }


}
