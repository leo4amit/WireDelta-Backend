package com.wiredelta.backend.services.impl;

import com.wiredelta.backend.entity.Jobs;
import com.wiredelta.backend.entity.JobsProposal;
import com.wiredelta.backend.errorCodes.WireDeltaError;
import com.wiredelta.backend.exception.JobsException;
import com.wiredelta.backend.models.dto.JobDto;
import com.wiredelta.backend.models.dto.JobProposalDto;
import com.wiredelta.backend.models.request.UpdateProposalStatusRequest;
import com.wiredelta.backend.models.response.JobProposalResponse;
import com.wiredelta.backend.models.response.UpdateProposalStatusResponse;
import com.wiredelta.backend.repository.JobsProposalRepository;
import com.wiredelta.backend.services.JobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobsServiceImpl implements JobsService {

    @Autowired
    JobsProposalRepository jobsProposalRepository;

    @Override
    public JobProposalResponse getAllJobProposals() {
        List<JobsProposal> jobsProposalEntities = jobsProposalRepository.findAll();
        List<JobProposalDto> jobProposals = generateJobProposals(jobsProposalEntities);
        JobProposalResponse jobsProposalResponse = new JobProposalResponse();
        jobsProposalResponse.setJobProposals(jobProposals);
        return jobsProposalResponse;
    }

    private List<JobProposalDto> generateJobProposals(List<JobsProposal> jobsProposalEntities) {
        List<JobProposalDto> jobProposalDtoList = new ArrayList<>();

        for (JobsProposal jobsProposalEntity : jobsProposalEntities) {
            JobProposalDto jobProposalDto = new JobProposalDto();
            jobProposalDto.setId(jobsProposalEntity.getId());
            jobProposalDto.setCompany(jobsProposalEntity.getCompany());
            jobProposalDto.setDescription(jobsProposalEntity.getDescription());
            jobProposalDto.setDate(jobsProposalEntity.getDate());
            jobProposalDto.setNegotiable(jobsProposalEntity.getNegotiable());
            jobProposalDto.setStatus(jobsProposalEntity.getStatus());
            JobDto jobDto = generateJobDto(jobsProposalEntity.getJob());
            jobProposalDto.setJobDto(jobDto);

            jobProposalDtoList.add(jobProposalDto);
        }

        return jobProposalDtoList;
    }

    private JobDto generateJobDto(Jobs jobsEntity) {
        JobDto jobDto = new JobDto();
        jobDto.setId(jobsEntity.getId());
        jobDto.setDescription(jobsEntity.getDescription());
        jobDto.setDueDate(jobsEntity.getDueDate());
        jobDto.setCreatedAt(jobsEntity.getCreatedAt());
        jobDto.setIsDone(jobsEntity.getIsDone());
        jobDto.setIsEmergency(jobsEntity.getIsEmergency());
        jobDto.setPrice(jobsEntity.getPrice());
        jobDto.setUpdatedAt(jobsEntity.getUpdatedAt());
        jobDto.setTitle(jobsEntity.getTitle());
        jobDto.setLatitude(jobsEntity.getLatitude());
        jobDto.setLongitude(jobsEntity.getLongitude());
        return jobDto;
    }

    @Override
    @Transactional
    public UpdateProposalStatusResponse updateJobProposals(UpdateProposalStatusRequest jobProposalDto) throws JobsException {

        JobsProposal jobsProposalEntity = jobsProposalRepository.getById(jobProposalDto.getId());

        if (jobsProposalEntity == null) {
            throw new JobsException(WireDeltaError.WD_ERR_1002);
        }

        jobsProposalEntity.setStatus(jobProposalDto.getStatus());

        jobsProposalRepository.save(jobsProposalEntity);

        UpdateProposalStatusResponse updateProposalStatusResponse = UpdateProposalStatusResponse.builder()
                .status(jobsProposalEntity.getStatus())
                .id(jobsProposalEntity.getId())
                .message("Job Proposal is " + jobProposalDto.getStatus()).build();

        return updateProposalStatusResponse;
    }
}
