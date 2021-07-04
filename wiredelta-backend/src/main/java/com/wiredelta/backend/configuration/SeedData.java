package com.wiredelta.backend.configuration;

import com.wiredelta.backend.entity.Jobs;
import com.wiredelta.backend.entity.JobsProposal;
import com.wiredelta.backend.entity.User;
import com.wiredelta.backend.entity.UserRole;
import com.wiredelta.backend.repository.JobsProposalRepository;
import com.wiredelta.backend.repository.JobsRepository;
import com.wiredelta.backend.repository.RolesRepository;
import com.wiredelta.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Configuration
public class SeedData {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    JobsProposalRepository jobsProposalRepository;

    @Autowired
    JobsRepository jobsRepository;


    @PostConstruct
    public void seedData() {

        List<UserRole> roles = rolesRepository.findAll();

        if (roles == null || roles.size() == 0) {
            //save roles.
            UserRole userAdminRole = new UserRole();
            userAdminRole.setRole("Admin");
            rolesRepository.save(userAdminRole);

            UserRole userRole = new UserRole();
            userRole.setRole("User");
            rolesRepository.save(userRole);

            //user 1
            User user = new User();
            user.setUserName("amit");
            user.setEmail("amit.sharma@gmail.com");
            user.setPassword("amit123");
            user.setPhoneNo("7503359825");
            user.setActive(true);
            user.setUserRole(userAdminRole);
            user.setProfilePic("https://www.nicepng.com/png/detail/522-5226533_get-beyond-the-usual-suspects-profile-pic-icon.png");
            userRepository.save(user);


            User user2 = new User();
            user2.setUserName("Taniya");
            user2.setEmail("Taniya@gmail.com");
            user2.setPassword("Taniya123");
            user2.setPhoneNo("7503359825");
            user2.setActive(true);
            user2.setUserRole(userRole);
            user2.setProfilePic("https://www.nicepng.com/png/detail/522-5226533_get-beyond-the-usual-suspects-profile-pic-icon.png");
            userRepository.save(user2);

            saveJobs(user2);
        }
    }

    private void saveJobs(User user) {

        // jobs
        for (int i = 0; i < 10; i++) {
            Jobs jobs = new Jobs();
            jobs.setDescription("this is test description");
            jobs.setIsDone(false);

            if (i % 2 == 0)
                jobs.setIsEmergency(false);
            else
                jobs.setIsEmergency(true);

            jobs.setPrice(new BigDecimal(50));
            jobs.setTitle("Fix the Boat");
            jobs.setLatitude(Double.parseDouble("55.6761"));
            jobs.setLongitude(Double.parseDouble("12.5683"));
            jobs.setUser(user);

            jobsRepository.save(jobs);

            jobsProposals(jobs, i);

        }
    }

    private void jobsProposals(Jobs jobs, int i) {

        JobsProposal jobsProposal = new JobsProposal();
        jobsProposal.setDescription("This is proposal decription");
        jobsProposal.setCompany("Wire Delta");
        jobsProposal.setDate(new Date());
        if (i % 2 == 0)
            jobsProposal.setNegotiable(true);
        else
            jobsProposal.setNegotiable(false);
        jobsProposal.setJob(jobs);

        jobsProposalRepository.save(jobsProposal);

    }

}
