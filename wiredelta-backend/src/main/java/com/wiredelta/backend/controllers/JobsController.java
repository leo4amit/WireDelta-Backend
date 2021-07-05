package com.wiredelta.backend.controllers;

import com.wiredelta.backend.models.request.UpdateProposalStatusRequest;
import com.wiredelta.backend.models.response.BaseResponse;
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
    public BaseResponse<JobProposalResponse> jobsProposalResponse() throws Exception {
        BaseResponse<JobProposalResponse> baseResponse=new BaseResponse();
        baseResponse.setData(jobsService.getAllJobProposals());
        return baseResponse;
    }

    @PostMapping(value = "/updateStatus")
    public BaseResponse<UpdateProposalStatusResponse> updateStatus(@RequestBody UpdateProposalStatusRequest updateJobProposalRequest) throws Exception {
        BaseResponse<UpdateProposalStatusResponse> baseResponse=new BaseResponse();
        baseResponse.setData(jobsService.updateJobProposals(updateJobProposalRequest));
        return baseResponse;
    }


}
